package org.mwolff.resourceloader.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ClassLoaderCommandTest {

    private final String classPathRessource = "test.txt";
    
    @Test
    public void testReadBadFile() throws Exception {
        final boolean result = new ClassLoaderCommand().executeAsChain("/BadPath");
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void testReadGoodFile() throws Exception {
        final boolean result = new ClassLoaderCommand().executeAsChain(classPathRessource);
        assertThat(result, is(Boolean.FALSE));
    }
}
