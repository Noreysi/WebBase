package cl.empresa.qa.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.relevantcodes.extentreports.ExtentTest;

import cl.empresa.qa.helpers.PageWeb;

public class PaginaPrincipalPage extends PageWeb {

	// Atributos
    private By clickSighIn;


	// Constructor
	public PaginaPrincipalPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);
    
    this.clickSighIn = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
		
	}
	// Metodos
	
    public void clickLogin () 
    {
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(clickSighIn))).click();
    	
    }
	
	
}
