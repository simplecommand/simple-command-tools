package org.mwolff.resourceloader.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mwolff.command.CommandTransition;

public class ClassLoaderCommandTest {

    private final String classPathRessource = "test.txt";
    
    @Test
    public void testReadBadFile() throws Exception {
        final CommandTransition result = new ClassLoaderCommand().executeCommandAsChain("/BadPath");
        assertThat(result, is(CommandTransition.NEXT));
    }

    @Test
    public void testReadGoodFile() throws Exception {
        final CommandTransition result = new ClassLoaderCommand().executeCommandAsChain(classPathRessource);
        assertThat(result, is(CommandTransition.FAILURE));
    }
}
