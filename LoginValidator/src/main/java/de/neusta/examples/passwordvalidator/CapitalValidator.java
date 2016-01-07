package de.neusta.examples.passwordvalidator;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import de.mwolff.commons.command.DefaultCommand;
import de.mwolff.commons.command.iface.CommandException;

@Component
public class CapitalValidator<T extends PasswordParameter> extends DefaultCommand<T> {

    private int countCapital;

    @Override
    public void execute(PasswordParameter loginParameter) throws CommandException {
        if (countCapital == 0) {
            throw new CommandException("The length is not configured yet (validator.properties).");
        }
        validateLength(loginParameter);
    }

    @Required
    public void setCountCapital(int countCapital) {
        this.countCapital = countCapital;
    }

    private void validateLength(PasswordParameter passwordParameter) {

        final String password = passwordParameter.getPassword();
        int actCount = 0;

        for (int i = 0; i < password.length(); i++) {
            final char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                actCount++;
            }
        }
        if (actCount < countCapital) {
            passwordParameter.getErrors().add(constructErrorMessage(countCapital));
        }
    }

    private static String constructErrorMessage(int length) {
        return String.format("The Password has to be as least %d capital characters.", length);
    }

}
