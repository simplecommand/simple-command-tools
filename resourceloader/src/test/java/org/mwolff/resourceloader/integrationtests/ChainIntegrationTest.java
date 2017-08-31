package org.mwolff.resourceloader.integrationtests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.DefaultCommandContainer;
import org.mwolff.resourceloader.commands.ClassLoaderCommand;
import org.mwolff.resourceloader.commands.FileLoaderCommand;

public class ChainIntegrationTest {


    @Test
    public void testGreenPathClassloader() throws Exception {
        
        new ClassLoaderCommand();
        new FileLoaderCommand();
        
        CommandContainer<String> commandContainer = new DefaultCommandContainer<>();
        boolean result = commandContainer
            .addCommand(new ClassLoaderCommand())
            .addCommand(new FileLoaderCommand())
            .executeAsChain("test.txt");
        
        assertThat(result, is(Boolean.FALSE));
    }
    
    @Test
    public void testRedPathClassloader() throws Exception {
        
        new ClassLoaderCommand();
        new FileLoaderCommand();
        
        CommandContainer<String> commandContainer = new DefaultCommandContainer<>();
        boolean result = commandContainer
            .addCommand(new ClassLoaderCommand())
            .addCommand(new FileLoaderCommand())
            .executeAsChain("nobodyKnows.txt");
        
        assertThat(result, is(Boolean.TRUE));
    }
}
