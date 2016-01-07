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

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import de.neusta.examples.passwordvalidator.LengthValidator;
import de.neusta.examples.passwordvalidator.PasswordParameter;

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
