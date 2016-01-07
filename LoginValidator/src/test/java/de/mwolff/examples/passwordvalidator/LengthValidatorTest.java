package de.mwolff.examples.passwordvalidator;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class LengthValidatorTest {

    @Test
    public void testValidateFails() throws Exception {
        final LengthValidator<PasswordParameter> lengthValidator = new LengthValidator<PasswordParameter>();
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setLength(19);
        loginParameter.setPassword("short to Validate.");
        lengthValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(false));
        assertThat(errors.get(0), CoreMatchers.is("The Password has to be as least 19 Characters."));
    }

    @Test
    public void testValidate() throws Exception {
        final LengthValidator<PasswordParameter> lengthValidator = new LengthValidator<PasswordParameter>();
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setLength(19);
        loginParameter.setPassword("Long enough to Validate.");
        lengthValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(true));
    }

}
