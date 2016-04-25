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
