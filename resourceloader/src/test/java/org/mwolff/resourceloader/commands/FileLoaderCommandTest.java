package org.mwolff.resourceloader.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mwolff.command.CommandTransitionEnum.CommandTransition;

public class FileLoaderCommandTest {

    private final String filename = "src/test/resources/test.txt";
    
    @Test
    public void testReadFileFails() throws Exception {
        final CommandTransition result = new FileLoaderCommand().executeCommandAsChain("invalidFilename");
        assertThat(result, is(CommandTransition.NEXT));
    }
    
    @Test
    public void testReadFile() throws Exception {
        final CommandTransition result = new FileLoaderCommand().executeCommandAsChain(filename);
        assertThat(result, is(CommandTransition.DONE));
    }
}
