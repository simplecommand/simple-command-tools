package de.mwolff.examples.loginvalidator;

import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class LoginServiceTest {

    @Resource
    LoginService loginService;
    
    @Test
    public void loginServiceExists() throws Exception {
        assertThat(loginService, CoreMatchers.notNullValue());
    }
    
  

}
