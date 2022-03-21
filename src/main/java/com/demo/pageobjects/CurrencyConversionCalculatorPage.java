package com.demo.pageobjects;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.base.BaseClass;

public class CurrencyConversionCalculatorPage extends BaseClass{
	
	@FindBy (css = "input[class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']")
	WebElement sellInputArea;
	@FindBy (css = "input[class='form-control ng-pristine ng-valid ng-empty ng-touched']")
	WebElement buyInputArea;
	@FindBy (xpath = "//span[contains(text(),'EUR')]")
	WebElement dropDownEUR;
	@FindBy (xpath = "//button[contains(text(),'Filter')]") 
	WebElement filterBtn;
	@FindBy (xpath = "//button[contains(text(),'Clear filter')]") 
	WebElement clearFilterBtn;
	@FindBy (xpath = "//img[@class='main ls-is-cached lazyloaded']") 
	WebElement companyLogo;		
	@FindBy (xpath = "//strong[@id='rates']") 
	WebElement rateTitle;	
	@FindBy (xpath = "//span[@role='button']") 
	WebElement popupCountrySelectionBtn;
	@FindBy (xpath = "//span[contains(text(),'GBP')]") 
	WebElement currencyDropDownBtn;	
	@FindBy (xpath = "//table[@class='transformable-table table table-striped']//tr[1]//td[3]") 
	WebElement eurUsaPayseraRate;
	@FindBy (xpath = "//table[@class='transformable-table table table-striped']//tr[1]//td[3]") 
	WebElement gbpUsaPayseraRate;
	@FindBy (xpath = "//table[@class='transformable-table table table-striped']//tr[1]//td[4]") 
	WebElement eurUsaPayseraAmount;
	@FindBy (xpath = "//table[@class='transformable-table table table-striped']//tr[1]//td[5]//span[@class='ng-binding']") 
	WebElement eurUsaSwedbankAmount;
	@FindBy (xpath = "//table[@class='transformable-table table table-striped']//tr[1]//td[5]//span[@class='other-bank-loss ng-binding ng-scope']") 
	WebElement eurUsabankLossAmount;
	
	
	public CurrencyConversionCalculatorPage () {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement sellInputField() throws Throwable  {
		return sellInputArea;	
	}
	
	public WebElement buyInputField() throws Throwable  {
		return buyInputArea;	
	}
	
	public void insertSellInput() throws Throwable {
		sellInputArea.sendKeys("200");
	}
	
	public void insertBuyInput() throws Throwable {
		buyInputArea.sendKeys("200");
	}
	
	public void clickFilterBtn() throws Throwable {
		filterBtn.click();
	}
	
	public void clickClearFilterBtn() throws Throwable {
		clearFilterBtn.click();	
	}
	
	public boolean validateLogo() throws Throwable {
		return companyLogo.isDisplayed();	
	}
	
	public boolean validateRatesTitle() throws Throwable {
		return rateTitle.isDisplayed();		
	}
	
	public String beforeRateUpdateCheck() throws Throwable {		
		//String eurUSDPRate = eurUsaPayseraRate.getAttribute("innerHTML");
		String eurUSDPRate = eurUsaPayseraRate.getText();
		return eurUSDPRate;
	}
	public String afterRateUpdateCheck() throws Throwable {		
		//String gbpUSDPRate = gbpUsaPayseraRate.getAttribute("innerHTML");
		String gbpUSDPRate = gbpUsaPayseraRate.getText();
		return gbpUSDPRate;
	}
	
	public String prayseraAmount() throws Throwable {	
		String eurUsaPayseraRate = eurUsaPayseraAmount.getText();
		return eurUsaPayseraRate;
	}
	public String swedbankAmount() throws Throwable {	
		String eurUsaSwedBankRate = eurUsaSwedbankAmount.getText();
		return eurUsaSwedBankRate;
	}
	public String bankLossAmount() throws Throwable {	
		String eurUsaBankLossAmount = eurUsabankLossAmount.getText();
		return eurUsaBankLossAmount;
	}
	
	
	public void selectCurrencyByCurrencyCode(String currencycode) {
		
		String actualCurrency = currencyDropDownBtn.getAttribute("innerHTML");
		if (actualCurrency.contains(currencycode)){
			System.out.println("Currency '"+actualCurrency+"' is changed with respect to country");
			System.out.println("=================");
		}
	}
	
	public void selectCountryByCountryName(String country) throws Throwable {		
		popupCountrySelectionBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[@id='countries-dropdown']")).click();

		List<WebElement> country_menu = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a "));

		for (int i=0;i<country_menu.size();i++) {
			WebElement countryname = country_menu.get(i);
			String innerhtml =countryname.getAttribute("innerHTML");			
			if (innerhtml.contains(country)) {
				countryname.click();
				break;		
			}
		}
		
		System.out.println("Country is changed to '"+country+"'");

		}
	}
