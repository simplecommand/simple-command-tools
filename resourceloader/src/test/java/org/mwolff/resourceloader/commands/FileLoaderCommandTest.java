package org.mwolff.resourceloader.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FileLoaderCommandTest {

    private final String filename = "src/test/resources/test.txt";

    @Test
    public void testReadFileFails() throws Exception {
        final FileLoaderCommand scanFile = new FileLoaderCommand();
        final boolean result = scanFile.executeAsChain("invalidFilename");
        assertThat(result, is(Boolean.TRUE));
    }
    
    @Test
    public void testReadFile() throws Exception {
        final FileLoaderCommand scanFile = new FileLoaderCommand();
        final boolean result = scanFile.executeAsChain(filename);
        assertThat(result, is(Boolean.FALSE));
    }
}
