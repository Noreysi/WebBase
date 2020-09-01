package cl.empresa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import cl.empresa.qa.helpers.Helper;
import cl.empresa.qa.helpers.PageWeb;

public class RegistroPage extends PageWeb {

	// Atributos
	private By generoFemenino;
	private By nombre;
	private By apellido;
	private By password;
	private By diaNacimiento;
	private By anoNacimiento;
	private By mesNacimiento;
	private By compania;
	private By direccion;
	private By ciudad;
	private By estado;
	private By zipCode;
	private By telefono;
	private By aliasDireccion;
	private By clickRegistrar;
	private By telefonoCasa;

	// Constructor
	public RegistroPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);

		this.generoFemenino = By.id("id_gender2");
		this.nombre = By.id("customer_firstname");
		this.apellido = By.id("customer_lastname");
		this.password = By.id("passwd");
		this.diaNacimiento = By.id("days");
		this.mesNacimiento = By.id("months");
		this.anoNacimiento = By.id("years");
		this.compania = By.id("company");
		this.direccion = By.id("address1");
		this.ciudad = By.id("city");
		this.estado = By.id("id_state");
		this.zipCode = By.id("postcode");
		this.telefono = By.id("phone_mobile");
		this.aliasDireccion = By.id("alias");
		this.clickRegistrar = By.xpath("//*[@id=\"submitAccount\"]/span");
		this.telefonoCasa = By.id("phone");

	}
	// Metodos

	public void informacionPersonal(String name, String apellidos, String pass, String subDir) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(generoFemenino))).click();
		Helper.addEvidence(TAKE_SS, driver, test, "Informacion Registro Vacia", subDir, "informacionPersonal_01");
		driver.findElement(nombre).sendKeys(name);
		driver.findElement(apellido).sendKeys(apellidos);
		driver.findElement(password).sendKeys(pass);
		Select dia = new Select(driver.findElement(diaNacimiento));
		dia.selectByVisibleText("10  ");
		Select mes = new Select(driver.findElement(mesNacimiento));
		mes.selectByVisibleText("June ");
		Select ano = new Select(driver.findElement(anoNacimiento));
		ano.selectByVisibleText("1988  ");
		Helper.waitSeconds(1);
		Helper.addEvidence(TAKE_SS, driver, test, "Informacion Registro con informacion personal", subDir,
				"informacionPersonal_02");

	}

	public void informacionDireccion(String subDir) {

		driver.findElement(compania).sendKeys("Selenium");
		driver.findElement(direccion).sendKeys("Avenida prueba calle prueba");
		driver.findElement(ciudad).sendKeys("Santiago");
		Select state = new Select(driver.findElement(estado));
		state.selectByVisibleText("New York");
		driver.findElement(zipCode).sendKeys("00000");
		driver.findElement(telefono).sendKeys("324323453");
		driver.findElement(aliasDireccion).clear();
		driver.findElement(aliasDireccion).sendKeys("aliasprueba");
		Helper.addEvidence(TAKE_SS, driver, test, "Informacion Registro con informacion personal", subDir,
				"informacionDireccion_01");
	}

	public void ingresarTelefonoCasa() {

		driver.findElement(telefonoCasa).sendKeys("55445533");

	}

	public void registrarInformacion() {

		driver.findElement(clickRegistrar).click();

	}

}
