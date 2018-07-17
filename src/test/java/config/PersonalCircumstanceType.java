package config;

public enum PersonalCircumstanceType {

    AAAA_DESC("2500001951"),
    AP_MEDICATION_IN_POSESSION__ASSESSMENT("2500003002"),
    ACCOMMODATION("1"),
    ALCOHOL_SCREENING("15"),
    ARMED_FORCES_PERSONNEL("12"),
    BENEFIT("3"),
    CIRCUM_ONE("2500000451"),
    CP_UPW_OFFENDER_PROJECT_INFORMATION("13"),
    CARE_LEAVER("1500000451"),
    CHILD_CONCERNS("2500003005"),
    CHILD_SEXUAL_EXPLOITATION_VICTIM("2500003007"),
    CODE_NOT_FOUND_IN_MIGRATION("9"),
    DTV_TIERING("2500003020"),
    DEATH_UNDER_SUPERVISION_FACTORS("1500000953"),
    DEATH_UNDER_SUPERVISION_STATUS("1500000952"),
    DEATH_UNDER_SUPERVISION_TYPE("1500000951"),
    DEPENDENTS("11"),
    ESF_INDUCTION("2500000952"),
    EDUCATIONAL_ATTAINMENT_LEVELS("2500000951"),
    EMPLOYMENT("2"),
    FAMILY_SUPPORT("2500003008"),
    GENDER_REASSIGNMENT_QUESTIONS("2500003050"),
    GENERAL_HEALTH("4"),
    HATE_CRIME_CATEGORIES("16"),
    LITERACY_AND_NUMERACY("7"),
    LONDON_CRC_TIERING("2500003035"),
    LOOKED_AFTER_CHILD("2500003034"),
    NPS_PERSONAL_CONTACTS("6"),
    NIGEL_PC("2500003451"),
    OPD_PATHWAY_SCREEN("1500001951"),
    OFFENDER_LEVEL_RECORDING("14"),
    OFFENDER_HAS_CARE_AND_SUPPORT_NEEDS("2500003037"),
    OFFENDER_IS_CARER_OF_ADULT_WITH_CARE_AND_SUPT_NEEDS("2500003036"),
    OTHER("8"),
    PREGNANCY_MATERNITY("2500000000"),
    PRIORITY_LEVEL("2500003046"),
    PROGRESSION("2500003045"),
    PURPLE_FUTURES_BAND("2500003039"),
    RAGGING("2500003033"),
    RRP_BAND("2500003047"),
    RRP_STEPDOWN("2500003044"),
    RELATIONSHIP("10"),
    REPORTING_FREQUENCY("1499999951"),
    RISK_TO_ADULT_AT_RISK("2500003043"),
    RISK_TO_CHIDREN_IDENTITY_KNOWN("2500003041"),
    SAFEGUARDING_ADULT_AT_RISK("2500003053"),
    SICKNESS_NON_AVAILABILITY("5"),
    SKILLS_SCREENING("17"),
    SODEXO_BAND("2500003048"),
    SUPERHUMAN_ABILITIES("2500001451"),
    TR_COHORT_ALLOCATION("1500001451"),
    THAMES_VALLEY_CRC_TIERING("2500003052"),
    TROUBLED_FAMILIES("2500003049"),
    Z_DUMMY_CIRCUMSTANCE_TYPE_05("18"),
    Z_DUMMY_CIRCUMSTANCE_TYPE_06("19"),
    Z_DUMMY_CIRCUMSTANCE_TYPE_07("20"),
    Z_DUMMY_CIRCUMSTANCE_TYPE_08("21"),
    Z_DUMMY_CIRCUMSTANCE_TYPE_09("22"),
    Z_DUMMY_CIRCUMSTANCE_TYPE_10("23"),
    A_MADE_UP_PC("2500002451"),
    ZZ("2500002452");

    private final String personalCircumstanceType;

    PersonalCircumstanceType(String personalCircumstanceType) {
        this.personalCircumstanceType = personalCircumstanceType;
    }

    public String getPersonalCircumstanceType() {
        return personalCircumstanceType;
    }
}

