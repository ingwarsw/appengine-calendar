/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.eu.ingwar.test1;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Karol Lassak 'Ingwar' <karol.lassak@coi.gov.pl>
 */
public class CalTest {
    
    @Test
    public void testSomeMethod() throws IOException {
        CalendarService c = new CalendarService();
        Calendar cal = c.test();
//        com.google.api.services.calendar.model.Calendar model = new com.google.api.services.calendar.model.Calendar();
//        model.setDescription("Pierwszy testowy");
//        model.setSummary("Tescikowy kalendarz");
//        cal.calendars().insert(model);
//        
//        for (CalendarListEntry en : cal.calendarList().list().execute().getItems()) {
//            System.err.println("CalList: " + en.getDescription());
//        }
//        for (CalendarListEntry en : cal.calendarList().list().execute().getItems()) {
//            System.err.println("CalList: " + en.getDescription());
//        }    
    }
    
    @Test
    public void testUtils() throws IOException {
        Utils.getClientCredential();
    }
}
