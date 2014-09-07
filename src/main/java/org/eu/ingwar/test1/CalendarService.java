package org.eu.ingwar.test1;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import java.io.IOException;

/**
 *
 * @author Karol Lassak 'Ingwar' <karol.lassak@coi.gov.pl>
 */
public class CalendarService {

    public CalendarService() {
    }
    
    public CalendarService test() throws IOException {
        Calendar cal = Utils.loadCalendarClient();
        System.out.println("Test");
        return cal;
    }
}
