package org.mwolff.toolbox.testtools;

import org.apache.log4j.Logger;

public class TestAppenderTestClass {

    private static final Logger LOG = Logger.getLogger(TestAppenderTestClass.class);

    public void injectTestAppender() {
        TestAppenderTestClass.LOG.info("Message To Catch");
    }
}
