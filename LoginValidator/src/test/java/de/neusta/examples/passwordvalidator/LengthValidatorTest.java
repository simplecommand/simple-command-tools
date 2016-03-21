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

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import de.mwolff.commons.command.iface.CommandException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/application.xml")
public class LengthValidatorTest {

    @Resource
    LengthValidator<PasswordParameter> lengthValidator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void validateFails() throws Exception {
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setPassword("short to Validate.");
        lengthValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(false));
        assertThat(errors.get(0), CoreMatchers.is("The Password has to be as least 20 Characters."));
    }

    @Test
    public void validateThrowsException() throws Exception {
        thrown.expect(CommandException.class);
        lengthValidator.setLength(0);
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setPassword("Long enough to Validate.");
        lengthValidator.execute(loginParameter);
    }

    @Test
    public void validate() throws Exception {
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setPassword("Long enough to Validate.");
        lengthValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(true));
    }

}
