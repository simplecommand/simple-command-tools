package org.mwolff.toolbox.datehelper;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatehelperTest {

    private Datehelper datehelper;

    @Before
    public void setUp() {
        datehelper = new Datehelper();
    }

    @Test
    public void testIsLeap() throws Exception {
        Assert.assertTrue(datehelper.isLeap(2000));
        Assert.assertTrue(datehelper.isLeap(2004));
    }

    @Test
    public void testIsNotLeap() throws Exception {
        Assert.assertFalse(datehelper.isLeap(200));
        Assert.assertFalse(datehelper.isLeap(201));
    }

    @Test
    public void testMonday() throws Exception {

        final String datum = "21.08.2017";
        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Montag"));
    }

    @Test
    public void testTuesday() throws Exception {

        final String datum = "22.08.2017";

        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Dienstag"));

    }

    @Test
    public void testWednesday() throws Exception {

        final String datum = "23.08.2017";
        final Datehelper datehelper = new Datehelper();

        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Mittwoch"));

    }

    @Test
    public void testThursday() throws Exception {

        final String datum = "24.08.2017";
        final Datehelper datehelper = new Datehelper();

        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Donnerstag"));

    }

    @Test
    public void testFriday() throws Exception {

        final String datum = "25.08.2017";
        final Datehelper datehelper = new Datehelper();

        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Freitag"));

    }

    @Test
    public void testSaturday() throws Exception {

        final String datum = "26.08.2017";
        final Datehelper datehelper = new Datehelper();

        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Samstag"));

    }

    @Test
    public void testSunday() throws Exception {

        final String datum = "27.08.2017";
        final Datehelper datehelper = new Datehelper();

        final String weekday = datehelper.getWeekday(datum);
        Assert.assertThat(weekday, CoreMatchers.is("Sonntag"));

    }

}
