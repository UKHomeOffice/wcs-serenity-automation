package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.managementUI.ChildTopic;
import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ManagementUIStepDefs extends Page {

    Dashboard dashboard;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    ChildTopic childTopic;

    @When("^I navigate to the \"([^\"]*)\" Management page$")
    public void navigateToSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "TEAM":
                clickOn(dashboard.addRemoveUsersButton);
                break;
            case "CHILD TOPIC":
                clickOn(dashboard.addChildTopicButton);
                break;
            case "UNIT":
                clickOn(dashboard.addUnitButton);
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @Then("^I should be taken to the \"([^\"]*)\" Management page$")
    public void assertThatTheUserIsTakenToTheSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "TEAM":
                teamManagement.assertTeamManagementPageTitle();
                break;
            case "CHILD TOPIC":
                childTopic.assertChildTopicPageTitle();
                break;
            case "UNIT":
                unitManagement.assertUnitManagementPageTitle();
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @When("^I select the \"([^\"]*)\" team from the dropdown$")
    public void selectTeamFromDropdown(String teamName) {
        switch (teamName.toUpperCase()) {
            case "PURSUE DISRUPTIONS UNIT":
                teamManagement.selectATeam(teamName);
                break;
            case "OSCT SECRETARIAT":
                teamManagement.selectATeam(teamName);
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                teamManagement.selectATeam(teamName);
                break;
            default:
                pendingStep(teamName + " is not defined within " + getMethodName());
        }
    }

    @And("^I add the user \"([^\"]*)\" to the team$")
    public void addUserToSelectedTeam(String nameOfUser) {
        waitABit(500);
        teamManagement.assertTeamName();
        switch (nameOfUser.toUpperCase()) {
            case "EAMON.DROKO@TEN10.COM":
                teamManagement.selectAUser(nameOfUser);
                break;
            case "DANNY.LARGE@TEN10.COM":
                teamManagement.selectAUser(nameOfUser);
                break;
            default:
                pendingStep(nameOfUser + " is not defined within " + getMethodName());
        }
    }

    @Then("^the user should be visible in the team list$")
    public void assertThatUserIsVisibleInTeamList() {
        teamManagement.assertThatUserIsVisibleInTeamList();
    }

    @And("^I remove a user from the team$")
    public void removeUserFromTeamAndStoreUserName() {
        teamManagement.removeFirstUserInListAndStoreName();
    }

    @Then("^that user should no longer appear in the list of team members$")
    public void assertThatUserHasBeenRemovedFromTeam() {
        teamManagement.assertThatRemovedUserIsNoLongerVisibleInList();
    }

    @When("^I search for a team with no assigned users$")
    public void navigateToTeamWithNoAssignedUsers() {
        teamManagement.selectTeamWithNoUsers();
    }

    @Then("^no users should be shown in user list$")
    public void assertTeamHasNoUsers() {
        teamManagement.assertThatTeamContainsNoUsers();
    }

    @And("^I attempt to remove the user \"([^\"]*)\"$")
    public void attemptRemoveUserFromTeamWithAssignedCases(String nameOfUserWithCases) {
        setSessionVariable("nameOfUserWithCases").to(nameOfUserWithCases);
        switch (nameOfUserWithCases.toUpperCase()) {
            case "EAMON.DROKO@TEN10.COM":
                teamManagement.removeUserFromTeamWithAssignedCases();
                break;
            default:
                pendingStep(nameOfUserWithCases + " is not defined within " + getMethodName());
        }
    }

    @Then("^an error message should be displayed as they have cases assigned in that team$")
    public void assertThatCasesAssignedErrorMessageIsDisplayed() {
        teamManagement.assertUserHasCasesErrorMessage();
    }

    @Then("^an error message should displayed as no team been selected$")
    public void assertThatSelectTeamErrorMessageIsDisplayed() {
        teamManagement.assertSelectATeamErrorMessage();
    }

    @Then("^an error message should be displayed as they have not entered a display name and short code$")
    public void assertThatDisplayNameAndShortCodeErrorMessagesAreDisplayed() {
        unitManagement.assertDisplayNameAndShortCodeErrorMessages();
    }
}

