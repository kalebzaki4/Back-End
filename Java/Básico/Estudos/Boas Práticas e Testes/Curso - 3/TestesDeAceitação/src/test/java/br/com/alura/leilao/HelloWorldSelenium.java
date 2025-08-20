import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // Importe esta classe

public class HelloWorldSelenium {

    private WebDriver browser;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kalebzaki\\Documents\\GitHub\\Back-End\\Java\\Básico\\Estudos\\Boas Práticas e Testes\\Curso - 3\\TestesDeAceitação\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        this.browser = new ChromeDriver(options);
    }

    @Test
    public void helloTest() {
        this.browser.navigate().to("http://localhost:8080/leiloes");
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }
}