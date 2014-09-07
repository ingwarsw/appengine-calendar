package org.eu.ingwar.appengine.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eu.ingwar.appengine.calendar.utils.ServiceUtils;

public class CalServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Calendar service = ServiceUtils.getCalendarInstance();

        resp.setContentType("text/plain");
        resp.getWriter().println("Acha");
        resp.getWriter().println("URL: " + service.getBaseUrl());
        resp.getWriter().println("Service path: " + service.getServicePath());
        
        resp.getWriter().println("Service path: " + service.events().list("en.polish#holiday@group.v.calendar.google.com").execute());
        
        com.google.api.services.calendar.model.Calendar model = new com.google.api.services.calendar.model.Calendar();
        model.setDescription("Pierwszy testowy");
        model.setSummary("Tescikowy kalendarz");
//        model.setId("en.polish#holiday@group.v.calendar.google.com");
//        model.
        String calId = service.calendars().insert(model).execute().getId();
        resp.getWriter().println("Dodano kalendarz o id: " + calId);
//        service.
        
        for (CalendarListEntry en : service.calendarList().list().execute().getItems()) {
            resp.getWriter().println("CalList: " + en.getDescription() + ": " + en.getId());
            
        }
    }
}
