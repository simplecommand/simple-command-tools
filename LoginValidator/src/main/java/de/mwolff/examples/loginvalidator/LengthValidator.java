package de.mwolff.examples.loginvalidator;

import org.springframework.stereotype.Component;

import de.mwolff.commons.command.DefaultCommand;

@Component
public class LengthValidator<T extends LoginParameter> extends DefaultCommand<T> {


    @Override
    public void execute(LoginParameter loginParameter) {
        validateLength(loginParameter);
    }

    private void validateLength(LoginParameter loginParameter) {
        int characters = loginParameter.getLength();
        if (loginParameter.getPassword().length() < characters) {
            loginParameter.getErrors().add(constructErrorMessage(characters));
        }
    }
    
    private String constructErrorMessage(int length) {
        return String.format("The Password has to be as least %d Characters.", length);
    }
}
