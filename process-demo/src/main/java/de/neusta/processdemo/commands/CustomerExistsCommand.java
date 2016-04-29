package de.neusta.processdemo.commands;

import de.mwolff.commons.command.AbstractDefaultProcessCommand;
import de.mwolff.commons.command.iface.CommandException;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class CustomerExistsCommand <T extends ProcessParameter> extends AbstractDefaultProcessCommand<T>{

    @Override
    public void execute(T context) throws CommandException {
    }
    
    @Override
    public String executeAsProcess(String startCommand, T context) {

        String result = null;
        if (context.isCustomerExists()) {
            context.setCustomerDecision("NewsLetter");
            result = "Ja";
        } else {
            context.setCustomerDecision("AddCustomer");
            result = "Nein";
        }
        
        return result;
    }

}
