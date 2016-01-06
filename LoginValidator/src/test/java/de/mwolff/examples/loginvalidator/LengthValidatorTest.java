package de.mwolff.examples.loginvalidator;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class LengthValidatorTest {

    @Test
    public void testValidateFails() throws Exception {
        final LengthValidator<LoginParameter> lengthValidator = new LengthValidator<LoginParameter>();
        final LoginParameter loginParameter = new LoginParameter();
        loginParameter.setLength(19);
        loginParameter.setPassword("short to Validate.");
        lengthValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(false));
        assertThat(errors.get(0), CoreMatchers.is("The Password has to be as least 19 Characters."));
    }

    @Test
    public void testValidate() throws Exception {
        final LengthValidator<LoginParameter> lengthValidator = new LengthValidator<LoginParameter>();
        final LoginParameter loginParameter = new LoginParameter();
        loginParameter.setLength(19);
        loginParameter.setPassword("Long enough to Validate.");
        lengthValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(true));
    }

}
