package control;

import java.util.Calendar;

public class ScheduleManager {
	public static boolean isOverlapping(Calendar testStart, Calendar testEnd, Calendar test2Start, Calendar test2End) {
	    return testStart.before(test2End) && test2Start.before(testEnd);
	}
}
