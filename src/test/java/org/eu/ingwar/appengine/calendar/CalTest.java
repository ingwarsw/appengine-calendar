package org.eu.ingwar.appengine.calendar;

import java.io.IOException;
import org.eu.ingwar.appengine.calendar.utils.ServiceUtils;
import org.junit.Test;

/**
 *
 * @author Karol Lassak 'Ingwar' <karol.lassak@coi.gov.pl>
 */
public class CalTest {
    
    @Test
    public void testSomeMethod() throws IOException {
   
    }
    
    @Test
    public void testUtils() throws IOException {
        ServiceUtils.getCalendarInstance();
    }

//    public void test() throws IOException {
//
//        Calendar service = ServiceUtils.getCalendarInstance();
//
//        com.google.api.services.calendar.model.Calendar model = new com.google.api.services.calendar.model.Calendar();
//        model.setDescription("Pierwszy testowy");
//        model.setSummary("Tescikowy kalendarz");
////        model.setId("en.polish#holiday@group.v.calendar.google.com");
////        String calId = service.calendars().insert(model).execute().getId();
////        resp.getWriter().println("Dodano kalendarz o id: " + calId);
//
//        for (CalendarListEntry en : service.calendarList().list().execute().getItems()) {
////            resp.getWriter().println("CalList: " + en.getDescription() + ": " + en.getId());
//            service.calendars().delete(en.getId()).execute();
//        }
//    }
    
}
