package istearAhmed.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTable extends BaseDriver {
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
	
	@Test (priority = 1, description = "Table form add user and delete funtionality.")
	public void alertForm() throws InterruptedException{
		WebElement elementBoxElement = driver.findElement(By.xpath("//h5[text()='Elements']"));
		elementBoxElement.click();
		Thread.sleep(2000);
		WebElement webTableBoxElement = driver.findElement(By.xpath("//span[text()='Web Tables']"));
		webTableBoxElement.click();
		WebElement tableFormElement = driver.findElement(By.className("web-tables-wrapper"));
		Assert.assertTrue(tableFormElement.isDisplayed(),"Table form is not displayed");
		WebElement addButtonElement = driver.findElement(By.id("addNewRecordButton"));
		addButtonElement.click();
		WebElement registerFormElement = driver.findElement(By.id("registration-form-modal"));
		Assert.assertTrue(registerFormElement.isDisplayed(), "Register form is not displayed");
		
		WebElement firstNameElement = driver.findElement(By.id("firstName"));
		WebElement lastNameElement = driver.findElement(By.id("lastName"));
		WebElement emailElement = driver.findElement(By.id("userEmail"));
		WebElement ageElement = driver.findElement(By.id("age"));
		WebElement salaryElement = driver.findElement(By.id("salary"));
		WebElement departmentElement = driver.findElement(By.id("department"));
		
		String[] dataStrings =new String[] {"Kalam", "Khan", "abcd@gmail.com", "71", "50000", "ICT"};
		
		firstNameElement.sendKeys(dataStrings[0]);
		Thread.sleep(2000);
		lastNameElement.sendKeys(dataStrings[1]);
		Thread.sleep(2000);
		emailElement.sendKeys(dataStrings[2]);
		Thread.sleep(2000);
		ageElement.sendKeys(dataStrings[3]);
		Thread.sleep(2000);
		salaryElement.sendKeys(dataStrings[4]);
		Thread.sleep(2000);
		departmentElement.sendKeys(dataStrings[5]);
		Thread.sleep(2000);
		
		WebElement submitButtonElement = driver.findElement(By.xpath("//button[@id='submit']"));
		
		submitButtonElement.click();
		
		for(int i = 0; i<dataStrings.length; i++ ) {
			Assert.assertTrue(driver.getPageSource().contains(dataStrings[i]));
			Thread.sleep(2000);
		}
		WebElement editElement = driver.findElement(By.id("edit-record-4"));
		WebElement deleteElement = driver.findElement(By.id("delete-record-4"));
		deleteElement.click();
		
		Assert.assertFalse(driver.getPageSource().contains(dataStrings[0]), "User is not deleted");
	
	}
	@AfterClass
	public void closeWindow() {
		driver.close();
	}
}
