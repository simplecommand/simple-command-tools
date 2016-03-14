package org.mwolff.rules;


import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.springframework.beans.factory.annotation.Autowired;

import org.mwolff.rules.SpringContextRule;

public class TestSpringRule {

	@Rule
	public TestRule contextRule = new SpringContextRule(
			new String[] { "file:src/test/resources/applicationcontext.xml" },
			this);

	@Autowired
	public String bar;

	@Test
	public void testBaz() throws Exception {
		assertEquals("bar", this.bar);
	}

}
