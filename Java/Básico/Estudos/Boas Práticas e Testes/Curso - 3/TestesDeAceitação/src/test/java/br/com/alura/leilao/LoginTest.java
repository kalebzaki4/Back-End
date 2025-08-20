package br.com.alura.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
    @Test
    public void deveFazerLoginComSucesso() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kalebzaki\\Documents\\GitHub\\Back-End\\Java\\Básico\\Estudos\\Boas Práticas e Testes\\Curso - 3\\TestesDeAceitação\\drivers\\chromedriver.exe");
        WebDriver browser = new ChromeDriver(new ChromeOptions().setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"));
        browser.navigate().to("http://localhost:8080/login");

        browser.findElement(By.id("usermane")).sendKeys("Fulano da Silva");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assert.assertEquals("Fulano da Silva", browser.findElement(By.id("usuario-logado")).getText());
    }
}
