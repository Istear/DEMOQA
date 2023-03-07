package istearAhmed.task3;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAlerts extends BaseDriver {
	public String baseUrl = "https://demoqa.com/";
	@BeforeClass
	public void homePage()throws InterruptedException {
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	@Test (priority = 0, description = "Main page displayed.")
	public void mainPage() throws InterruptedException{
		String mainPageTitlElement = driver.getTitle();
		Assert.assertEquals(mainPageTitlElement, "DEMOQA");
		Thread.sleep(5000);
	}
	
	@Test (priority = 1, description = "Alert form is displayed.")
	public void alertForm() throws InterruptedException{
		WebElement alertBoxElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/*[1]"));
		Thread.sleep(2000);
		alertBoxElement.click();
		Thread.sleep(2000);
		WebElement alertButtonElement = driver.findElement(By.xpath("//span[contains(text(),'Alerts')]"));
		alertButtonElement.click();
		Thread.sleep(2000);
		WebElement alertFormElement = driver.findElement(By.id("javascriptAlertsWrapper"));
		Assert.assertTrue(alertFormElement.isDisplayed());
	}
	
	@Test (priority = 2, description = "First button alert test")
	public void seeFirstAlert() throws InterruptedException{
		WebElement allertButtonElement = driver.findElement(By.id("alertButton"));
		allertButtonElement.click();
		Thread.sleep(2000);
		String firstMassagElement = driver.switchTo().alert().getText();
		Assert.assertEquals(firstMassagElement, "You clicked a button");
		Thread.sleep(2000);
		Alert alertOne = driver.switchTo().alert();
		alertOne.accept();
	}
	
	@Test (priority = 3, description = "Confirm alert test")
	public void confirmBoxAlert() throws InterruptedException{
		WebElement confirmAllertButtonElement = driver.findElement(By.id("confirmButton"));
		confirmAllertButtonElement.click();
		Thread.sleep(2000);
		String secondMassageElementString = driver.switchTo().alert().getText();
		Assert.assertEquals(secondMassageElementString, "Do you confirm action?");
		Thread.sleep(2000);
		Alert alertTwoAlert = driver.switchTo().alert();
		alertTwoAlert.accept();
		WebElement confirmResElement = driver.findElement(By.id("confirmResult"));
		String confirmTextString = confirmResElement.getText();
		Assert.assertEquals(confirmTextString,"You selected Ok");
	}
	@Test (priority = 4, description = "Prompt alert test")
	public void promptBoxAlert() throws InterruptedException{
		WebElement promptAllertButtonElement = driver.findElement(By.id("promtButton"));
		promptAllertButtonElement.click();
		Thread.sleep(2000);
		String thirdMassageElementString = driver.switchTo().alert().getText();
		Assert.assertEquals(thirdMassageElementString, "Please enter your name");
		String promptString = "Prompt Testing";
		driver.switchTo().alert().sendKeys(promptString);
		Alert alertThreeAlert = driver.switchTo().alert();
		Thread.sleep(7000);
		alertThreeAlert.accept();
		
		WebElement promptResElement = driver.findElement(By.id("promptResult"));
		String promptTextString = promptResElement.getText();
		Assert.assertEquals(promptTextString,"You entered "+promptString);
		Thread.sleep(5000);
	}
	@AfterClass
	public void closeWindow() {
		driver.close();
	}
}
