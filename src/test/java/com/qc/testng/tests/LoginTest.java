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
		pass = driver.findElement(By.xpath("//input[@type='password']"));
		signin = driver.findElement(By.xpath("//button[@type='submit']"));
	}
	
	@Test
	public void doLogin() {
		email.sendKeys("queuecodes@gmail.com");
		pass.sendKeys("123456");
		signin.click();
	}
	
	@AfterMethod
	public void doAssert() {
		String actResult = driver.getTitle();
		String expResult = "Queue Codes | Dashboard";
		Assert.assertEquals(actResult, expResult);
		doLogout();
	}
	
	public void doLogout() {
		logout = driver.findElement(By.id("hlogout"));
		logout.click();
	}
}
