package xyz.innky.graduationproject.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimePeriod {
    private long start;
    private long end;
    private  static final List<TimePeriod> timePeriods = new ArrayList<>(8);
    static {
        timePeriods.add(new TimePeriod(gett(8, 0), gett(8, 45)));
        timePeriods.add(new TimePeriod(gett(8, 50), gett(9, 35)));
        timePeriods.add(new TimePeriod(gett(9, 55), gett(0, 40)));
        timePeriods.add(new TimePeriod(gett(10, 45), gett(11, 30)));
        timePeriods.add(new TimePeriod(gett(14, 0), gett(14, 45)));
        timePeriods.add(new TimePeriod(gett(14, 50), gett(15, 35)));
        timePeriods.add(new TimePeriod(gett(15, 55), gett(16, 40)));
        timePeriods.add(new TimePeriod(gett(16, 45), gett(17, 30)));
    }

    public static long gett(int h, int m){
        return (h * 60L + m)*60L*1000L;
    }
    private TimePeriod(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static TimePeriod getInstance(int i) {
        return timePeriods.get(i);
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
