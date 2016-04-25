package runner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;
import org.mwolff.generator.structures.Configuration;
import org.springframework.context.ApplicationContext;

import de.mwolff.command.chainbuilder.InjectionChainBuilder;

public class SpringRunnerTest {
    
    @Mock
    InjectionChainBuilder<Configuration> injectionChainBuilder;
    
    @Mock
    private static ApplicationContext ctx;
    
    @Mock
    private static Configuration configuration;
    
    @Test
    public void loader() throws Exception {
        String[] args = new String[10];
        SpringRunner.main(args);
        assertThat(SpringRunner.getBuilder(), notNullValue());
    }
    
    @Test
    public void loaderFails() throws Exception {
        
    }
  
    
    
}
