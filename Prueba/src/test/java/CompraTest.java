import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class CompraTest {
    private WebDriver firefoxDriver;
    @Before
    public void abrirDriver(){
        //encontrar el geckodriver
        System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
        //creando opciones sobre el navegador
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", false);
        //creando nueva instancia del navegador
        firefoxDriver = new FirefoxDriver(options);
        //creando un tiempo de espera para el caso de prueba
        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void hacerCompra() throws InterruptedException {
        //abrimos url
        firefoxDriver.get("http://automationpractice.com/index.php");

        //Creando el botón de Sign In
        WebElement btnSignIn = firefoxDriver.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
        btnSignIn.click();

        //Input de email por medio de css
        WebElement inputEmail = firefoxDriver.findElement(By.cssSelector("#email"));
        inputEmail.sendKeys("9806prueba@gmail.com");

        //Input de Password por medio de css
        WebElement inputPassword = firefoxDriver.findElement(By.cssSelector("#passwd"));
        inputPassword.sendKeys("NataliaPolo9806");

        //Botón de SignIn
        WebElement btnSignInCandado = firefoxDriver.findElement(By.cssSelector("#SubmitLogin"));
        btnSignInCandado.click();

        //Pestaña de T-shirts por medio de Xpath
        WebElement pestanaTshirts = firefoxDriver.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[3]/a[1]"));
        pestanaTshirts.click();

        //Crear acción
        Actions accion = new Actions(firefoxDriver);

        //Encontrar la imágen del producto
        WebElement imagenCompra = firefoxDriver.findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']"));
        accion.moveToElement(imagenCompra).moveToElement(firefoxDriver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"))).
                click().build().perform();

        //Boton de Compra Uno
        WebElement btnCompraUno = firefoxDriver.findElement(By.xpath("//span[contains(text(), 'Proceed to checkout')]"));
        btnCompraUno.click();

        //Boton de Compra Dos
        WebElement btnCompraDos = firefoxDriver.findElement(By.xpath("//p//*[contains(text(), 'Proceed to checkout')]"));
        btnCompraDos.click();

        //Boton de Compra Tres
        WebElement btnCompraTres = firefoxDriver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/form[1]/p[1]/button[1]/span[1]"));
        btnCompraTres.click();

        //CheckBox Envío
        WebElement checkBoxEnvio = firefoxDriver.findElement(By.xpath("//input[@id='cgv']"));
        checkBoxEnvio.click();

        //Boton de Compra Cuatro
        WebElement btnCompraCuatro = firefoxDriver.findElement(By.xpath("//button[@name='processCarrier']"));
        btnCompraCuatro.click();

        //BotonCheque
        WebElement btnCheque = firefoxDriver.findElement(By.cssSelector(".cheque"));
        btnCheque.click();

        //BotonConfirmarCompra
        WebElement btnConfirmarCompra = firefoxDriver.findElement(By.xpath("//p//button[@type='submit']"));
        btnConfirmarCompra.click();

        //Banner Orden Completa
        WebElement bannerOrdenCompleta = firefoxDriver.findElement(By.className("alert"));

        Assert.assertTrue(bannerOrdenCompleta.isDisplayed());

        Thread.sleep(40000);

    }
    @After
    public void cerrarDriver(){
        //cerrar el explorador
        firefoxDriver.quit();
    }
}
