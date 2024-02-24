package com.qc.testng.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseIntegration{

	@BeforeMethod
	public void locateElement() {
		email = driver.findElement(By.xpath("//input[@type='text']"));
		email.clear();
		pass = driver.findElement(By.xpath("//input[@type='password']"));
		pass.clear();
		signin = driver.findElement(By.xpath("//button[@type='submit']"));
	}
	
	@Test(dataProvider = "loginData")
	public void doLogin(String testName, String uName, String uPass) {
		tName = testName;
		email.sendKeys(uName);
		pass.sendKeys(uPass);
		signin.click();
	}
	
	@AfterMethod
	public void doAssert() throws InterruptedException {
		Thread.sleep(2000);
		String actResult = driver.getTitle();
		String expResult;
		if(tName.equals("Both are valid")) {
			expResult = "Queue Codes | Dashboard";
			doLogout();
		}else {
			expResult = "Queue Codes | Log in";
		}
		Assert.assertEquals(actResult, expResult);		
	}
	
	public void doLogout() {
		logout = driver.findElement(By.id("hlogout"));
		logout.click();
	}
}
