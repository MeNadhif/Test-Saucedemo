package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Halaman Login saucedemo")
    public void halaman_login_saucedemo() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // Set Timeout for web driver on witing element
        driver.manage().window().maximize(); // Maximize Window
        driver.get(baseUrl);

        // Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("User input (.*) as email$")
    public void user_input_failed_loginn_gmail_com_as_email(String email) {
        driver.findElement(By.id("user-name")).sendKeys(email);
    }

    @And("User input (.*) as password$")
    public void user_input_secret_sauce_as_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("Click login button")
    public void click_login_button() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User verify (.*) login result$")
    public void user_verify_failed_login_result(String status) {
        if(status.equals("success")){ // Jika Success
            driver.findElement(By.xpath("//div[contains(text(), 'dashboard')]"));
            String header = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div")).getText();
            Assert.assertEquals(header, "Swag Labs");
        }else { // Jika Gagal
            String ErrorLogin = driver. findElement(By.xpath("//h3[@data-test='error']")).getText();
            Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");
        }
        driver.close();
    }
}
