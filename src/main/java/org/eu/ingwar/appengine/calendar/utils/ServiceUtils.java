package org.eu.ingwar.appengine.calendar.utils;

import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

public class ServiceUtils {

    public static final String HOLIDAYS_ID = "en.polish#holiday@group.v.calendar.google.com";

    /**
     * Global instance of the HTTP transport.
     */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static Calendar instance;
    private static List<Event> holidays;

    public static Calendar getCalendarInstance() throws IOException {
        if (instance == null) {
            AppIdentityCredential credential = new AppIdentityCredential(Arrays.asList(CalendarScopes.CALENDAR));
            instance = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("whateva").build();
        }
        return instance;
    }
    
    
    public static boolean isHoliday(Date date) throws IOException {
        if (holidays == null) {
            holidays = getCalendarInstance().events().list(HOLIDAYS_ID).execute().getItems();
        }
        
        for (Event ev : holidays) {
            Date eventDate = new Date(ev.getStart().getDate().getValue());
            if (DateUtils.isSameDay(date, eventDate)) {
                return true;
            }
        }
        return false;
    }

    private ServiceUtils() {
    }

}
