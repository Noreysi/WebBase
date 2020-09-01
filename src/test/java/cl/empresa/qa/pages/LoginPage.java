package cl.empresa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import cl.empresa.qa.helpers.Helper;
import cl.empresa.qa.helpers.PageWeb;

public class LoginPage extends PageWeb {

	// Atributos
	private By ingresarMail;
	private By clickCrear;
	private By userEmail;
	private By password;
	private By clickIniciar;
	private By validarIngreso;

	// Constructor
	public LoginPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);

		this.ingresarMail = By.id("email_create");
		this.clickCrear = By.xpath("//*[@id=\"SubmitCreate\"]/span");
		this.userEmail = By.id("email");
		this.password = By.id("passwd");
		this.clickIniciar = By.xpath("//*[@id=\"SubmitLogin\"]/span");
		this.validarIngreso = By.xpath("//*[@id=\"center_column\"]/h1");

	}
	// Metodos

	public void registroMail(String correoIngreso, String subDir) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(ingresarMail))).sendKeys(correoIngreso);
		Helper.addEvidence(TAKE_SS, driver, test, "Ingreso Correo a registrar", subDir, "registroMail_01");
		driver.findElement(clickCrear).click();

	}

	public void loginMetodo(String user, String pass) {
		//
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(userEmail))).sendKeys(user);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(clickIniciar).click();

	}

	public void assertIngreso(String titulo) {
		Assert.assertEquals(driver.findElement(validarIngreso).getText(), titulo);

	}

}
