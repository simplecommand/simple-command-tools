package org.mwolff.processdemo.commands;

import org.mwolff.command.CommandTransition;
import org.mwolff.command.process.AbstractDefaultProcessCommand;
import org.springframework.util.StringUtils;

public class SuccessProcessCommand extends AbstractDefaultProcessCommand<PaymentParameterObject> {

    @Override
    public String executeAsProcess(PaymentParameterObject context) {
        executeCommand(context);
        return "SUCCESS";
    }
    
    @Override
    public CommandTransition executeCommand(PaymentParameterObject parameterObject) {
        if (parameterObject.isTestmode()) {
            String breadCrumb = parameterObject.getBreadCrumb();
            if (StringUtils.isEmpty(breadCrumb)) breadCrumb = "";

            breadCrumb = breadCrumb + " => " + getProcessID();
            parameterObject.setBreadCrumb(breadCrumb);
        }
        return super.executeCommand(parameterObject);
    }

}
