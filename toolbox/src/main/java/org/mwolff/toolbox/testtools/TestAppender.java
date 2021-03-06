package org.mwolff.toolbox.testtools;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 */
public class TestAppender extends AppenderSkeleton {

    final List<LoggingEvent> log = new ArrayList<>();

    /*
     * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.
     * LoggingEvent)
     */
    @Override
    protected void append(final LoggingEvent loggingEvent) {
        log.add(loggingEvent);
    }

    /*
     * @see org.apache.log4j.Appender#close()
     */
    @Override
    public void close() {
        // Interface method. Does nothing in this case.
    }

    public final List<LoggingEvent> getLog() {
        return new ArrayList<>(log);
    }

    /*
     * @see org.apache.log4j.Appender#requiresLayout()
     */
    @Override
    public boolean requiresLayout() {
        return false;
    }

}
