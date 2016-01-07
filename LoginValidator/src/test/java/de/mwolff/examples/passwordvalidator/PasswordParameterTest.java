package de.mwolff.examples.passwordvalidator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class PasswordParameterTest {

    private PasswordParameter loginParameter;

    @Before
    public void setUp() {
        loginParameter = new PasswordParameter();
    }

    @Test
    public void setLoginName() throws Exception {
        loginParameter.setLoginName("Manfred Wolff");
        final String loginName = (String) ReflectionTestUtils.getField(loginParameter, "loginName");
        assertThat(loginName, is("Manfred Wolff"));
    }

    @Test
    public void getLoginName() throws Exception {
        loginParameter.setLoginName("Manfred Wolff");
        final String loginName = loginParameter.getLoginName();
        assertThat(loginName, is("Manfred Wolff"));
    }

    @Test
    public void getErrorsIsEmpty() throws Exception {
        final List<String> errors = loginParameter.getErrors();
        assertThat(errors.isEmpty(), CoreMatchers.is(true));
    }

    @Test
    public void setLength() throws Exception {
        loginParameter.setLength(12);
        final int length = (int) ReflectionTestUtils.getField(loginParameter, "length");
        assertThat(length, is(12));
    }

    @Test
    public void getLength() throws Exception {
        loginParameter.setLength(12);
        final int length = loginParameter.getLength();
        assertThat(length, is(12));
    }

    @Test
    public void setPassword() throws Exception {
        loginParameter.setPassword("password");
        final String password = (String) ReflectionTestUtils.getField(loginParameter, "password");
        assertThat(password, is("password"));
    }

    @Test
    public void getPassword() throws Exception {
        loginParameter.setPassword("password");
        final String password = loginParameter.getPassword();
        assertThat(password, is("password"));
    }
}
