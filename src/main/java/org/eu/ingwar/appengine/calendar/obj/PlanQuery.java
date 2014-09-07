package org.eu.ingwar.appengine.calendar.obj;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Karol Lassak 'Ingwar' <karol.lassak@coi.gov.pl>
 */
@Data
public class PlanQuery {
    private Date start;
    private int count;
    private List<Boolean> days;
}
