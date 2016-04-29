package de.neusta.processdemo.commands;

import de.mwolff.commons.command.AbstractDefaultProcessCommand;
import de.mwolff.commons.command.iface.CommandException;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class StartCommand<T extends ProcessParameter> extends AbstractDefaultProcessCommand<T>{

    @Override
    public void execute(T context) throws CommandException {
        context.setStarted(true);
    }
    
    @Override
    public String executeAsProcess(String startCommand, T context) {
        try {
            this.execute(context);
        } catch (CommandException e) {
        }
        return "OK";
    }
}
