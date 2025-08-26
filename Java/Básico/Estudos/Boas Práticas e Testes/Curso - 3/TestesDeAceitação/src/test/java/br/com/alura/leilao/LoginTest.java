package br.com.alura.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @Test
    public void deveFazerLoginComSucesso() {

        browser.findElement(By.id("usermane")).sendKeys("Fulano da Silva");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals(LOGIN));
        Assert.assertEquals("Fulano da Silva", browser.findElement(By.id("usuario-logado")).getText());
    }

    public void deveriaExibirMensagemDeErroParaUsuarioInvalido() {
        browser.findElement(By.id("usermane")).sendKeys("Invalido");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assert.assertThrows( NotFoundException.class , () -> {;
            browser.findElement(By.id("usuario-logado"));
        });
    }

    public void naoDeveriaAcessarPaginaRestirtaSemEstarLogado() {
        browser.navigate().to("http://localhost:8080/leiloes/2");

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assert.assertThrows( NotFoundException.class , () -> {;
            browser.findElement(By.id("usuario-logado"));
        });
    }
}
