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

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.neusta.configurator.PasswordConfigurator;

@Configuration
public class AppConfig {

    @Bean(name = { "lengthValidator" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public LengthValidator<PasswordParameter> lengthValidator() {
        return new LengthValidator<PasswordParameter>();
    }

    @Bean(name = { "passwordService" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PasswordService passwordService() {
        return new PasswordService();
    }

    @Bean(name = { "passwordConfigurator" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PasswordConfigurator passwordConfigurator() {
        return new PasswordConfigurator();
    }

    @Bean(name = { "passwordParameter" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PasswordParameter passwordParameter() {
        return new PasswordParameter();
    }

}
