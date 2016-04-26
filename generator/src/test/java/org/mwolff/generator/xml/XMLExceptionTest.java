/**
    Simple Generator Framework.
    Bases on Simple Command Framework

    Framework for easy source code generation via velocity
    @author Manfred Wolff <m.wolff@neusta.de>
    Download: https://github.com/simplecommand/SimpleCommandFramework-Examples


    Copyright (C) 2016 neusta software development

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
    USA
 */
package org.mwolff.generator.xml;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class XMLExceptionTest {

    @Test
    public void xmlExceptionDefaultConstructorTest() throws Exception {
        Assert.assertThat(new XMLException(), Matchers.notNullValue());
    }

    @Test
    public void xmlExceptionWithMessageTest() throws Exception {
        final XMLException commandException = new XMLException("message");
        Assert.assertThat(commandException.getMessage(), Matchers.is("message"));
    }

    @Test
    public void xmlExceptionWithMessageAndThrowableTest() throws Exception {
        final XMLException commandException = new XMLException("message", null);
        Assert.assertThat(commandException.getMessage(), Matchers.is("message"));
        Assert.assertThat(commandException.getCause(), Matchers.nullValue());
    }

    @Test
    public void xmlExceptionWithThrowableTest() throws Exception {
        final Exception exception = new Exception();
        final XMLException commandException = new XMLException(exception);
        Assert.assertThat(commandException.getCause(), Matchers.is(exception));
    }

}
