package com.demo.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.demo.base.BaseClass;
import com.demo.pageobjects.CurrencyConversionCalculatorPage;

public class CurrencyConversionCalculatorPageTest extends BaseClass{
	ExtentReports extent= new ExtentReports();
	public CurrencyConversionCalculatorPageTest(){
		super();
	}
		
		
	@Test
	public void verifyLogo() throws Throwable{
	CurrencyConversionCalculatorPage currencypage = new CurrencyConversionCalculatorPage();
	System.out.println("TestCase01:");
	ExtentTest test1 = extent.createTest("TestCase01");
    Thread.sleep(2000);
	boolean logo = currencypage.validateLogo();
	test1.info("Asserting page logo");
	Assert.assertTrue(logo);
	//System.out.println("Page logo verified");
	test1.pass("Page logo verified");
	//System.out.println("=================");
    Thread.sleep(2000);

	}
	
	@Test
	public void sellAndBuyFieldVerify() throws Throwable {
		CurrencyConversionCalculatorPage currencypage = new CurrencyConversionCalculatorPage();
		ExtentTest test2 = extent.createTest("TestCase02");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
    	JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1200)", "");
        Thread.sleep(2000);
        
        WebElement buyinputarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main[1]/article[1]/section[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/input[1]")));   
        test2.info("Inserting value into buy input area field");
        buyinputarea.sendKeys("200");
        currencypage.clickFilterBtn();
        test2.pass("Inserted buy input field and clicked filter");
        
  
        WebElement sellinputarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main[1]/article[1]/section[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/input[1]")));
        test2.info("Clicking sell input area");
        sellinputarea.click();
        String value = sellinputarea.getAttribute("value"); 
        test2.pass("Observed sell input area value");
        test2.pass("When user fills BUY amount, SELL amount box is being : "+value.toString());
//    	System.out.println("TestCase02:");
//      System.out.println("When user fills BUY amount, SELL amount box is being : "+value.toString());
//    	System.out.println("=================");
        }
	
	@Test
	public void currencyVerifyAccordingToCountry() throws Throwable {
		CurrencyConversionCalculatorPage currencypage = new CurrencyConversionCalculatorPage();
		ExtentTest test3 = extent.createTest("TestCase03");
		JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,3000)", "");
        Thread.sleep(2000); 
    	//System.out.println("TestCase03:");
        
        currencypage.selectCountryByCountryName("United Kingdom");
        test3.pass("Country selected from dropdown");
        
        js.executeScript("window.scrollBy(0,1200)", "");
        Thread.sleep(2000);
        currencypage.selectCurrencyByCurrencyCode("GBP");
        test3.pass("Currency selected from dropdown");

	}
	
	@Test
	public void currencyRateUpdateVerifyAccordingToCountry() throws Throwable {
		CurrencyConversionCalculatorPage currencypage = new CurrencyConversionCalculatorPage();
		ExtentTest test4 = extent.createTest("TestCase04");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,3000)", "");
        Thread.sleep(2000);
    	//System.out.println("TestCase04:");
        currencypage.selectCountryByCountryName("Lithuania");
        test4.pass("Country selected Lithuania");

		js.executeScript("window.scrollBy(0,1200)", "");		
		String beforeUSDRate = currencypage.beforeRateUpdateCheck();
        test4.pass("Lithuania currency rate observed");
		
		js.executeScript("window.scrollBy(0,3000)", "");
        Thread.sleep(2000); 
        currencypage.selectCountryByCountryName("United Kingdom");
        test4.pass("Country selected United Kingdom");

        js.executeScript("window.scrollBy(0,1200)", "");
		String afterUSDRate = currencypage.afterRateUpdateCheck();
        test4.pass("United Kingdom currency rate observed");

		
		if(beforeUSDRate!=afterUSDRate) {
			test4.pass("Lithuania USD rate      :"+beforeUSDRate);
			test4.pass("United Kingdom USD rate :"+afterUSDRate);
//			System.out.println("Lithuania USD rate      :"+beforeUSDRate);
//			System.out.println("United Kingdom USD rate :"+afterUSDRate);
//			System.out.println("=================");
		}

	}
	
	@Test
	public void lossAmountVerification() throws Throwable {
		CurrencyConversionCalculatorPage currencypage = new CurrencyConversionCalculatorPage();
		ExtentTest test5 = extent.createTest("TestCase05");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,3000)", "");
        Thread.sleep(2000);
    	//System.out.println("TestCase05:");
        currencypage.selectCountryByCountryName("Lithuania");
        test5.pass("Country selected Lithuania");
		js.executeScript("window.scrollBy(0,1200)", "");
		
        String payseraAmount = currencypage.prayseraAmount();
        String swedbankAmount = currencypage.swedbankAmount();
        test5.pass("prayseraAmount observed");
        test5.pass("swedbankAmount observed");
        
        Double paysera = Double.valueOf(payseraAmount);
        Double swedbank = Double.valueOf(swedbankAmount);  

        if(paysera>swedbank) {
        	String banklossAmount = currencypage.bankLossAmount();
            test5.pass("Paysera amount : "+payseraAmount+" is greater than swedbank amount : "+swedbankAmount
        			+ " The loss amount is : "+banklossAmount);

//        	System.out.println("Paysera amount : "+payseraAmount+" is greater than swedbank amount : "+swedbankAmount
//        			+ " The loss amount is : "+banklossAmount);
//        	System.out.println("=================");
        }
	}



}
