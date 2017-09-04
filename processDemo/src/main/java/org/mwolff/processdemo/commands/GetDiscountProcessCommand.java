package org.mwolff.processdemo.commands;

import org.mwolff.command.process.AbstractDefaultProcessCommand;
import org.springframework.util.StringUtils;

public class GetDiscountProcessCommand extends AbstractDefaultProcessCommand<PaymentParameterObject>{

    @Override
    public String executeAsProcess(PaymentParameterObject context) {
        execute(context);
        return "SUCCESS";
    }
    
    @Override
    public boolean executeAsChain(PaymentParameterObject parameterObject) {
        return false;
    }

    @Override
    public void execute(PaymentParameterObject parameterObject) {
        
        parameterObject.setValue(parameterObject.getValue() * 0.9);

        if (parameterObject.isTestmode()) {
            String breadCrumb = parameterObject.getBreadCrumb();
            if (StringUtils.isEmpty(breadCrumb)) breadCrumb = "";
            
            breadCrumb = breadCrumb + " => " + getProcessID();
            parameterObject.setBreadCrumb(breadCrumb);
        }
        
    }
}
