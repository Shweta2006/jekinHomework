package assignmentJenkin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VariousConcepts {
	// Declaration of the webDriver
			WebDriver driver;
			Actions action;
			String browser = "chrome";

			// Web element List
			By USER_NAME = By.xpath("//input[@id='username']");
			By PASSWORD = By.xpath("//input[@id='password']");
			By LOGIN = By.xpath("//button[@name='login']");
			By DASH_BOARD = By.xpath("//h2[text()=' Dashboard ']");
			By CUSTOMER = By.xpath("//span[text()='Customers']");
			By ADD_CUSTOMER = By.linkText("Add Customer");
			By CONTACTS = By.xpath("//h2[text()=' Contacts ']");
			By FULL_NAME = By.xpath("//input[@id='account']");
			By COMPANY = By.xpath("//select[@id='cid']");
			By EMAIL = By.xpath("//input[@id='email']");
			By PHONE = By.xpath("//input[@id='phone']");
			By ADDRESS = By.xpath("//input[@id='address']");
			By CITY = By.xpath("//input[@id='city']");
			By STATE = By.xpath("//input[@id='state']");
			By ZIP = By.xpath("//input[@id='zip']");
			By COUNTRY = By.xpath("//span[@class='select2-selection__rendered']");
			By COUNTRY_TEXTBOX = By.xpath("//span[@class='select2-search select2-search--dropdown']/child::input");
			By TAG = By.xpath("//ul[@class='select2-selection__rendered']");
			By TAG_TEXTBOX = By.xpath("//input[@role='textbox']");
			By SUBMIT_BUTTON = By.xpath("//button[@id='submit']");
			// By CUSTOMER_NAME = By.xpath("//h5[text()='Annabelle']");
			By LIST_CUSTOMER = By.linkText("List Customers");
			By SEARCH_NEW_CUSTOMER = By.xpath("//input[@id='foo_filter']");
			By VALIDATE_CUSTOMER = By.linkText("Annabelle");

			// test data
			String userName = "demo@techfios.com";
			String password = "abc123";
			String fullName = "Annabelle";
			String email = "Anne1@gmail.com";
			String phone = "1234597800";
			String address = "Irving";
			String city = "Dallas";
			String state = "Texas";
			String zip = "23235";
			String country = "United States";
			String tag_name = "Ann";

			@BeforeClass
			public void config() {

				try {
					InputStream input = new FileInputStream("src/main/java/config/config.properties");
					Properties prop = new Properties();
					prop.load(input);
					browser = prop.getProperty("browser");
					System.out.println("browser used: " + browser);

				} catch (IOException e) {
					e.getStackTrace();
				}

			}

			@BeforeMethod
			public void init() {

				// if-else-if statement, as soon as the condition is met, the corresponding set
				// of statements get executed, rest gets ignored. If none of the condition is
				// met then the statements inside “else” gets executed.
				if (browser.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
					driver = new ChromeDriver();
				}

				else if (browser.equalsIgnoreCase("Firefox")) {
					System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
					driver = new FirefoxDriver();
				}

				driver.manage().deleteAllCookies();
				driver.get("http://techfios.com/billing/?ng=admin/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			}

			@Test
			public void loginTest() {
				driver.findElement(USER_NAME).sendKeys(userName);
				driver.findElement(PASSWORD).sendKeys(password);
				driver.findElement(LOGIN).click();
				Assert.assertEquals(driver.findElement(DASH_BOARD).getText(), "Dashboard", "Wrong Page");

				driver.findElement(CUSTOMER).click();
				driver.findElement(ADD_CUSTOMER).click();
				Assert.assertEquals(driver.findElement(CONTACTS).getText(), "Contacts", "Wrong Page");

				driver.findElement(FULL_NAME).sendKeys(fullName);
				// select key: Company DropDown field
				Select sel = new Select(driver.findElement(COMPANY));
				sel.selectByVisibleText("Techfios");
				driver.findElement(EMAIL).sendKeys(email);
				driver.findElement(PHONE).sendKeys(phone);
				driver.findElement(ADDRESS).sendKeys(address);
				driver.findElement(CITY).sendKeys(city);
				driver.findElement(STATE).sendKeys(state);
				driver.findElement(ZIP).sendKeys(zip);
				driver.findElement(COUNTRY).click();
				driver.findElement(COUNTRY_TEXTBOX).sendKeys(country);

				action = new Actions(driver);
				action.sendKeys(Keys.ENTER).build().perform();

				driver.findElement(TAG).click();
				driver.findElement(TAG_TEXTBOX).sendKeys(tag_name);
				action.sendKeys(Keys.ENTER).build().perform();

				driver.findElement(SUBMIT_BUTTON).click();
				// Assert.assertEquals(driver.findElement(CUSTOMER_NAME).getText(), fullName,
				// "Wrong Page");

				driver.findElement(LIST_CUSTOMER).click();
				driver.findElement(SEARCH_NEW_CUSTOMER).sendKeys(fullName);
				Assert.assertEquals(driver.findElement(VALIDATE_CUSTOMER).getText(), fullName, "Wrong Page");
				System.out.println("The New Customer is: " + fullName);
			}

			@AfterMethod
			public void burnDown() {
				driver.close();
				driver.quit();
			}

	}


	
		