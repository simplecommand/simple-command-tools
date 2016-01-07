package de.mwolff.examples.passwordvalidator;

import static org.junit.Assert.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PasswordServiceTest {
    
    @Resource
    PasswordService passwordService;
    
    @Resource
    PasswordParameter passwordParameter;
    
    @Test
    public void validateNoError() throws Exception {
        
        // Given
        String password = "KlartextInDerDatenbank";
        
        // When
        List<String> errors = null;
        boolean result = passwordService.valdidate(password);
        // Then
        assertThat(result, CoreMatchers.is(true));
        assertThat(errors, CoreMatchers.nullValue());
    }
    
    @Test
    public void validateError() throws Exception {
        
        // Given
        String password = "Klartext";
        
        // When
        List<String> errors = null;
        boolean result = passwordService.valdidate(password);
        errors = passwordService.getErrors();
        
        // Then
        assertThat(result, CoreMatchers.is(false));
        assertThat(errors.size(), CoreMatchers.is(1));
    }
  

}
