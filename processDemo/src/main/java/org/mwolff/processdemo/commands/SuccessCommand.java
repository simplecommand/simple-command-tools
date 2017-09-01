package org.mwolff.processdemo.commands;

import org.mwolff.command.CommandException;
import org.mwolff.command.process.ProcessCommand;

public class SuccessCommand implements ProcessCommand<PaymentParameterObject>{

    @Override
    public String executeAsProcess(PaymentParameterObject context) {
        return "SUCCESS";
    }
    
    @Override
    public boolean executeAsChain(PaymentParameterObject parameterObject) {
        return false;
    }

    @Override
    public void execute(PaymentParameterObject parameterObject) throws CommandException {
        
    }

    @Override
    public String getProcessID() {
        return "SUSSESSPROCESS";
    }

    @Override
    public void setProcessID(String processID) {
    }

}
