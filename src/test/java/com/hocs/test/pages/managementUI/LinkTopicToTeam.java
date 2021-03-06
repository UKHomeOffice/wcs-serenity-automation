package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class LinkTopicToTeam extends Page {

    @FindBy(xpath = "//input[@id='topics-input']")
    public WebElementFacade topicsSearchBar;

    @FindBy(xpath = "//input[@id='draft-qa-input']")
    public WebElementFacade draftAndQATeamSearchBar;

    @FindBy(xpath = "//input[@id='private-minister-input']")
    public WebElementFacade privateAndMinisterTeamSearchBar;

    @FindBy(css = ".govuk-table__body tr:nth-child(1) td")
    public WebElementFacade draftQaTeamAssignment;

    @FindBy(css = ".govuk-table__body tr:nth-child(2) td")
    public WebElementFacade privateMinisterTeamAssignment;

    @FindBy(css = ".govuk-error-summary__body")
    public WebElementFacade errorMessageContents;

    @FindBy(css = ".govuk-heading-l")
    public WebElementFacade topicInSummary;

    public void selectATopic(String topic) {
        waitABit(1000);
        clickOn(topicsSearchBar);
        typeInto(topicsSearchBar, topic);
        setSessionVariable("chosenTopic").to(topic);
        waitABit(1000);
        topicsSearchBar.sendKeys(Keys.ENTER);
    }

    public void selectADraftAndQATeam(String team) {
        waitABit(1000);
        clickOn(draftAndQATeamSearchBar);
        draftAndQATeamSearchBar.sendKeys(team);
        setSessionVariable("chosenDraftAndQATeam").to(team);
        waitABit(1000);
        draftAndQATeamSearchBar.sendKeys(Keys.ENTER);
    }

    public void selectAPrivateAndMinisterTeam(String team) {
        waitABit(1000);
        clickOn(privateAndMinisterTeamSearchBar);
        privateAndMinisterTeamSearchBar.sendKeys(team);
        setSessionVariable("chosenPrivateAndMinisterTeam").to(team);
        waitABit(1000);
        privateAndMinisterTeamSearchBar.sendKeys(Keys.ENTER);
    }

    public void assertSummaryDisplaysSelectedTopicAndTeams() {

        waitABit(2000);
        assertThat(topicInSummary.getText(), containsText(sessionVariableCalled("chosenTopic").toString()));
        assertThat(draftQaTeamAssignment.getText(), is(sessionVariableCalled("chosenDraftAndQATeam").toString()));
        assertThat(privateMinisterTeamAssignment.getText(),
                is(sessionVariableCalled("chosenPrivateAndMinisterTeam").toString()));
    }

    public void assertLinkTopicToTeamPageTitle() {
        assertThat(managementUIPageTitle.getText(), is("Topic search"));
    }

    public void assertTopicIsRequiredErrorMessage() {
        assertThat(errorMessageContents.getText(), is("Select a topic before submitting."));
    }

    public void assertDraftandQATeamIsRequiredErrorMessage() {
        assertThat(errorMessageContents.getText(), is("The team for Initial Draft and QA response stages is required"));
    }

    public void assertPrivateAndMinisterTeamIsRequiredErrorMessage() {
        assertThat(errorMessageContents.getText(),
                is("The team for Private Office and Minister sign off stages is required"));
    }
}
