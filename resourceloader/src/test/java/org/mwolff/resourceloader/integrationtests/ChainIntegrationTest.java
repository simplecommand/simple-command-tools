package org.mwolff.resourceloader.integrationtests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandContainer;
import org.mwolff.command.CommandTransition;
import org.mwolff.command.DefaultCommandContainer;
import org.mwolff.command.chain.XMLChainBuilder;
import org.mwolff.resourceloader.commands.ClassLoaderCommand;
import org.mwolff.resourceloader.commands.FileLoaderCommand;

public class ChainIntegrationTest {


    @Test
    public void testGreenPathClassloader() throws Exception {
        CommandContainer<String> commandContainer = new DefaultCommandContainer<>();
        CommandTransition result = commandContainer
            .addCommand(new ClassLoaderCommand())
            .addCommand(new FileLoaderCommand())
            .executeCommandAsChain("test.txt");
        
        assertThat(result, is(CommandTransition.FAILURE));
    }

    @Test
    public void testGreenPathXML() throws Exception {
        XMLChainBuilder<String> builder = new XMLChainBuilder<>("/chain.xml");
        CommandTransition result = builder.executeCommandAsChain("test.txt");
        assertThat(result, is(CommandTransition.DONE));
    }
    
    @Test
    public void testRedPathClassloader() throws Exception {
        
        new ClassLoaderCommand();
        new FileLoaderCommand();
        
        CommandContainer<String> commandContainer = new DefaultCommandContainer<>();
        CommandTransition result = commandContainer
            .addCommand(new ClassLoaderCommand())
            .addCommand(new FileLoaderCommand())
            .executeCommandAsChain("nobodyKnows.txt");
        
        assertThat(result, is(CommandTransition.NEXT));
    }
}
