package com.hocs.test.glue;

import static config.Users.*;
import static config.Usernames.*;
import static config.Passwords.*;
import static jnr.posix.util.MethodName.getMethodName;
import static junit.framework.TestCase.fail;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;

import config.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;
import static net.serenitybdd.core.Serenity.setSessionVariable;

// Workstacks page link under homepage.java as workstacksLink


public class WorkstacksStepDefs {

    @Managed
    private
    WebDriver driver; // set webdriver to var driver


    @Steps(shared = true)
    NavigationStepDefs navigationStepDefs;

    private LoginPage loginpage; //require loginpage file features not sure why this is under @steps instead of @managed

    private Page page; //more pages

    Homepage homepage;

    Workstacks workstacks;

    @When("^The current user allocates the case to themself$")
    public void allocateToMe() {
        homepage.firstStageFindMyCase();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();

    }

    @When("^They unallocate the case from themself")
    public void unallocateCase() {
        homepage.selectMyCases();
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.clickUnallocateCasesButton();

    }

    @When("^They select the check box against the case$")
    public void selectCaseCheckbox() {
        homepage.firstStageFindMyCase();
        workstacks.clickCheckboxRelevantToCaseReference();
    }


    @Then("^The case is added to the current user's cases$")
    public void assertThatCaseIsAllocatedToMe() {
        homepage.selectMyCases();
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("^The case is not visible in the user's cases$")
    public void assertThatCaseHasBeenUnallocatedFromMe() {
        workstacks.assertCaseReferenceIsNotVisible();

    }

    @When("^They enter Case Reference type \"([^\"]*)\" into the filter$")
    public void enterCaseReferenceType(String caseReferenceType) {
        workstacks.selectWorkstackFilter.click();
        switch (caseReferenceType.toUpperCase()) {
            case "MIN":
                workstacks.selectWorkstackFilter.sendKeys(caseReferenceType);
                break;
            case "DTEN":
                workstacks.selectWorkstackFilter.sendKeys(caseReferenceType);
                break;
            case "TRO":
                workstacks.selectWorkstackFilter.sendKeys(caseReferenceType);
                break;
            default: System.out.println(caseReferenceType
                    + " is not defined within " + getClass().getSimpleName()
                    + " class, " + getMethodName() + " method");
                caseReferenceType = null;
                assumeNotNull(caseReferenceType);
        }
    }

    @Then("^The cases are filtered by the chosen Case Reference$")
    public void assertCasesAreFilteredByCaseReference(){

    }




   /* @When("^I view my workstacks$")
    //could belong in navigateStepDefs
    public void navigateToWorkstacks() {
        navToWorkstacks();
    } */


   /* @And("^My team has 0 cases$")
    //should be an assert not a step.
    public void isThisEvenAStep() {
        doesntDoAnything();
    } */


    @And("^assign 1 to another team member$")
    public void assignToTeamMember() {
        // make integer variable so can assign X number based on requirements for test
        Workstacks.assignCaseToTeamMember();
    }

//    @And("^I assign X to me$")
//    public void assignCaseToMe() {
//        //as above make the amount of cases not static
//        Workstacks.assignCaseToMyself();
//    }

    @And("^I do not see 'thisTeam'$")
    public void assertNoTeamWhenNoCasesForTeam() {
        Workstacks.assertThatCaselessTeamNotVisible();
    }


    @And("^I see a X value for assigned to me$")
    public void assertXCasesAssignedToMe() {
        Workstacks.assertThatCasesAssignedToMe();
    }


    @And("^I see a Z value for unassigned$")
    public void assertNumOfUnassignedCases() {
        Workstacks.assertThatXCasesAreUnassigned();
    }


    @And("^assign 'assigned' 'team A' to another team member$")
    public void assignXCasesToAnotherTeamMember() {
        Workstacks.assignMultipleTeamSpecificCasesToTeammate();
    }


    @And("^assign 'assigned' 'thisTeam' cases to me$")
    public void assignXCasesToMe() {
        Workstacks.assignTeamSpecificCasesToMe();
    }

    //Asserts @Then & @And
    //Are @And Asserts okay or will they be dodgy, use * instead


    @And("^I see 'thisTeam' has X cases total$")
    public void assertTeamHasXTotalCases() {
        Workstacks.assertThatTeamHasXTotalCases();
    }
    // Not linking to ft file for some reason


    @And("^I see 'thisTeam' has X unassigned case$")
    public void assertTeamHasXUnassignedCases() {
        Workstacks.assertThatTeamHasXUnassignedCases();
    }


    @And("^I see 1 of my cases is overdue$")
    public void myOverdueCases() {
        Workstacks.assertOverdueCases();
    }


    @And("^I see 1 of my cases is priority$")
    public void myPriorityCases() {
        Workstacks.assertThatMyCaseIsPriority();
    }


    @And("^A case not assigned to me is priority$")
    //should almost certainly be a @then
    public void unassignedCaseIsPriority() {
        Workstacks.assertUnassignedCaseIsPriority();
    }


    /*@Then("^I see a sign up message$")
    public void noTeamsSignUpMessage() {
        errorPage.assertNoTeamsSignUpMessage();
    } */


    @Then("^I see a X value for 'thisTeam'$")
    public void teamHasNoCases() {
        // if this is A team has X cases then dont need a new step for 5/3/1 scenarios
        Workstacks.assertTeamHasNoCases();
    }


    @Then("^I see that I have X cases assigned to me")
    public void assertXCasesAreAssignedToMe() {
        Workstacks.assertThatXCasesAreAssignedToMe();
    }


}
