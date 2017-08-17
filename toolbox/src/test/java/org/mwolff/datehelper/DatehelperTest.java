package org.mwolff.datehelper;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
        assertTrue(datehelper.isLeap(2000));
        assertTrue(datehelper.isLeap(2004));
    }

    @Test
    public void testIsNotLeap() throws Exception {
        assertFalse(datehelper.isLeap(200));
        assertFalse(datehelper.isLeap(201));
    }

    @Test
    public void testMonday() throws Exception {
        
        String datum = "21.08.2017";
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Montag"));
    }
    
    @Test
    public void testTuesday() throws Exception {

        String datum = "22.08.2017";
        
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Dienstag"));
        
    }
    
    @Test
    public void testWednesday() throws Exception {

        String datum = "23.08.2017";
        Datehelper datehelper = new Datehelper();
        
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Mittwoch"));
        
    }
    
    @Test
    public void testThursday() throws Exception {

        String datum = "24.08.2017";
        Datehelper datehelper = new Datehelper();
        
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Donnerstag"));
        
    }
    
    @Test
    public void testFriday() throws Exception {

        String datum = "25.08.2017";
        Datehelper datehelper = new Datehelper();
        
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Freitag"));
        
    }
    
    @Test
    public void testSaturday() throws Exception {

        String datum = "26.08.2017";
        Datehelper datehelper = new Datehelper();
        
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Samstag"));
        
    }
    
    @Test
    public void testSunday() throws Exception {

        String datum = "27.08.2017";
        Datehelper datehelper = new Datehelper();
        
        String weekday = datehelper.getWeekday(datum);
        assertThat(weekday, is("Sonntag"));
        
    }
    
}
