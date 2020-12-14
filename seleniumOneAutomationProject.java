package homeWorks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.omg.PortableServer.ThreadPolicy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumOneAutomationProject {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * Test Case: VerifyNewOrderCreation
		 * 
		 */

		// 1. Open the chrome browser and
		// 2. Go to
		// http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx

		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		Thread.sleep(1500);

		// 3. Login using username Tester and password test

		String username = "Tester";
		String password = "test";

		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();

		// 4. Click on Order link
		driver.findElement(By.linkText("Order")).click();

		// 5. Enter a random quantity between 1 and 100
		// additional note: need to clear the initial "0" default value;

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE);

		int a = (int) (Math.random() * 100);

		System.out.println(a); // to check;

		WebElement quantity = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));

		quantity.sendKeys(Integer.toString(a));

		// 6. Enter Customer Name: John <Middle Name > Doe.
		// Instead of <Middle Name> your code should enter a random string of length 5
		// every time.

		String name = "Joe";
		String lastName = "Doe";

		String AZLetters = "abcdefghijklmnopqrstuvxyz";

		int neededMiddleNameLength = 5;

		StringBuilder sb = new StringBuilder(neededMiddleNameLength);

		for (int i = 0; i < neededMiddleNameLength; i++) {

			// generate a random number between 0 to neededMiddleNameLength length
			int index = (int) (AZLetters.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AZLetters.charAt(index));

		}

		System.out.println(sb.toString().toUpperCase()); // to check

		String middleName = sb.toString().toUpperCase();

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName"))
				.sendKeys(name + " " + middleName + " " + lastName);

		// 7. Enter street:

		String address = "8607 Westwood Center Dr";

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(address);

		// 8. Enter City: Vienna

		String city = "Vienna";

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);

		// 9. Enter State: Virginia

		String state = "Virginia";

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);

		// 10. Enter a random 5 digit number to the zip code field

		String randomZipNumbers = "0123456789";

		int neededZipLength = 5;

		StringBuilder sb2 = new StringBuilder(neededZipLength);

		for (int i = 0; i < neededZipLength; i++) {

			// generate a random number between 0 to neededZipLength length
			int index = (int) (randomZipNumbers.length() * Math.random());

			// add Character one by one in end of sb
			sb2.append(randomZipNumbers.charAt(index));

		}

		System.out.println(sb2.toString()); // to check

		String randomZip = sb2.toString();

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(randomZip);

		// 11. Select any card type. Every time your code should select a different
		// type.
		// AND
		// 12. Enter any card number: If you selected Visa, card number should start
		// with 4. If you selected Master, card number should start with 5. If you
		// selected American Express, card number should start with 3. New card number
		// should be auto generated every time you run the test. Card numbers should be
		// 16 digits for Visa and Master, 15 for American Express.

		int numOfCcards = 3; // as there 3 credit cards --> Visa, MCard, Amex;

		int index = (int) (numOfCcards * Math.random());

		System.out.println("index is equal to:" + index);

		String randomSelectedCard = "";

		String creditCardNums = "";

		if (index == 1) {
			randomSelectedCard = "ctl00_MainContent_fmwOrder_cardList_0";
			creditCardNums = "4" + (int) (Math.random() * 100000000) + (long) (Math.random() * 10000000);
			// 16 digits including first number 4 for VISA

		} else if (index == 2) {
			randomSelectedCard = "ctl00_MainContent_fmwOrder_cardList_1";
			creditCardNums = "5" + (long) (Math.random() * 100000000) + (long) (Math.random() * 10000000);
			// 16 digits including first number 5 for MCard
		} else {
			randomSelectedCard = "ctl00_MainContent_fmwOrder_cardList_2";
			creditCardNums = "3" + (int) ((Math.random() * 100000000) + (Math.random() * 1000000));
			// 15 digits including first number 3 for Amex
		}

		System.out.println("random selected card is:" + randomSelectedCard); // to check

		driver.findElement(By.id(randomSelectedCard)).click();

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(creditCardNums);

		// 13. Enter a valid expiration date (newer than the current date)

		Thread.sleep(1000);
		WebElement expDate = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1"));

		LocalDate currentDate = LocalDate.now();

		String futureDate = String.valueOf(currentDate.plusMonths(1));
		String year = futureDate.substring(2, 4);
		String month = futureDate.substring(5, 7);

		expDate.sendKeys(month + "/" + year);

		// 14. Click on Process

		WebElement processButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton"));
		processButton.click();

		// 15. Verify that the page contains text “New order has been successfully
		// added”.

		boolean actualResult = driver.getPageSource().contains("New order has been successfully added");
		if (actualResult) {
			System.out.println("PASS");
		} else {
			System.err.println("FAIL");

//Please push your java project to GitHub and submit your GitHub repository link

		}
	}
}
