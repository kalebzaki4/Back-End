package br.com.alura.leilao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPage {
    private static final String LOGIN = "http://localhost:8080/login";
    private WebDriver browser;


    @BeforeAll
    public static void BeforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kalebzaki\\Documents\\GitHub\\Back-End\\Java\\Básico\\Estudos\\Boas Práticas e Testes\\Curso - 3\\TestesDeAceitação\\drivers\\chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver(new ChromeOptions().setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"));
        browser.navigate().to("http://localhost:8080/login");
    }

    @AfterEach
    public void AfterEach() {
        this.browser.quit();
    }
}
