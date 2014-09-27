package org.eu.ingwar.appengine.calendar;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.repackaged.com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
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
        
        Preconditions.checkNotNull(query.getStart());

        Calendar cal = Calendar.getInstance();
        cal.setTime(query.getStart());

        Plan res = new Plan();

        while (visits > 0) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
            
            if (days.get(dayOfWeek).getSelected()) {
                boolean holiday = ServiceUtils.isHoliday(cal.getTime());
                if (!holiday) {
                    visits--;
                    res.addIntern(cal.getTime());
                }
                if (visits == 0) {
                    res.setLast(cal.getTime());
                    break;
                }
            }
        }
        return res;
    }
}
