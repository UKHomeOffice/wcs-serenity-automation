package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import config.Environments;
import config.Users;
import cucumber.api.java.en.Given;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs {

    @Managed
    WebDriver driver;

    @Steps(shared = true)
    NavigationStepDefs navigationStepDefs;

    LoginPage loginPage;

    Page page;

    @Given("^I login as \"([^\"]*)\"")
    public void iLoginas(String user) {
        navigateToNDelius();
        Serenity.setSessionVariable("user").to(user);
        switch (user.toUpperCase()) {
            case "DOM":
                enterHocsLoginDetails(Users.DOM);
                break;
            default:
                fail(user + " is not defined with LoginStepDefs.iLoginAs()");
        }
        page.clickSubmitButton();
    }

    private void enterHocsLoginDetails(Users user) {
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
    }

    private void navigateToNDelius() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'DEV'");
            baseUrl = Environments.DEV.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environments.DEV.getEnvironmentURL();
                    break;
                case "LOCAL":
                    baseUrl = Environments.LOCAL.getEnvironmentURL();
                    break;
                default:
                    fail(env + " is not defined within LoginStepDefs, navigateToNDelius method");
            }
        }
        driver.get(baseUrl);
    }

}
