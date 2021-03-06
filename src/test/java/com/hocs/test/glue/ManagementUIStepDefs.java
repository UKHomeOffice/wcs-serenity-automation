package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.give_me_a_case.Fetch;
import com.hocs.test.pages.managementUI.AddChildTopic;
import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.managementUI.LinkTopicToTeam;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.standard_line.StandardLine;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ManagementUIStepDefs extends Page {

    GenericInputStepDefs genericInputStepDefs;

    LoginStepDefs loginStepDefs;

    Fetch fetch;

    MarkUpDecision markUpDecision;

    Topics topics;

    Dashboard dashboard;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    AddChildTopic addChildTopic;

    StandardLine standardLine;

    LinkTopicToTeam linkTopicToTeam;

    @When("^I navigate to the \"([^\"]*)\" Management page$")
    public void navigateToSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "STANDARD LINE":
                clickOn(dashboard.addStandardLineButton);
                break;
            case "TEAM":
                clickOn(dashboard.addRemoveUsersButton);
                break;
            case "ADD CHILD TOPIC":
                clickOn(dashboard.addChildTopicButton);
                break;
            case "ADD A UNIT":
                clickOn(dashboard.addUnitButton);
                break;
            case "VIEW UNITS":
                clickOn(dashboard.viewUnitsButton);
                break;
            case "LINK TOPIC TO TEAM":
                clickOn(dashboard.linkTopicToTeamButton);
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @Then("^I should be taken to the \"([^\"]*)\" Management page$")
    public void assertThatTheUserIsTakenToTheSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "STANDARD LINE":
                standardLine.assertAddStandardLinePageTitle();
                break;
            case "TEAM":
                teamManagement.assertTeamManagementPageTitle();
                break;
            case "UNIT":
                unitManagement.assertUnitManagementPageTitle();
                break;
            case "ADD CHILD TOPIC":
                addChildTopic.assertAddChildTopicPageTitle();
                break;
            case "LINK TOPIC TO TEAM":
                linkTopicToTeam.assertLinkTopicToTeamPageTitle();
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @When("^I select the \"([^\"]*)\" team from the dropdown$")
    public void selectTeamFromDropdown(String teamName) {
        switch (teamName.toUpperCase()) {
            case "UK CENTRAL AUTHORITY":
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

    @And("^I add the users \"([^\"]*)\" and \"([^\"]*)\" to the team$")
    public void addTwoUsersToSelectedTeam(String firstUser, String secondUser) {
        waitABit(500);
        teamManagement.assertTeamName();
        teamManagement.clearTeamMembers();
        teamManagement.clearTeamMembers();
        switch (firstUser.toUpperCase()) {
            case "EAMON.DROKO@TEN10.COM":
                setSessionVariable("firstUser").to(firstUser);
                teamManagement.selectAUser(firstUser);
                break;
            default:
                pendingStep(firstUser + " is not defined within " + getMethodName());
        }

        switch (secondUser.toUpperCase()) {
            case "DANNY.LARGE@TEN10.COM":
                setSessionVariable("secondUser").to(secondUser);
                teamManagement.selectAUser(secondUser);
                break;
            default:
                pendingStep(secondUser + " is not defined within " + getMethodName());
        }
    }

    @Then("^the users should visible in the team list$")
    public void assertThatUsersAreBothVisibleInTeamList() {
        teamManagement.assertMultipleUsersAddedToTeam();
    }

    @And("^I click the Add Selected Users button$")
    public void clickTheAddSelectedUsersButtonWithoutSelectingUser() {
        teamManagement.clickAddSelectedUsers();
    }

    @Then("^an error message should be displayed as no users have been selected$")
    public void assertThatUserShouldBeDisplayedErrorMessageIsDisplayed() {
        teamManagement.assertSelectSomeUsersErrorMessage();
    }

    @Then("^an error message should be displayed as all Standard Line information has not been added$")
    public void assertThatAllStandardLineErrorMessagesAreDisplayed() {
        standardLine.assertStandardLineIsRequiredErrorMessage();
        standardLine.assertExpiryDateIsRequiredErrorMessage();
        standardLine.assertTopicIsRequiredErrorMessage();
    }


    @When("^I add a new Standard Line$")
    public void userAddsANewStandardLine() {
        standardLine.enterStandardLineTopic();
        standardLine.addStandardLineDocument();
        standardLine.enterStandardLineExpirationDate();
        clickOn(unitManagement.submitButton);
    }

    @Then("^the Standard Line should be added to the selected topic$")
    public void assertThatStandardLineHasBeenAdded() {
        standardLine.assertStandardLineSuccessMessage();
    }

    @When("^I enter a Standard Line expiration date in the past$")
    public void enterPastStandardLineExpirationDate() {
        standardLine.enterStandardLineTopic();
        standardLine.addStandardLineDocument();
        standardLine.enterPastStandardLineExpirationDate();
        clickOn(unitManagement.submitButton);
    }

    @Then("^an error message should be displayed as the expiration date must be in the future$")
    public void assertThatExpirationDateMustBeInFutureErrorMessageIsDisplayed() {
        standardLine.assertDateMustBeInFutureErrorMessage();
=======
    @And("^I select a topic that \"([^\"]*)\" have linked teams$")
    public void iSelectATopicThatHaveLinkedTeams(String topicState) {
        switch (topicState.toUpperCase()) {
            case "DOES":
                linkTopicToTeam.selectATopic("Register of faith leaders");
                break;
            case "DOES NOT":
                linkTopicToTeam.selectATopic(sessionVariableCalled("newChildTopic").toString());
                break;
            default:
                pendingStep(topicState + " is not defined within " + getMethodName());
        }
    }

    @Then("^an error message should be displayed as no topic has been selected$")
    public void anErrorMessageShouldBeDisplayedAsNoTopicHasBeenSelected() {
        linkTopicToTeam.assertTopicIsRequiredErrorMessage();
    }

    @Given("^I have created a new child topic$")
    public void iHaveCreatedANewChildTopic() {
        navigateToSelectedManagementPage("ADD CHILD TOPIC");
        addChildTopic.selectAParentTopic("Police Website");
        addChildTopic.inputNewChildTopic();
        genericInputStepDefs.clickTheButton("SUBMIT");
    }

    @And("^I select a \"([^\"]*)\" team$")
    public void iSelectATeam(String typeOfTeam) {
        switch (typeOfTeam.toUpperCase()) {
            case "INITIAL DRAFT AND QA RESPONSE STAGES":
                linkTopicToTeam.selectADraftAndQATeam("Advice Team");
                break;
            case "PRIVATE OFFICE/MINISTER SIGN OFF STAGES":
                linkTopicToTeam.selectAPrivateAndMinisterTeam("Permanent Secretary");
                break;
            default:
                pendingStep(typeOfTeam + " is not defined within " + getMethodName());
        }
    }

    @Then("^an error message should be displayed as no \"([^\"]*)\" team has been selected$")
    public void anErrorMessageShouldBeDisplayedAsNoTeamHasBeenSelected(String typeOfTeam) {
        switch (typeOfTeam.toUpperCase()) {
            case "INITIAL DRAFT AND QA RESPONSE STAGES":
                linkTopicToTeam.assertDraftandQATeamIsRequiredErrorMessage();
                break;
            case "PRIVATE OFFICE/MINISTER SIGN OFF STAGES":
                linkTopicToTeam.assertPrivateAndMinisterTeamIsRequiredErrorMessage();
                break;
            default:
                pendingStep(typeOfTeam + " is not defined within " + getMethodName());
        }

    }

    @Then("^the summary should correctly detail the topic and the teams chosen to link to it$")
    public void theSummaryShouldCorrectlyDetailTheTopicAndTheTeamsChosenToLinkToIt() {
        linkTopicToTeam.assertSummaryDisplaysSelectedTopicAndTeams();
    }

    @Given("^I have linked teams to the new child topic$")
    public void iHaveLinkedTeamsToTheNewChildTopic() {
        navigateToSelectedManagementPage("LINK TOPIC TO TEAM");
        iSelectATopicThatHaveLinkedTeams("DOES NOT");
        genericInputStepDefs.clickTheButton("SUBMIT");
        iSelectATeam("INITIAL DRAFT AND QA RESPONSE STAGES");
        iSelectATeam("PRIVATE OFFICE/MINISTER SIGN OFF STAGES");
        genericInputStepDefs.clickTheButton("SUBMIT");
        genericInputStepDefs.clickTheButton("SUBMIT");
    }

    @And("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String site) {
        switch (site.toUpperCase()) {
            case "HOCS":
                loginStepDefs.navigateToHocs();
                break;
            case "MANAGEMENT UI":
                loginStepDefs.navigateToManagementUI();
                break;
            default:
                pendingStep(site + " is not defined within " + getMethodName());
        }

    }


    @And("^I discover the current default team links for a topic$")
    public void iDiscoverTheCurrentDefaultTeamLinksForATopic() {
        fetch.giveMeACase("DCU MIN", "MARKUP");
        markUpDecision.getToMarkupAddATopicScreenPrerequisites();
        topics.enterATopic("Register of faith leaders");
        topics.getCurrentDefaultTeamsForTopic();
    }

    @And("^I select to amend the team links for the topic$")
    public void iSelectToAmendTheTeamLinksForTheTopic() {
        navigateToSelectedManagementPage("LINK TOPIC TO TEAM");
        iSelectATopicThatHaveLinkedTeams("DOES");
        genericInputStepDefs.clickTheButton("SUBMIT");
    }

    @And("^I select a different \"([^\"]*)\" team$")
    public void iSelectADifferentTeam(String typeOfTeam) {
        switch (typeOfTeam.toUpperCase()) {
            case "INITIAL DRAFT AND QA RESPONSE STAGES":
                switch (sessionVariableCalled("defaultDraftTeam").toString()) {
                    case "Advice Team":
                        linkTopicToTeam.selectADraftAndQATeam("North Region");
                        break;
                    case "North Region":
                        linkTopicToTeam.selectADraftAndQATeam("Advice Team");
                        break;
                    default:
                        pendingStep(sessionVariableCalled("defaultDraftTeam").toString() + " is not defined within "
                                + getMethodName());
                }
                break;
            case "PRIVATE OFFICE/MINISTER SIGN OFF STAGES":
                switch (sessionVariableCalled("defaultPrivateOfficeTeam").toString()) {
                    case "Minister for Lords":
                        linkTopicToTeam.selectAPrivateAndMinisterTeam("Permanent Secretary");
                        break;
                    case "Permanent Secretary":
                        linkTopicToTeam.selectAPrivateAndMinisterTeam("Minister for Lords");
                        break;
                    default:
                        pendingStep(sessionVariableCalled("defaultPrivateOfficeTeam") + " is not defined within "
                                + getMethodName());
                }
                break;
            default:
                pendingStep(typeOfTeam + " is not defined within " + getMethodName());
        }
    }

    @When("^I check the default team links in HOCS again$")
    public void iCheckTheDefaultTeamLinksInHOCSAgain() {
        iNavigateTo("HOCS");
        fetch.giveMeACase("DCU MIN", "MARKUP");
        markUpDecision.getToMarkupAddATopicScreenPrerequisites();
        topics.enterATopic("Register of faith leaders");
    }

    @When("^I enter a display name$")
    public void iEnterADisplayName() {
        addChildTopic.inputNewChildTopic();
    }

    @When("^I select a parent topic$")
    public void iSelectAParentTopic() {
        addChildTopic.selectAParentTopic("Specific Cases");
    }

    @Then("^I am returned to the dashboard screen$")
    public void iAmReturnedToTheDashboardScreen() {
        dashboard.assertElementIsDisplayed(dashboard.subheading);
    }

    @Then("^an error message should be displayed as no parent topic has been selected$")
    public void anErrorMessageShouldBeDisplayedAsNoParentTopicHasBeenSelected() {
        addChildTopic.assertParentTopicIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as no display name has been entered$")
    public void anErrorMessageShouldBeDisplayedAsNoDisplayNameHasBeenEntered() {
        addChildTopic.assertDisplayNameIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed stating that topic already exists$")
    public void anErrorMessageShouldBeDisplayedStatingThatTopicAlreadyExists() {
        addChildTopic.assertDuplicateTopicErrorMessage();
    }

    @And("^I enter a parent topic and display name that duplicate an existing child topic$")
    public void iEnterAParentTopicAndDisplayNameThatDuplicateAnExistingChildTopic() {
        addChildTopic.selectAParentTopic("Biometrics");
        addChildTopic.inputAChildTopicDisplayName("TEST TOPIC");
    }

    @And("^I select a different parent topic$")
    public void iSelectADifferentParentTopic() {
        addChildTopic.selectAParentTopic("Biometrics");
    }

    @And("^I enter the same display name$")
    public void iEnterTheSameDisplayName() {
        addChildTopic.inputAChildTopicDisplayName(sessionVariableCalled("newChildTopic").toString());
    }

    @And("^I get a case and progress to the point of adding a topic$")
    public void iCreateACaseAndProgressToThePointOfAddingATopic() {
        fetch.giveMeACase("DCU MIN", "MARKUP");
        markUpDecision.getToMarkupAddATopicScreenPrerequisites();
    }

    @Then("^an error message should be displayed as the topic was not recognised as a valid topic$")
    public void anErrorMessageShouldBeDisplayedAsTheTopicWasNotRecognisedAsAValidTopic() {
        markUpDecision.assertTopicIsRequiredErrorMessage();
    }

    @And("^a success message is displayed$")
    public void aSuccessMessageIsDisplayed() {
        dashboard.assertSuccessMessageDisplayed();
    }

    @When("^I add a new Standard Line$")
    public void userAddsANewStandardLine() {
        standardLine.enterStandardLineTopic();
        standardLine.addStandardLineDocument();
        standardLine.enterStandardLineExpirationDate();
        clickOn(unitManagement.submitButton);
    }

    }

    @When("^I enter a Standard Line expiration date in the past$")
    public void enterPastStandardLineExpirationDate() {
        standardLine.enterStandardLineTopic();
        standardLine.addStandardLineDocument();
        standardLine.enterPastStandardLineExpirationDate();
        clickOn(unitManagement.submitButton);
    }

    @Then("^an error message should be displayed as the expiration date must be in the future$")
    public void assertThatExpirationDateMustBeInFutureErrorMessageIsDisplayed() {
        standardLine.assertDateMustBeInFutureErrorMessage();
    }
}

