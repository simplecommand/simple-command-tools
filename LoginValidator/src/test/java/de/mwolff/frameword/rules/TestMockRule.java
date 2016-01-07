package de.mwolff.frameword.rules;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import de.mwolff.framework.rules.MockRule;

public class TestMockRule {

    @Rule
    public TestRule mockRule = new MockRule(this);

    @Mock
    public List<String> baz;

    @Test
    public void testBaz() throws Exception {
        when(this.baz.size()).thenReturn(2);
        assertEquals(2, this.baz.size());
    }
}