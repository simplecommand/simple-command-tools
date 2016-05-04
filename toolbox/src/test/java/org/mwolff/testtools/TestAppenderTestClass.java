package org.mwolff.testtools;

import org.apache.log4j.Logger;

public class TestAppenderTestClass {

    private static final Logger LOG = Logger.getLogger(TestAppenderTestClass.class);
    
    public void injectTestAppender() {
        LOG.info("Message To Catch");
    }
}
