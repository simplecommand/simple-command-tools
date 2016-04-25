package org.mwolff.generatorexample.loader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SpringLoaderTest {
    
    @Test
    public void loader() throws Exception {
        String[] args = new String[10];
        SpringLoader.main(args);
        assertThat(SpringLoader.getBuilder(), notNullValue());
    }
}
