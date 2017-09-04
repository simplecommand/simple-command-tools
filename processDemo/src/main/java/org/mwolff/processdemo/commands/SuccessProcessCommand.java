package org.mwolff.processdemo.commands;

import org.mwolff.command.CommandException;
import org.mwolff.command.process.AbstractDefaultProcessCommand;

public class SuccessProcessCommand extends AbstractDefaultProcessCommand<PaymentParameterObject>{

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

}
