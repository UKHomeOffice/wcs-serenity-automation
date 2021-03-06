package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.When;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.core.Serenity.setSessionVariable;

public class TeamManagement extends Page {

    @FindBy(xpath = "//input[@id='Teams-input']")
    public WebElementFacade teamSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade viewTeamButton;

    @FindBy(xpath = "//button[@class='govuk-button add-team-members-button']")
    public WebElementFacade addTeamMembersButton;

    @FindBy(xpath = "//input[@id='users-input']")
    public WebElementFacade userSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade addSelectedUsersButton;

    @FindBy(xpath = "//h2[@class='govuk-heading-l']")
    public WebElementFacade teamNameHeader;

    @FindBy(xpath = "(//td//a)[1]")
    public WebElementFacade firstRemoveButtonInList;

    @FindBy(xpath = "(//div//p)[2]")
    public WebElementFacade errorMessage;

    public void assertTeamManagementPageTitle() {
        assertThat(managementUIPageTitle.getText(), is("Team search"));
    }

    public void selectATeam(String teamName) {
        waitABit(500);
        typeInto(teamSearchBar, teamName);
        setSessionVariable("teamName").to(teamName);
        teamSearchBar.sendKeys(Keys.ENTER);
        clickOn(viewTeamButton);
    }

    public void selectTeamWithNoUsers() {
        waitABit(500);
        typeInto(teamSearchBar, "Data & Identity Unit");
        teamSearchBar.sendKeys(Keys.ENTER);
        clickOn(viewTeamButton);
    }

    public void selectAUser(String nameOfUser) {
        clickOn(addTeamMembersButton);
        userSearchBar.sendKeys(nameOfUser);
        setSessionVariable("nameOfUser").to(nameOfUser);
        waitABit(4000);
        userSearchBar.sendKeys(Keys.ENTER);
        clickOn(addSelectedUsersButton);
    }

    public void clickAddSelectedUsers() {
        clickOn(addTeamMembersButton);
        clickOn(addSelectedUsersButton);
    }

    public void removeUserFromTeamWithAssignedCases() {
        String nameOfUser = sessionVariableCalled("nameOfUserWithCases").toString();
        WebElementFacade removeButtonOfUser = findAll("//td[@class='govuk-table__cell'][contains(text(), '" + nameOfUser +
                "')]/."
                + ".//td//a").get(0);
        clickOn(removeButtonOfUser);
    }

    public void assertTeamName() {
        assertThat(teamNameHeader.getText(), is("Team: " + sessionVariableCalled("teamName")));
    }

    public void assertThatUserIsVisibleInTeamList() {
        WebElementFacade membersInTeamTable = findAll("(//tr[@class='govuk-table__row'])[2]").get(0);

        String nameOfTeamInHeader = sessionVariableCalled("teamName").toString();
        String nameOfNewUser = sessionVariableCalled("nameOfUser").toString();

        assertThat(teamNameHeader.getText(), containsText(nameOfTeamInHeader));
        assertThat(membersInTeamTable.getText(), containsText(nameOfNewUser));
    }

    public void removeFirstUserInListAndStoreName() {
        WebElementFacade firstMemberInTeamTable = findBy("(//td[@class='govuk-table__cell'])[1]");
        String nameAndEmailOfFirstUser = firstMemberInTeamTable.getText();
        setSessionVariable("userNameAndEmail").to(nameAndEmailOfFirstUser);
        clickOn(firstRemoveButtonInList);
    }

    public void clearTeamMembers() {
        WebElementFacade removeButton = $("//td//a");
        if (isElementDisplayed($(removeButton))) {
            clickOn(removeButton);
        }
    }

    public void assertThatRemovedUserIsNoLongerVisibleInList() {
        waitABit(500);
        String removedUser = sessionVariableCalled("userNameAndEmail").toString();
        assertThat($("//body").getText(), containsText(removedUser), is(false));
    }

    public void assertThatTeamContainsNoUsers() {
        waitABit(500);
        assertThat(isElementDisplayed(firstRemoveButtonInList), is(false));
    }

    public void assertUserHasCasesErrorMessage() {
        assertThat(errorMessage.getText(), is("The user cannot be removed from the team as they have cases assigned"));
    }

    public void assertSelectATeamErrorMessage() {
        assertThat(errorMessage.getText(), is("Please select a team before submitting."));
    }

    public void assertMultipleUsersAddedToTeam() {
        waitABit(500);
        String firstAddedUser = sessionVariableCalled("firstUser").toString();
        String secondAddedUser = sessionVariableCalled("secondUser").toString();

        assertThat($("//table[@class='govuk-table']").getText(), containsText(firstAddedUser));
        assertThat($("//table[@class='govuk-table']").getText(), containsText(secondAddedUser));
    }

    public void assertSelectSomeUsersErrorMessage() {
        assertThat(errorMessage.getText(), is("Please select some users before submitting."));
    }
}
