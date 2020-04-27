package demo.Myproject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class FbTest extends UtilClass {

	@Test
	public void verifyFBCase() throws Exception {

		try {
			String usrName = "giri.araveti@avaamo.com", password = "******";
			driver.get("https://www.facebook.com");
			testReport = extent.createTest("verifyFBCase");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(usrName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='xhpc_message']")))
					.clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='xhpc_message']")))
					.sendKeys("Hello World ");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_45wg _69yt'])")))
					.click();
				
			
//				Thread.sleep(1000);
//				if(driver.findElement(By.xpath("//div[contains(.,'Duplicate Status Update')]")).isDisplayed())
//				{   System.out.println("found");
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='autofocus  layerCancel _4jy0 _4jy3 _4jy1 _51sy selected _42ft'])"))).click();
//				}
				testReport.log(Status.PASS, "Posted message on FB is :: "+ driver.findElement(By.xpath("//div[@data-testid='post_message']")).getText());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNavigationLabel"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'Log Out')]"))).click();
			

			Thread.sleep(500);
			if(wait.until(ExpectedConditions.alertIsPresent()) !=null)
			{
			Alert alrt = driver.switchTo().alert();
			alrt.accept();}
			testReport.log(Status.PASS, "Verify FB");

			
		} catch (Exception e) {

			e.printStackTrace();

		}
		testReport.log(Status.PASS, "Logged  out of FB");
	}
	
	@Test
	public void verifyWalletHub() throws Exception {
		testReport = extent.createTest("verifyWalletHub");

		String usrName="giri.rvt@gmail.com", password="*******";
		String MyText="WalletHub’s brain performs three primary functions, providing: 1) Customized credit-improvement advice; 2) Personalized savings alerts; and 3) 24/7 wallet surveillance, and is based in Washington, DC.";

		try {
			driver.get("http://wallethub.com/profile/test_insurance_company");

			Actions act = new Actions(driver);
			WebElement starthree = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector(".review-action .rvs-star-svg:nth-child(3) path:nth-child(1)")));
			String checked = starthree.getAttribute("fill");
			act.click(starthree).build().perform();
			String unchecked = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#reviews-section > modal-dialog > div > div > write-review > review-star > div > svg:nth-child(4) > g > path")))
					.getAttribute("fill");
			testReport.log(Status.PASS, "Before Selection on UI star was in grey "+unchecked);
			testReport.log(Status.PASS, "After Selected Star turned to colcor "+checked);
			WebElement selopt = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Select...')]")));
			act.click(selopt).build().perform();
			WebElement optns = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'Health Insurance')]")));
			act.click(optns).build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/textarea"))).sendKeys(MyText);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sbn-action semi-bold-font btn fixed-w-c tall']"))).click();
		} catch (Exception e) {
			
			e.printStackTrace();
			testReport.log(Status.FAIL,  e.toString());
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ugc-thanks']"))).getText();
		testReport.log(Status.INFO,"Redirect to login page");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='em'])"))).sendKeys(usrName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='password'])"))).sendKeys(password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Login')]"))).click();
		testReport.log(Status.PASS, "Submitted the review");
//		Thread.sleep(1500);
//		Actions act = new Actions(driver);
//		WebElement MyUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("brgm-button .brgm-user .brgm-list-box")));
//		act.moveToElement(MyUser).build().perform();
//		WebElement LogOff=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-form")));
//		LogOff.click();
//
//		testReport.log(Status.PASS, "Clciked on most recent ");
		wait.until(ExpectedConditions.titleContains("Test Insurance Company Reviews"));//.urlContains("https://wallethub.com/profile/test-insurance-company"));
		String MyReview = driver.findElement(By.cssSelector("div.rvtab-ci-content.with-links.text-select")).getText();
		Assert.assertEquals(MyText, MyReview);
		testReport.log(Status.PASS, "MyInput ::"+ MyText );
		testReport.log(Status.PASS, "MyReview ::"+ MyReview);

		
		

	}
}
