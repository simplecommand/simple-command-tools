package org.mwolff.processdemo.commands;

public class PaymentParameterObject {
    
    private String customerAccountNumber;
    private boolean fail;
    private boolean premium;
    private double value;
    
    private boolean testmode;
    private String breadCrumb;

    public String getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(String customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isTestmode() {
        return testmode;
    }

    public void setTestmode(boolean testmode) {
        this.testmode = testmode;
    }

    public String getBreadCrumb() {
        return breadCrumb;
    }

    public void setBreadCrumb(String breadCrumb) {
        this.breadCrumb = breadCrumb;
    }
}
