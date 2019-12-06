package com.example.seedfunding.InvestorModel;

public class Investor_upload {
    private String InvestorName,InvestorAbout,InvestorExperience,InvestorEducation,InvestorDomain,InvestorBudget;
    private String mInvestorImageUrl;

    public Investor_upload() {
    }

    public Investor_upload(String investorName, String investorAbout, String investorExperience, String investorEducation, String investorDomain, String investorBudget, String mInvestorImageUrl) {
        InvestorName = investorName;
        InvestorAbout = investorAbout;
        InvestorExperience = investorExperience;
        InvestorEducation = investorEducation;
        InvestorDomain = investorDomain;
        InvestorBudget = investorBudget;
        this.mInvestorImageUrl = mInvestorImageUrl;
    }

    public String getInvestorName() {
        return InvestorName;
    }

    public void setInvestorName(String investorName) {
        InvestorName = investorName;
    }

    public String getInvestorAbout() {
        return InvestorAbout;
    }

    public void setInvestorAbout(String investorAbout) {
        InvestorAbout = investorAbout;
    }

    public String getInvestorExperience() {
        return InvestorExperience;
    }

    public void setInvestorExperience(String investorExperience) {
        InvestorExperience = investorExperience;
    }

    public String getInvestorEducation() {
        return InvestorEducation;
    }

    public void setInvestorEducation(String investorEducation) {
        InvestorEducation = investorEducation;
    }

    public String getInvestorDomain() {
        return InvestorDomain;
    }

    public void setInvestorDomain(String investorDomain) {
        InvestorDomain = investorDomain;
    }

    public String getInvestorBudget() {
        return InvestorBudget;
    }

    public void setInvestorBudget(String investorBudget) {
        InvestorBudget = investorBudget;
    }

    public String getmInvestorImageUrl() {
        return mInvestorImageUrl;
    }

    public void setmInvestorImageUrl(String mInvestorImageUrl) {
        this.mInvestorImageUrl = mInvestorImageUrl;
    }
}
