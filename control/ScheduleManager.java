package control;

import java.util.Calendar;

public class ScheduleManager {
	public static boolean isOverlapping(Calendar periodStart, Calendar periodEnd, Calendar period2Start, Calendar period2End) {
	    return periodStart.before(period2End) && period2Start.before(period2End);
	}
}
