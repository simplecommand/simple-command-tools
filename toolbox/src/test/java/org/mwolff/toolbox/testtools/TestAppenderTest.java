package org.mwolff.toolbox.testtools;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class TestAppenderTest {

    @Test
    public void appenderTest() throws Exception {

        final TestAppenderTestClass test = new TestAppenderTestClass();

        final Logger logger = (Logger) ReflectionTestUtils.getField(test, "LOG");
        logger.removeAllAppenders();
        logger.setLevel(Level.DEBUG);

        final TestAppender appender = new TestAppender();
        logger.addAppender(appender);

        test.injectTestAppender();

        final List<LoggingEvent> log = appender.getLog();
        final LoggingEvent firstLogEntry = log.get(0);
        Assert.assertThat(firstLogEntry.getLevel(), CoreMatchers.is(Level.INFO));
        Assert.assertThat((String) firstLogEntry.getMessage(), CoreMatchers.is("Message To Catch"));
        Assert.assertThat(false, CoreMatchers.is(appender.requiresLayout()));

        appender.close();
        logger.removeAppender(appender);
    }

}
