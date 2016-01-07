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
        boolean result = true;
        
        passwordParameter.setLength(12);
        passwordParameter.setPassword(password);
        lengthValidator.execute(passwordParameter);
        
        errors = passwordParameter.getErrors();
        if (! errors.isEmpty()) {
            result = false;
        }
        return result;
    }

    public List<String> getErrors() {
        return errors;
    }

}
