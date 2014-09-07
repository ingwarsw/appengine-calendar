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
}
