package org.mwolff.generator.structures;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class ClassStructureTest {
    
    @Test
    public void importerNotExists() throws Exception {
        ClassStructure structure = new ClassStructure();
        assertThat(structure.importerExists("org.mwolff.List"), is(false));
    }

    @Test
    public void importerExists() throws Exception {
        ClassStructure structure = new ClassStructure();
        structure.addImports("org.mwolff.List");
        assertThat(structure.importerExists("org.mwolff.List"), is(true));
    }

}
