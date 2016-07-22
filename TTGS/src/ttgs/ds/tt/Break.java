package ttgs.ds.tt;

import java.time.Duration;
import java.util.Date;
import ttgs.algorithms.Engine;
import ttgs.rules.Defaults.TimeDefaults;
import ttgs.rules.Defaults.BreakDefaults;
/**
 *
 * @author Vin
 */
public class Break {   
    public static final int SMALLBREAK = BreakDefaults.SMALLBREAK, BIGBREAK = BreakDefaults.BIGBREAK; 
    public static final int LUNCHBREAK = BreakDefaults.LUNCHBREAK, GAMESBREAK = BreakDefaults.GAMESBREAK;
    private Duration breakDuration;
    private TTGSDate startTime, endTime;
    public Duration getBreakDuration() {
        return breakDuration;
    }
    public void setBreakDuration(Duration breakDuration) {
        this.breakDuration = breakDuration;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(TTGSDate startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(TTGSDate endTime) {
        this.endTime = endTime;
    }
    private Break(Duration duration) {this.setBreakDuration(breakDuration);}
    public Break(TTGSDate start, TTGSDate end) {
        this(Engine.calcDurationBetween(start, end));
        this.setStartTime(start);
        this.setEndTime(end);        
    }
}
