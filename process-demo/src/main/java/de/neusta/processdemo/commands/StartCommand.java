package de.neusta.processdemo.commands;

import de.mwolff.commons.command.AbstractDefaultProcessCommand;
import de.mwolff.commons.command.iface.CommandException;
import de.neusta.processdemo.parameterobject.ProcessParameter;

public class StartCommand<T extends ProcessParameter> extends AbstractDefaultProcessCommand<T>{

    @Override
    public String executeAsProcess(String startCommand, T context) {
        context.setStarted(true);
        return "OK";
    }

    @Override
    public void execute(T context) throws CommandException {
    }
}
