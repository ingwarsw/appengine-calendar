package org.eu.ingwar.appengine.calendar.obj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Karol Lassak 'Ingwar' <karol.lassak@coi.gov.pl>
 */
@Data
public class Plan {
    private Date last;
    private List<Date> interns;
    
    public void addIntern(Date date) {
        if (interns == null) {
            interns = new ArrayList<>();
        }
        interns.add(date);
    }
}
