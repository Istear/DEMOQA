package istearAhmed.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestIframe extends BaseDriver{
	public String baseUrl = "https://demoqa.com/";
	@BeforeClass
	public void homePage()throws InterruptedException {
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	@Test (priority = 0, description="Main page is displayed.")
	public void mainPage() throws InterruptedException{
		String mainPageTitlElement = driver.getTitle();
		Assert.assertEquals(mainPageTitlElement, "DEMOQA");
		Thread.sleep(2000);
	}
	@Test (priority = 1, description = "Nested frame form is open and parent frame and child frame massage is presented.")
	public void alertForm() throws InterruptedException{
		WebElement alertBoxElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/*[1]"));
		Thread.sleep(2000);
		alertBoxElement.click();
		Thread.sleep(2000);
		WebElement NestedFrameBoxElement = driver.findElement(By.xpath("//span[contains(text(),'Nested Frames')]"));
		Thread.sleep(2000);
		NestedFrameBoxElement.click();
		Thread.sleep(2000);
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		//WebElement frameOneMassagElement = driver.findElement(By.xpath("//body"));
		Assert.assertTrue(driver.getPageSource().contains("Parent frame"));
		//String massageOneString = frameOneMassagElement.getText();
		WebElement frame2 = driver.findElement(By.xpath("//body/iframe[1]"));
		driver.switchTo().frame(frame2);
		//WebElement frameTwoMassagElement = driver.findElement(By.xpath("//p[contains(text(),'Child Iframe')]"));
		//String massageTwoString = frameTwoMassagElement.getText();
		Assert.assertTrue(driver.getPageSource().contains("Child Iframe"));
		driver.switchTo().defaultContent();
		//Assert.assertEquals(massageOneString,"Parent frame");
		//Assert.assertEquals(massageTwoString,"Child iframe");
	}
	
	@Test (priority = 2, description = "Frame form is open, Massage from upper frame is equal to massage from lower frame.")
	public void frame() throws InterruptedException{
		WebElement frameBoxElement = driver.findElement(By.xpath("//span[text()='Frames']"));
		frameBoxElement.click();
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		WebElement frameOneStringElement = driver.findElement(By.id("sampleHeading"));
		String frameOneString = frameOneStringElement.getText();
		driver.switchTo().defaultContent();
		WebElement frame2 = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(frame2);
		WebElement frameTwoStringElement = driver.findElement(By.id("sampleHeading"));
		String frameTwoString = frameTwoStringElement.getText();
		driver.switchTo().defaultContent();
		Assert.assertEquals(frameOneString, frameTwoString);
	}
	
	@AfterClass
	public void closeWindow() {
		driver.close();
	}
}
