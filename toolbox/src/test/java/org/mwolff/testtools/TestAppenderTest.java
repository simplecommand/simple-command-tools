package org.mwolff.testtools;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class TestAppenderTest {

    @Test
    public void appenderTest() throws Exception {
        
        TestAppenderTestClass test = new TestAppenderTestClass();

        final Logger logger = (Logger) ReflectionTestUtils.getField(test, "LOG");
        logger.removeAllAppenders();
        logger.setLevel(Level.DEBUG);

        final TestAppender appender = new TestAppender();
        logger.addAppender(appender);

        test.injectTestAppender();
        
        final List<LoggingEvent> log = appender.getLog();
        final LoggingEvent firstLogEntry = log.get(0);
        assertThat(firstLogEntry.getLevel(), is(Level.INFO));
        assertThat((String) firstLogEntry.getMessage(), is("Message To Catch"));
        assertThat(false, is(appender.requiresLayout()));
        
        logger.removeAppender(appender);
    }

}
