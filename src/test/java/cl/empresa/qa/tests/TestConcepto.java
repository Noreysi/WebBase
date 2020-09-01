package cl.empresa.qa.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cl.empresa.qa.helpers.Helper;
import cl.empresa.qa.pages.LoginPage;
import cl.empresa.qa.pages.PaginaPrincipalPage;
import cl.empresa.qa.pages.RegistroPage;
import cl.empresa.qa.vo.LoginVO;

public class TestConcepto {
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;
	private static String SUBDIR = "AmbienteBase\\";
	private static Boolean TAKE_SS = true;
	private static int WAITING = 10;

	@BeforeSuite
	public void configExtentReports() {
		// ExtentReports config
		this.extent = new ExtentReports("ExtentReports/PruebaCurso.html", true);
		this.extent.addSystemInfo("Host Name", "Tecnova Soluciones Informaticas SA");
		this.extent.addSystemInfo("Enviroment", "Automation Testing");
		this.extent.addSystemInfo("User Name", "Heliam Ordejoiti");
        //llamada a objeto de configuracion de Extent report
		File conf = new File("src/test/resources/extentReports/" + "extent-config.xml");
		this.extent.loadConfig(conf);
	}

	@BeforeMethod
	@Parameters({ "URL_PRINCIPAL" })
	public void configSelenium(String URL_PRINCIPAL) {
		// Selenium config
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("Empresa", "Tecnova");
		System.setProperty("webdriver.chrome.driver", "DRIVERS/chromedriver.exe");
		//Helper.robotMoveMouse(2000, 2000);
		driver = new ChromeDriver();
		//Implicit Waits No usar si se estan usando explicit wait
		driver.manage().timeouts().implicitlyWait(WAITING, TimeUnit.SECONDS);
		//Maximisar Ventana
		driver.manage().window().maximize();
		//Navegar a url principal
		driver.navigate().to(URL_PRINCIPAL);
	}

	@Test
	@Parameters({ "nombre", "apellido", "pass" })
	public void crearRegistro(String nombre, String Apellido, String pass) {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba CONCEPTO más", "Probando conceptos MáS.");
		test.log(LogStatus.INFO, "Prueba inicial conceptos");
		PaginaPrincipalPage principal = new PaginaPrincipalPage(driver, test, TAKE_SS, 20);
		LoginPage login = new LoginPage(driver, test, TAKE_SS, 20);
		RegistroPage registro = new RegistroPage(driver, test, TAKE_SS, 20);
		principal.clickLogin();
		login.registroMail("prueba3333rrr3@prueba.com", subDir);
		registro.informacionPersonal(nombre, Apellido, pass, subDir);
   
	}

	@Test
	@Parameters({ "ingreso", "hoja1"})
	public void loginUsuario(String ingreso, String hoja1) {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba CONCEPTO más", "Probando conceptos MáS.");
		test.log(LogStatus.INFO, "Prueba inicial conceptos");
		LoginVO datosVO = new LoginVO(ingreso,hoja1);
		PaginaPrincipalPage principal = new PaginaPrincipalPage(driver, test, TAKE_SS, 20);
		LoginPage login = new LoginPage(driver, test, TAKE_SS, 20);
		principal.clickLogin();
		login.loginMetodo(datosVO.getUserVO(),datosVO.getPassVO());
		login.assertIngreso("MY ACCOUNT");

	}

	@Test
	public void crearRegistroConTelefonoCasa() {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Crear Registro con telefono de casa", "Prueba de registro con telefono.");
		test.log(LogStatus.INFO, "Prueba Con telefono");
		PaginaPrincipalPage principal = new PaginaPrincipalPage(driver, test, TAKE_SS, 20);
		LoginPage login = new LoginPage(driver, test, TAKE_SS, 20);
		RegistroPage registro = new RegistroPage(driver, test, TAKE_SS, 20);
		principal.clickLogin();
		login.registroMail("correobnbn@correo.com", subDir);
		registro.informacionPersonal("Antonio", "Rojas", "123456", subDir);
		registro.informacionDireccion(subDir);
		registro.ingresarTelefonoCasa();
		registro.registrarInformacion();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test failed.- <br>" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped.- <br>" + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed.-");
		}
		// driver.close();
		extent.endTest(test);
	}

	@AfterSuite
	public void closeExtentReports() {
		// Escribimos los datos al reporte.
		extent.flush();
	}
}
