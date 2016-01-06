package de.mwolff.examples.loginvalidator;

import org.springframework.stereotype.Component;

import de.mwolff.commons.command.DefaultCommand;

@Component
public class LengthValidator<T extends LoginParameter> extends DefaultCommand<T> {

    private int characters;

    @Override
    public void execute(LoginParameter loginParameter) {
        characters = loginParameter.getLength();
        if (loginParameter.getPassword().length() < characters) {
            loginParameter.getErrors().add(String.format("The Password has to be as least %d Characters.", characters));
        }
    }

}
