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
package de.mwolff.examples.passwordvalidator;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppConfigTest {

    @Resource
    LengthValidator<PasswordParameter> lengthValidator;

    @Resource
    PasswordService passwordService;

    @Resource
    PasswordParameter passwordParameter;

    @Test
    public void lengthValidatorTest() throws Exception {
        assertThat(lengthValidator, CoreMatchers.notNullValue());
    }

    @Test
    public void loginServiceTest() throws Exception {
        assertThat(passwordService, CoreMatchers.notNullValue());
    }

    @Test
    public void passwordParameterTest() throws Exception {
        assertThat(passwordParameter, CoreMatchers.notNullValue());
    }

}
