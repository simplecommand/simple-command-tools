package org.mwolff.processdemo.commands;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class SuccessProcessCommandTest {

    @Test
    public void TestExecuteMethod() throws Exception {
        SuccessProcessCommand successProcessCommand = new SuccessProcessCommand();
        String result = successProcessCommand.executeAsProcess(null);
        assertThat(result, CoreMatchers.is("SUCCESS"));
    }
    
}
