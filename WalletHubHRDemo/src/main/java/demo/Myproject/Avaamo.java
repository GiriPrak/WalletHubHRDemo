package demo.Myproject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Avaamo {

	public static WebDriver driver;
	public static WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./chromeDriver/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 15);
	}

	@Test(enabled = true)
	public void verifyFBCase() throws Exception {
		try {
			String usrName = "giri.araveti@avaamo.com", password = "Pa$$@FB123";
			driver.get("https://www.facebook.com");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(usrName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='xhpc_message']")))
					.clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='xhpc_message']")))
					.sendKeys("Hello World");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_45wg _69yt'])")))
					.click();
			System.out
					.println("POST :: " + driver.findElement(By.xpath("//div[@data-testid='post_message']")).getText());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Test
	public void verifyWalletHub() throws Exception {
		// String usrName="giri.rvt@gmail.com", password="Pa$$@FB123";
		try {
			driver.get("http://wallethub.com/profile/test_insurance_company");
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='brgm-button brgm-signup'])"))).click();
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='em'])"))).click();
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='pw'])"))).click();
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])"))).click();

			Actions act = new Actions(driver);
			WebElement starthree = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector(".review-action .rvs-star-svg:nth-child(3) path:nth-child(1)")));
			String checked = starthree.getAttribute("fill");
			act.click(starthree).build().perform();
			String unchecked = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#reviews-section > modal-dialog > div > div > write-review > review-star > div > svg:nth-child(4) > g > path")))
					.getAttribute("fill");
			System.out.println(unchecked + "<< >>" + checked);
			WebElement selopt = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Select...')]")));
			act.click(selopt).build().perform();
			WebElement optns = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'Health Insurance')]")));
			act.click(optns).build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/textarea"))).sendKeys(
					"WalletHub’s brain performs three primary functions, providing: 1) Customized credit-improvement advice; 2) Personalized savings alerts; and 3) 24/7 wallet surveillance, and is based in Washington, DC.");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,'Submit')]"))).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void Afteeeer() throws IOException {
		System.out.println("close browser");
		driver.quit();

	}

}
