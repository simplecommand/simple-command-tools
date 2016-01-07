package de.mwolff.examples.passwordvalidator;

import org.springframework.stereotype.Component;

import de.mwolff.commons.command.DefaultCommand;

@Component
public class LengthValidator<T extends PasswordParameter> extends DefaultCommand<T> {


    @Override
    public void execute(PasswordParameter loginParameter) {
        validateLength(loginParameter);
    }

    private void validateLength(PasswordParameter passwordParameter) {
        int characters = passwordParameter.getLength();
        if (passwordParameter.getPassword().length() < characters) {
            passwordParameter.getErrors().add(constructErrorMessage(characters));
        }
    }
    
    private String constructErrorMessage(int length) {
        return String.format("The Password has to be as least %d Characters.", length);
    }
}
