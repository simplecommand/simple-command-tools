package org.mwolff.resourceloader.commands;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

public class ClassLoaderCommandTest {

    private final String classPathRessource = "test.txt";
    
    @Test
    public void testReadBadFile() throws Exception {
        final ClassLoaderCommand classLoaderCommand = new ClassLoaderCommand();
        final boolean result = classLoaderCommand.executeAsChain("/BadPath");
        Assert.assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void testReadGoodFile() throws Exception {
        final ClassLoaderCommand classLoaderCommand = new ClassLoaderCommand();
        final boolean result = classLoaderCommand.executeAsChain(classPathRessource);
        Assert.assertThat(result, is(Boolean.FALSE));
    }
}
