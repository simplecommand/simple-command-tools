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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mwolff.command.chainbuilder.InjectionChainBuilder;

@Component
public class PasswordService {

    List<String> errors = new ArrayList<String>();

    @Resource
    InjectionChainBuilder<PasswordParameter> injectionChainBuilder;

    @Resource
    PasswordParameter passwordParameter;

    public List<String> getErrors() {
        return errors;
    }

    public boolean valdidate(String password) {

        prepareParameters(password);
        injectionChainBuilder.executeAsChain(passwordParameter);
        return getErrorResult();
    }

    private void prepareParameters(String password) {
        passwordParameter.setPassword(password);
    }

    private boolean getErrorResult() {
        boolean result = true;
        errors = passwordParameter.getErrors();
        if (!errors.isEmpty()) {
            result = false;
        }
        return result;
    }
}
