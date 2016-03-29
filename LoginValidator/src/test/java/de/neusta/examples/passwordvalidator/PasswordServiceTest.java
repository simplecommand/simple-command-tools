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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import de.mwolff.command.chainbuilder.InjectionChainBuilder;
import de.mwolff.commons.command.iface.Command;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/application.xml")
public class PasswordServiceTest {

    @Resource
    PasswordService passwordService;

    @Resource
    PasswordParameter passwordParameter;

    @Test
    public void validateNoError() throws Exception {

        // Given
        final String password = "KlartextInDerDatenbank";

        // When
        final List<String> errors = null;
        final boolean result = passwordService.valdidate(password);

        // Then
        assertThat(result, CoreMatchers.is(true));
        assertThat(errors, CoreMatchers.nullValue());
    }
    
     
    @Test
    public void validateError() throws Exception {

        // Given
        final String password = "KlarText";

        // When
        List<String> errors = null;
        final boolean result = passwordService.valdidate(password);
        errors = passwordService.getErrors();
             
        // Then
        assertThat(result, CoreMatchers.is(false));
        assertThat(errors.size(), CoreMatchers.is(1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void atLeastTwoValidatorsInjected() throws Exception {
        final InjectionChainBuilder<PasswordParameter> builder = (InjectionChainBuilder<PasswordParameter>) ReflectionTestUtils
                .getField(passwordService, "injectionChainBuilder");
        final List<Command<PasswordParameter>> commands = (List<Command<PasswordParameter>>) ReflectionTestUtils
                .getField(builder, "commands");
        assertThat(commands.size(), CoreMatchers.is(2));
    }
}
