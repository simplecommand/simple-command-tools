/*
    Command Example for the SCF (Simple Command Framework)
    Copyright (C) 2016  Manfred Wolff, neusta software development

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package de.neusta.examples.passwordvalidator;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import de.mwolff.commons.command.DefaultCommand;
import de.mwolff.commons.command.iface.CommandException;

@Component
public class LengthValidator<T extends PasswordParameter> extends DefaultCommand<T> {
    

    private int injectedLength;

    @Override
    public void execute(PasswordParameter loginParameter) throws CommandException {
        if (injectedLength ==  0) {
          throw new CommandException("The length is not configured yet (validator.properties).");  
        }
        validateLength(loginParameter);
    }

    @Required
    public void setInjectedLength(int injectedLength) {
        this.injectedLength = injectedLength;
    }

    private void validateLength(PasswordParameter passwordParameter)  {
        if (passwordParameter.getPassword().length() < injectedLength) {
            passwordParameter.getErrors().add(constructErrorMessage(injectedLength));
        }
    }

    private static String constructErrorMessage(int length) {
        return String.format("The Password has to be as least %d Characters.", length);
    }

}
