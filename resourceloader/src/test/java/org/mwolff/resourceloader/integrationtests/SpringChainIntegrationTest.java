package org.mwolff.resourceloader.integrationtests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.command.Command;
import org.mwolff.command.chain.ChainBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class SpringChainIntegrationTest {

    @Resource
    ChainBuilder<String> injectionChainBuilder;
    
    @Test
    public void testInjectionSuccessful() throws Exception {
        assertThat(injectionChainBuilder, notNullValue());
    }
    
    @Test
    public void testCommandsAreInjected() throws Exception {
        @SuppressWarnings("unchecked")
        List<Command<String>> commandList = (List<Command<String>>) ReflectionTestUtils.getField(injectionChainBuilder, "commands");
        assertThat(commandList.size(), is(2));
    }
    
    @Test
    public void testGreenPathSpring() throws Exception {
        boolean result = injectionChainBuilder.buildChain().executeAsChain("test.txt");
        assertThat(result, is(Boolean.FALSE));
    }
 
}
