package ttgs.rules;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import ttgs.main.MainLogger;

/**
 *
 * @author Vin
 */
public class Defaults {

    //defult constructor

    
    public static void setFromFile() {
        
        File file = new File("src/ttgs/rules/defaults.ttgs.xml");
        log("Opening file : "+file.getName());
        file.setReadable(true, true);
        setfromConfig(file);
    }
    

    private static void setfromConfig(File file) {
       new FileDefaults(file).configure();
    }

    public static class TimeDefaults {

        //start time of the lectures in a day

        public static LocalTime startTimeofDay = LocalTime.of(7, 00);
        //end time of all the lectures in a day
        public static LocalTime endTimeofDay = LocalTime.of(18, 00);
        //start of small break
        public static LocalTime startofSmallBreak = LocalTime.of(9, 00);
        //start of big break
        public static LocalTime startofBigBreak = LocalTime.of(11, 00);
        //start of lunch break
        public static LocalTime startLunchBreak = LocalTime.of(13, 00);
        //start of games break
        public static LocalTime startofGamesBreak = LocalTime.of(16, 00);
        //duration of a day
        public static Duration lectureDuration = Duration.ofHours(2);
        //duration of the lunch break
        public static Duration lunchDuration = Duration.ofHours(1);
        //duration of the big break
        public static Duration bigbreakDuration = Duration.ofMinutes(30);
        //duration of the small break
        public static Duration smallbreakDuration = Duration.ofMinutes(20);

        public static LocalTime getStartofSmallBreak() {
            return startofSmallBreak;
        }

        public static void setStartofSmallBreak(LocalTime startofSmallBreak) {
            TimeDefaults.startofSmallBreak = startofSmallBreak;
        }

        public static LocalTime getStartofBigBreak() {
            return startofBigBreak;
        }

        public static void setStartofBigBreak(LocalTime startofBigBreak) {
            TimeDefaults.startofBigBreak = startofBigBreak;
        }

        public static LocalTime getStartLunchBreak() {
            return startLunchBreak;
        }

        public static void setStartLunchBreak(LocalTime startLunchBreak) {
            TimeDefaults.startLunchBreak = startLunchBreak;
        }

        public static LocalTime getStartofGamesBreak() {
            return startofGamesBreak;
        }

        public static void setStartofGamesBreak(LocalTime startofGamesBreak) {
            TimeDefaults.startofGamesBreak = startofGamesBreak;
        }

        public static LocalTime getStartTimeofDay() {
            return startTimeofDay;
        }

        public static void setStartTimeofDay(LocalTime startTimeofDay) {
            TimeDefaults.startTimeofDay = startTimeofDay;
        }

        public static LocalTime getEndTimeofDay() {
            return endTimeofDay;
        }

        public static void setEndTimeofDay(LocalTime endTimeofDay) {
            TimeDefaults.endTimeofDay = endTimeofDay;
        }

        public static Duration getLectureDuration() {
            return lectureDuration;
        }

        public static void setLectureDuration(Duration lectureDuration) {
            TimeDefaults.lectureDuration = lectureDuration;
        }

        public static Duration getLunchDuration() {
            return lunchDuration;
        }

        public static void setLunchDuration(Duration lunchDuration) {
            TimeDefaults.lunchDuration = lunchDuration;
        }

        public static Duration getBigbreakDuration() {
            return bigbreakDuration;
        }

        public static void setBigbreakDuration(Duration bigbreakDuration) {
            TimeDefaults.bigbreakDuration = bigbreakDuration;
        }

        public static Duration getSmallbreakDuration() {
            return smallbreakDuration;
        }

        public static void setSmallbreakDuration(Duration smallbreakDuration) {
            TimeDefaults.smallbreakDuration = smallbreakDuration;
        }
    }

    public static class LectureDefaults {

        public static final int LECTURE_IS_ONGOING = 1;
        public static final int LECTURE_IS_MISSED = 2;
    }
    
    public static class SortDefaults {
        
        public static final int ACSENDING = 0;
        public static final int DESCENDING = 1;
    }

    public static class RoomDefaults {

        public static final int NORMAL = 1;
        public static final int BIG = 2;
        public static final int LAB = 3;
        public static final int HALL = 4;
    }

    public static class BreakDefaults {

        public static final int SMALLBREAK = 1;
        public static final int BIGBREAK = 2;
        public static final int LUNCHBREAK = 3;
        public static final int GAMESBREAK = 4;
    }

    public static class InstitutionDefaults {

        public static String uniName = null;
        public static int numOfSchools = 0;

        public static String getUniName() {
            return uniName;
        }

        public static void setUniName(String uniName) {
            InstitutionDefaults.uniName = uniName;
        }

        public static int getNumOfSchools() {
            return numOfSchools;
        }

        public static void setNumOfSchools(int numOfSchools) {
            InstitutionDefaults.numOfSchools = numOfSchools;
        }
    }    
    public static void setDefaults(LocalTime start, LocalTime end, LocalTime small, LocalTime big, LocalTime lunch,
        LocalTime games, Duration smbd, Duration bbd, String uniname, String num) {
        log("Setting new default values");

        // now set the variables 
        log("Setting start time of day activities : "+start.getHour());
        TimeDefaults.setStartTimeofDay(start);
        log("Setting end time of day activities : "+end.getHour());        
        TimeDefaults.setEndTimeofDay(end);
        
        TimeDefaults.setStartofSmallBreak(small);
        TimeDefaults.setStartofBigBreak(big);
        TimeDefaults.setStartLunchBreak(lunch);
        TimeDefaults.setStartofGamesBreak(games);
        TimeDefaults.setSmallbreakDuration(smbd);
        TimeDefaults.setBigbreakDuration(bbd);
        log("Setting university name : "+uniname);
        InstitutionDefaults.setUniName(uniname);
        log("Setting number of schools : "+num);
        InstitutionDefaults.setNumOfSchools(Integer.parseInt(num));
    }
    private static void log(String s) {
        MainLogger.logger("Defaults > "+s);
    }
}
