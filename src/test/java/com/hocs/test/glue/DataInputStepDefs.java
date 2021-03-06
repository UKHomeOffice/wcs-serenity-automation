package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.accordion.CaseDetailsAccordion;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInputQADecision;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DataInputStepDefs extends Page {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    Homepage homepage;

    DataInputQADecision dataInputQADecision;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    CaseDetailsAccordion caseDetailsAccordion;

    @When("^I complete the Data Input stage$")
    public void completeDataInputStage() {
        dataInput.dataInputFullFlow();
    }

    @When("^I complete the Data Input stage and send a copy to Number Ten$")
    public void completeDataInputStageWCopyToN10() {
        dataInput.dataInputFullFlowWithCopyToN10();
    }

    @When("^the Data Input Stage is completed for \"([^\"]*)\" caseType$")
    public void completeDataInputPerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                dataInput.dataInputFullFlowMIN();
                break;
            case "DCU N10":
                dataInput.dataInputFullFlowDTEN();
                break;
            case "DCU TRO":
                dataInput.dataInputFullFlowTRO();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {
        addACorrespondentThatIsOrIsNotAnMP("Is not");
        recordCorrespondentDetails.fillMandatoryCorrespondentFieldsForSecondaryContact();
        dataInput.clickAddButton();
    }

    @When("^I select a Data Input QA decision of \"([^\"]*)\"$")
    public void iSelectADataInputQADecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "ACCEPT":
                dataInputQADecision.acceptDataInputQa();
                break;
            case "REJECT":
                dataInputQADecision.rejectDataInputQa();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        clickOn(dataInput.finishButton);
    }

    @When("^I do not select a Data Input QA response$")
    public void iDoNotSelectADataInputQAResponse() {
        clickOn(dataInput.finishButton);
    }

    @And("^I set the correspondence channel to \"([^\"]*)\"$")
    public void iSetTheCorrespondenceChannelTo(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                clickOn(dataInput.emailOriginalChannelRadioButton);
                break;
            case "POST":
                clickOn(dataInput.postOriginalChannelRadioButton);
                break;
            case "PHONE":
                clickOn(dataInput.phoneOriginalChannelRadioButton);
                break;
            case "NO. 10":
                clickOn(dataInput.numberTenOriginalChannelRadioButton);
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
    }

    @When("^I select to add a correspondent that \"([^\"]*)\" a member of parliament$")
    public void addACorrespondentThatIsOrIsNotAnMP(String isOrIsNot) {
        waitABit(2000);
        dataInput.selectAddACorrespondentLink();
        if (isOrIsNot.toUpperCase().equals("IS")) {
            dataInput.selectCorrespondentIsAMemberRadioButton();
        } else if (isOrIsNot.toUpperCase().equals("IS NOT")) {
            dataInput.selectCorrespondentIsNotAMemberRadioButton();
        }
        clickOn(dataInput.continueButton);
    }

    @When("^they complete the first data input screen$")
    public void completeFirstDataInputScreen() {
        dataInput.completeTheFirstDataInputFields();
    }

    @Then("^an error message should be displayed as I have not \"([^\"]*)\"$")
    public void assertReasonForErrorMessage(String reason) {
        switch (reason.toUpperCase()) {
            case "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX":
                workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
                break;
            case "ENTERED TEXT INTO THE FULL NAME FIELD":
                dataInput.assertCorrespondentFullNameErrorMessage();
                break;
            case "SELECTED THE CORRESPONDENCE TYPE":
                dataInput.assertCorrespondentTypeDropDownErrorMessage();
                break;
            case "SELECTED A MEMBER OF PARLIAMENT":
                dataInput.assertMemberIsRequiredErrorMessage();
                break;
            case "SELECTED THIS CORRESPONDENCE TYPE":
                dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
                break;
            case "ADDED A PRIMARY CORRESPONDENT":
                dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
                break;
            default:
                pendingStep(reason + " is not defined within " + getMethodName());
        }
    }

    @Then("^an error message should be displayed as I have not added any text into the case note text box$")
    public void assertThatCaseNoteMustNotBeBlankErrorMessageIsShown() {
        workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        dataInput.assertCorrespondentFullNameErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected the correspondent type$")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("^an error message should be displayed as I must select a member of parliament from the drop down$")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        dataInput.assertMemberIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I must select a correspondent type on this screen$")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("^they should be added to the list of correspondents$")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        recordCorrespondentDetails.assertPrimaryCorrespondent();
    }

    @Then("^an error message should be displayed as I have not added a primary correspondent$")
    public void assertThatWhichIsPrimaryCorrespondentErrorMessageIsDisplayed() {
        dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered a \"([^\"]*)\"$")
    public void assertValidationMessagesOnDataInputForm(String field) {
        switch (field.toUpperCase()) {
            case "CORRESPONDENCE DATE":
                dataInput.assertCorrespondenceDateErrorMessage();
                break;
            case "CORRESPONDENCE TYPE":
                dataInput.assertHowWasCorrespondenceReceivedErrorMessage();
                break;
            case "COPY TO NUMBER TEN":
                dataInput.assertShouldResponseBeCopiedN10ErrorMessage();
                break;
            default:
                pendingStep(field + " is not defined within " + getMethodName());
        }
    }

    @Then("^the correspondence type is the \"([^\"]*)\" correspondent$")
    public void theCorrespondenceTypeIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                recordCorrespondentDetails.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                recordCorrespondentDetails.assertSecondaryCorrespondent();
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @And("^a case has a \"([^\"]*)\" correspondent$")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                setSessionVariable("caseReference").to(getHeaderText().substring(10));
                dataInput.fillAllMandatoryCorrespondenceFields();
                genericInputStepDefs.clickTheButton("Continue");
                addACorrespondentThatIsOrIsNotAnMP("Is not");
                recordCorrespondentDetails.fillMandatoryCorrespondentFields();
                dataInput.clickAddButton();
                recordCorrespondentDetails.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                aCaseHasACorrespondent("PRIMARY");
                iAddAnAdditionalCorrespondent();
                recordCorrespondentDetails.assertSecondaryCorrespondent();
                break;
            default:
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }
    }

    @When("^I enter an invalid date$")
    public void enterAnInvalidDate() {
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
    }

    @When("^they do not enter a date into the date received text boxes$")
    public void deleteDefaultDateFromTextBoxes() {
        dataInput.clearDateCorrespondenceReceived();
    }

    @When("^they enter an invalid date into the date received text boxes$")
    public void enterInvalidDateIntoTextBoxes() {
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
    }

    @Then("^both correspondents are listed$")
    public void bothCorrespondentsAreListed() {
        recordCorrespondentDetails.assertPrimaryCorrespondent();
        recordCorrespondentDetails.assertSecondaryCorrespondent();
    }

    @When("^I select the primary correspondent radio button for a different correspondent$")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
        recordCorrespondentDetails.setSecondCorrespondentAsPrimaryCorrespondent();
    }

    @Then("^the correct correspondent is recorded as the \"([^\"]*)\" correspondent$")
    public void theCorrectCorrespondentIsRecordedAsTheCorrespondent(String arg0) throws Throwable {
        homepage.getCurrentCase();
        caseDetailsAccordion.assertThePrimaryContactName(sessionVariableCalled("secondCorrespondentFullName"));


    }
}
