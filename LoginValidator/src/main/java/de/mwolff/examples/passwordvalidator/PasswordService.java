package de.mwolff.examples.passwordvalidator;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class PasswordService {

    List<String> errors = null;

    @Resource
    LengthValidator<PasswordParameter> lengthValidator;

    @Resource
    PasswordParameter passwordParameter;

    public boolean valdidate(String password) {

        prepareParameters(password);
        lengthValidator.execute(passwordParameter);
        return getErrorResult();
    }

    private boolean getErrorResult() {
        boolean result = true;
        errors = passwordParameter.getErrors();
        if (!errors.isEmpty()) {
            result = false;
        }
        return result;
    }

    private void prepareParameters(String password) {
        passwordParameter.setLength(12);
        passwordParameter.setPassword(password);
    }

    public List<String> getErrors() {
        return errors;
    }

}
