package de.neusta.processdemo.parameterobject;

import de.mwolff.commons.command.iface.ParameterObject;

public class ProcessParameter implements ParameterObject {

    private boolean isStarted;
    private boolean customerExists;
    private boolean newsletterHasToBeSent;
    private boolean customerHasToBeAdded;
    private String  customerDecision;

    public boolean isCustomerExists() {
        return customerExists;
    }

    public boolean isCustomerHasToBeAdded() {
        return customerHasToBeAdded;
    }

    public boolean isNewsletterHasToBeSent() {
        return newsletterHasToBeSent;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setCustomerExists(boolean customerExists) {
        this.customerExists = customerExists;
    }

    public void setCustomerHasToBeAdded(boolean customerHasToBeAdded) {
        this.customerHasToBeAdded = customerHasToBeAdded;
    }

    public void setNewsletterHasToBeSent(boolean newsletterHasToBeSent) {
        this.newsletterHasToBeSent = newsletterHasToBeSent;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public String getCustomerDecision() {
        return customerDecision;
    }

    public void setCustomerDecision(String customerDecision) {
        this.customerDecision = customerDecision;
    }

}
