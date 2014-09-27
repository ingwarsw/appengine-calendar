package org.eu.ingwar.appengine.calendar;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.appengine.repackaged.com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.eu.ingwar.appengine.calendar.obj.Day;
import org.eu.ingwar.appengine.calendar.obj.Plan;
import org.eu.ingwar.appengine.calendar.obj.PlanQuery;
import org.eu.ingwar.appengine.calendar.utils.ServiceUtils;

@Api(name = "visits")
public class VisitsApi {

    @ApiMethod(name = "plan")
    public Plan plan(PlanQuery query) throws IOException {
        Preconditions.checkNotNull(query);
        
        int visits = query.getCount();
        Preconditions.checkArgument(visits > 0);
        
        List<Day> days = query.getDays();
        Preconditions.checkNotNull(days);
        Preconditions.checkArgument(days.size() == 7);
        
        Date from = query.getStart();
        Preconditions.checkNotNull(from);
        
        Plan res = new Plan();

        while (visits > 0) {
            for (int i = 0; i < 7; i++) {
                from = DateUtils.addDays(from, 1);
                if (days.get(i).getSelected()) {
                    boolean holiday = ServiceUtils.isHoliday(from);
                    if (!holiday) {
                        visits--;
                        res.addIntern(from);
                    }
                    if (visits == 0) {
                        res.setLast(from);
                        break;
                    }
                }
            }
        }
        return res;
    }
    
    
    public void test() throws IOException {
        
        Calendar service = ServiceUtils.getCalendarInstance();

        com.google.api.services.calendar.model.Calendar model = new com.google.api.services.calendar.model.Calendar();
        model.setDescription("Pierwszy testowy");
        model.setSummary("Tescikowy kalendarz");
//        model.setId("en.polish#holiday@group.v.calendar.google.com");
//        String calId = service.calendars().insert(model).execute().getId();
//        resp.getWriter().println("Dodano kalendarz o id: " + calId);

        for (CalendarListEntry en : service.calendarList().list().execute().getItems()) {
//            resp.getWriter().println("CalList: " + en.getDescription() + ": " + en.getId());
            service.calendars().delete(en.getId()).execute();
        }
    }
}
