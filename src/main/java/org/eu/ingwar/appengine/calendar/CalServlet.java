package org.eu.ingwar.appengine.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.time.DateUtils;
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

//        resp.getWriter().println("Service path: " + service.events().list("en.polish#holiday@group.v.calendar.google.com").execute());
        com.google.api.services.calendar.model.Calendar model = new com.google.api.services.calendar.model.Calendar();
        model.setDescription("Pierwszy testowy");
        model.setSummary("Tescikowy kalendarz");
//        model.setId("en.polish#holiday@group.v.calendar.google.com");
//        model.
//        String calId = service.calendars().insert(model).execute().getId();
//        resp.getWriter().println("Dodano kalendarz o id: " + calId);
//        service.

        for (CalendarListEntry en : service.calendarList().list().execute().getItems()) {
            resp.getWriter().println("CalList: " + en.getDescription() + ": " + en.getId());
            service.calendars().delete(en.getId()).execute();
        }

        Date from = null;
        try {
            DateFormat df = new SimpleDateFormat("");
            from = df.parse(req.getParameter("from"));
        } catch (Exception ex) {
            resp.getWriter().println("Exception: " + ex.getLocalizedMessage());
        }

        if (from == null) {
            from = new Date();
        }

        int visits = 52;

        List<Boolean> days = new ArrayList<>();
        days.add(Boolean.TRUE);
        days.add(Boolean.FALSE);
        days.add(Boolean.FALSE);
        days.add(Boolean.TRUE);
        days.add(Boolean.FALSE);
        days.add(Boolean.FALSE);
        days.add(Boolean.FALSE);

        while (visits > 0) {
            for (int i = 0; i < 7; i++) {
                from = DateUtils.addDays(from, 1);
                if (days.get(i)) {
                    boolean holiday = ServiceUtils.isHoliday(from);
                    resp.getWriter().print("Date: " + from);
                    if (holiday) {
                        resp.getWriter().println(" IS holiday? " + holiday);
                    } else {
                        visits--;
                        resp.getWriter().println(" IS worning.. visits left " + visits);
                    }
                    if (visits == 0) {
                        resp.getWriter().println("Last visit is: " + from);
                        break;
                    }
                }
            }
        }
    }
}
