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

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import de.mwolff.commons.command.iface.CommandException;
import de.neusta.framework.rules.MockRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/application.xml")
public class CapitalValidatorTest {

    @Rule
    public TestRule mockRule = new MockRule(this);

    @InjectMocks
    @Resource
    CapitalValidator<PasswordParameter> capitalValidator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void validateFails() throws Exception {
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setPassword("no capital letters");
        capitalValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(false));
        assertThat(errors.get(0), CoreMatchers.is("The Password has to be as least 2 capital characters."));
    }

    @Test
    public void validateThrowsException() throws Exception {
        thrown.expect(CommandException.class);
        ReflectionTestUtils.setField(capitalValidator, "countCapital", 0);
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setPassword("two Capital ketter.");
        capitalValidator.execute(loginParameter);
    }

    @Test
    public void validate() throws Exception {
        final PasswordParameter loginParameter = new PasswordParameter();
        loginParameter.setPassword("two Capital Letter.");
        capitalValidator.execute(loginParameter);
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(true));
    }

}
