package ttgs.algorithms;

import java.time.Duration;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ttgs.ds.Course;
import ttgs.ds.Faculty;
import ttgs.ds.Room;
import ttgs.ds.School;
import ttgs.ds.ActiveUnit;
import ttgs.ds.Group;
import ttgs.ds.Unit;
import ttgs.ds.tt.Day;
import ttgs.ds.tt.TTGSDate;
import ttgs.ds.tt.Lecture;
import ttgs.ds.tt.TimeSlot;
import ttgs.ds.tt.GroupTimeTable;
import ttgs.ds.tt.WeekDays;
import ttgs.main.MainLogger;
import ttgs.rules.Defaults;
import ttgs.rules.exception.TTGSExceptions.InvalidTimeArguments;
import ttgs.rules.exception.TTGSExceptions.LectureOutOfBoundsException;

/**
 *
 * @author Vin
 */
public class Engine {

    private static ArrayList<Lecture> mondayLectures = new ArrayList();
    private static ArrayList<Lecture> tuesdayLectures = new ArrayList();
    private static ArrayList<Lecture> wednesdayLectures = new ArrayList();
    private static ArrayList<Lecture> thursdayLectures = new ArrayList();
    private static ArrayList<Lecture> fridayLectures = new ArrayList();

    //method calculating total of student from an array
    /**
     * calculates the number of student from a list of students iterates through
     * the students and counts each one by one
     *
     * @param students the student required
     * @return the number the students passed to it
     */
    public static int getTotalOfStudents(ArrayList<Group> students) {
        logActivity("Calculating total number of students\n\n");
        //calculating total of joint students;
        int temp = 0;
        for (Group some : students) {
            temp += Integer.parseInt(some.toString().trim());
        }
        return temp;
    }
    //method calculating end local time

    /**
     * adds a local time and duration of time using instance variables to
     * convert the inputs into longs and adds the longs together converts the
     * resulting long back to local time
     *
     * @param start the local time as one of the operands
     * @param duration the duration to be added to the local time
     * @return a local time of this addition
     */
    public static TTGSDate calcLocalEndTime(TTGSDate start, Duration duration) {

        //calculating end time given start time and duration
        LocalTime time = getLocalTimefromDate(start);

        return getDatefromLocalTime(calcLocalEndTime(time, duration));

    }

    public static LocalTime getLocalTimefromDate(TTGSDate date) {
        logActivity("Converting date " + date.toString() + " to localtime");
        int hour = date.getHours();
        int minute = date.getMinutes();
        return LocalTime.of(hour, minute);
    }

    public static LocalTime calcLocalEndTime(LocalTime time, Duration duration) {
        logActivity("Calculating time passed after " + duration.getSeconds() + " seconds since " + time.toString());
        int hour = time.getHour();
        int minute = time.getMinute();
        minute += duration.toMinutes();
        while (minute > 59) {
            hour++;
            minute -= 60;
        }
        return LocalTime.of(hour, minute);
    }
    //method calculating duration difference between two local times

    /**
     * calculates the difference between two local times as a duration throws an
     * error if the local time start is after end uses instant variable to hold
     * the local time and afterwards makes a duration of their difference
     *
     * @param start the smaller local time
     * @param end the bigger local time
     * @return duration as difference between the local times
     */
    public static Duration calcDurationBetween(TTGSDate start, TTGSDate end) {
        logActivity("Clculating duration between date " + start.toString() + " to " + end.toString());
        try {
            if (!end.after(start)) {
                throw new InvalidTimeArguments();
            } else {
                //calculating difference
                int starthour = start.getHours();
                int startmin = start.getMinutes();

                int starttime = calcMinutes(starthour, startmin);
                int endhour = end.getHours();
                int endmin = end.getMinutes();

                int endtime = calcMinutes(endhour, endmin);

                int difference = endtime - starttime;
                logActivity("Result = Duration of minutes " + difference);
                return Duration.ofMinutes(difference);
            }
        } catch (InvalidTimeArguments e) {
        }
        return null;
    }

    public static boolean RoomBelongsToSchool(Room r, School s) {
        logActivity("checking if Room " + r.getName() + " belonngs to school " + s.getName());
        return r.getSid() == s.getId();
    }

    public static boolean DepartmentBelongsToSchool(Faculty d, School s) {
        logActivity("checking if Department " + d.getName() + " belonngs to school " + s.getName());
        return d.getSch_id() == s.getId();
    }

    public static boolean CourseBelongsToDepartment(Course c, Faculty d) {
        logActivity("checking if Course " + c.getName() + " belonngs to department " + d.getName());
        return c.getDept_id() == d.getId();
    }

    public static boolean UnitBelongsToCourse(ActiveUnit u, Course c) {
        logActivity("checking if unit " + u.getName() + " belonngs to course " + c.getName());
        for (Unit unit : c.getUnits()) {
            if (unit.getCode().equals(u.getCode())) {
                return true;
            }
        }

        return false;
    }

    public static boolean StudentsDoCourse(Group s, Course c) {
        logActivity("checking if group " + s.toString() + " do course " + c.getName());
        return s.getCourse() == c;
    }

    /**
     * swaps two lecture positions in a Day
     *
     * @param a first lecture
     * @param b second lecture
     * @param dtt the timetable for swapping
     */
    public static void swapLectures(Lecture a, Lecture b) {
        logActivity("Swaping lecture for " + a.getSemunit().getName() + " with that for " + b.getSemunit().getName());

        Lecture temp = a;
        a = b;
        b = temp;
    }
    /*
     *calculates the duration between two localtimes
     * @param start first localtime
     * @param end second localtime
     */

    public static Duration calcDurationBetween(LocalTime start, LocalTime end) {
        TTGSDate s = getDatefromLocalTime(start);
        TTGSDate e = getDatefromLocalTime(end);
        Duration duration = calcDurationBetween(s, e);
        return duration;
    }

    /**
     * calculating total minutes from an hour and minute
     *
     * @param : hourhour
     * @param minminute
     * @return return new minutes
     */
    public static int calcMinutes(int hour, int min) {
        logActivity("Converting " + hour + " hours and " + min + " minutes to minutes");
        return (hour * 60) + min;
    }

    /**
     * this function converts the minutes given to the function ad returns an
     * array of hour and minute make an two element array to store the hour and
     * the minute
     *
     * @param minutes the number of minutes to be converted
     * @return the array f hour and minute after conversion
     */
    public static int[] getHourMinute(int minutes) {
        int[] ans = new int[2];
        if (minutes <= 60) {
            ans[0] = 0;
            ans[1] = minutes;
        } else if (minutes > 60) {
            ans[0] = 0;
            ans[1] = minutes;
            while (minutes > 60) {
                ans[0] += 1;
                ans[1] -= minutes;
                minutes -= 60;
            }
        } else {
            try {
                throw new InvalidTimeArguments();
            } catch (InvalidTimeArguments ex) {

            }
        }
        return ans;
    }

    /**
     * changing local time to a date object
     *
     * @param local time expected
     * @return new date object
     */
    public static TTGSDate getDatefromLocalTime(LocalTime t) {
        logActivity("Getting date from localtime " + t.toString());
        TTGSDate tdate = new TTGSDate();
        tdate.setHours(t.getHour());
        tdate.setMinutes(t.getMinute());
        return tdate;
    }

    /**
     * returns the time slots between start time and end time of day
     *
     * @param start : the start time
     * @param end : the end time
     * @param slot: the duration of each time slot
     * @return the time slots generated
     */
    public static ArrayList<TimeSlot> calcTimeSlots(TTGSDate start, TTGSDate end, Duration slot) {
        logActivity("Calculating time slots from " + start.toString() + " to " + end.toString() + " of each span of " + slot.toMinutes() + " minutes");
        TTGSDate nstart = start;
        TTGSDate nend = end;
        Duration nslot = slot;
        try {
            if (nstart.after(nend) || nslot.getNano() < calcDurationBetween(nstart, nend).getNano()) {
                throw new InvalidTimeArguments();
            } else {
                ArrayList<TimeSlot> slots = new ArrayList();
                TTGSDate i = nstart;
                while (DatesEqual(i, nend)) {
                    if (getLocalTimefromDate(i) != Defaults.TimeDefaults.getStartLunchBreak()) {
                        TimeSlot t = new TimeSlot(i, nslot);
                        logActivity("Time:: " + t.toString());
                        slots.add(t);
                        i = calcLocalEndTime(i, nslot);
                    } else {
                        i = calcLocalEndTime(i, Defaults.TimeDefaults.getLunchDuration());
                    }
                }
                logActivity("Slots generated\n\n");
                for (TimeSlot sl : slots) {
                    logActivity(sl.toString());
                }
                return slots;
            }
        } catch (InvalidTimeArguments i) {
            logActivity("Could not generate timeslots, possible cause 'invalid configuration from defaults.ttgs.xml'");
        }

        return null;
    }

    static boolean DatesEqual(Date a, Date b) {
        return a.equals(b) || a.before(b);
    }

    /**
     *
     * @param tt the timetable to be shuffled
     * @param slots the number of slots in a day
     */
    public static void shuffle(GroupTimeTable tt, ArrayList<TimeSlot> slots) {
        logActivity("Shuffling events for timetable for " + tt.getStudents().getCourse().getName());
        ArrayList<Lecture> lectures = new ArrayList();
        for (Day dtt : tt.getTimetable()) {
            for (Lecture l : dtt.getLectures()) {
                lectures.add(l);
            }
        }
        //code to be implemented
        if (lectures.size() % 5 == 0) {
            logActivity("Found lectures are a multiple of five and distributing equally for five daya");
            List<Lecture> l = new ArrayList();
            int factor = lectures.size() / 5;
            int begin = 0;
            int end = factor - 1;
            l = lectures.subList(begin, end);
            ArrayList<Lecture> monlecs = new ArrayList();
            logActivity("Setting lectures for monday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.MON.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                monlecs.add(lec);
            }

            setMondayLectures(monlecs);
            spreadLecturesinTime(getMondayLectures(), slots);
            begin = end + 1;
            end += factor;
            l = lectures.subList(begin, end);
            ArrayList<Lecture> tuelecs = new ArrayList();
            logActivity("Setting lectures for tuesday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.TUE.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                tuelecs.add(lec);
            }
            setTuesdayLectures(tuelecs);
            spreadLecturesinTime(getTuesdayLectures(), slots);
            begin = end + 1;
            end += factor;
            l = lectures.subList(begin, end);
            ArrayList<Lecture> wenlecs = new ArrayList();
            logActivity("Setting lectures for wednessday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.WED.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                wenlecs.add(lec);
            }
            setWednesdayLectures(wenlecs);
            spreadLecturesinTime(getWednesdayLectures(), slots);
            begin = end + 1;
            end += factor;
            l = lectures.subList(begin, end);
            ArrayList<Lecture> thulecs = new ArrayList();
            logActivity("Setting lectures for thursday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.THU.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                thulecs.add(lec);
            }
            setThursdayLectures(wenlecs);
            spreadLecturesinTime(getThursdayLectures(), slots);
            begin = end + 1;
            end += factor;
            l = lectures.subList(begin, end);
            ArrayList<Lecture> frilecs = new ArrayList();
            logActivity("Setting lectures for friday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.FRI.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                frilecs.add(lec);
            }
            setFridayLectures(wenlecs);
            spreadLecturesinTime(getFridayLectures(), slots);

        } else {
            logActivity("Found lectures are not a multiple of five and hence distributing randomly");
            ArrayList<Integer> numbers = getLecturesNumbers(lectures.size(), slots.size());
            List<Lecture> l;
            int factor = numbers.get(0);
            int begin = 0;
            int end = factor - 1;
            l = lectures.subList(begin, end);
            ArrayList<Lecture> monlecs = new ArrayList();
            logActivity("Setting lectures for monday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.MON.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                monlecs.add(lec);
            }
            setMondayLectures(monlecs);
            spreadLecturesinTime(getMondayLectures(), slots);
            begin = end + 1;
            end += numbers.get(1);
            l = lectures.subList(begin, end);
            ArrayList<Lecture> tuelecs = new ArrayList();
            logActivity("Setting lectures for tuesday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.TUE.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                tuelecs.add(lec);
            }
            setTuesdayLectures(tuelecs);
            spreadLecturesinTime(getTuesdayLectures(), slots);
            begin = end + 1;
            end += numbers.get(2);
            l = lectures.subList(begin, end);
            ArrayList<Lecture> wenlecs = new ArrayList();
            logActivity("Setting lectures for wednesday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.WED.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                wenlecs.add(lec);
            }
            setWednesdayLectures(wenlecs);
            spreadLecturesinTime(getWednesdayLectures(), slots);
            begin = end + 1;
            end += numbers.get(3);
            l = lectures.subList(begin, end);
            ArrayList<Lecture> thulecs = new ArrayList();
            logActivity("Setting lectures for thursday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.THU.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                thulecs.add(lec);
            }
            setThursdayLectures(wenlecs);
            spreadLecturesinTime(getThursdayLectures(), slots);
            begin = end + 1;
            end += numbers.get(4);
            l = lectures.subList(begin, end);
            ArrayList<Lecture> frilecs = new ArrayList();
            logActivity("Setting lectures for friday");
            for (Lecture lec : l) {
                lec.setDay(WeekDays.FRI.name());
                logActivity("lecture for " + lec.getSemunit().getCode());
                frilecs.add(lec);
            }
            setFridayLectures(wenlecs);
            spreadLecturesinTime(getFridayLectures(), slots);
        }
    }

    private static ArrayList<Integer> getLecturesNumbers(int number, int bound) {
        logActivity("Getting lecture numbers randomly");
        int n = number;
        ArrayList<Integer> numbers = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Random random = new Random(n);
            int num = random.nextInt(bound);
            numbers.add(num);
            n -= num;
        }
        return numbers;
    }

    private static void spreadLecturesinTime(ArrayList<Lecture> lectures, ArrayList<TimeSlot> slots) {
        logActivity("Spreding lectures evenly for a day");
        try {
            if (lectures.size() > slots.size()) {
                throw new LectureOutOfBoundsException();
            } else {
                if (lectures.size() == slots.size() || lectures.size() / 2 > slots.size() / 2) {
                    return;
                } else {
                    for (int i = 1; i < lectures.size(); i += 2) {
                        lectures.get(i).setSlot(slots.get(i));
                    }

                }
            }
        } catch (LectureOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public static void SortUnits(ArrayList<ActiveUnit> units, int mode) {
        logActivity("Sorting units");

        Collections.sort(units);
    }

    public static void sortSchools(ArrayList<School> schools) {
        logActivity("Sorting schools with id");
        Collections.sort(schools);
    }

    public static ArrayList<Lecture> getMondayLectures() {
        return mondayLectures;
    }

    public static void setMondayLectures(ArrayList<Lecture> aMondayLectures) {
        mondayLectures = aMondayLectures;
    }

    public static ArrayList<Lecture> getTuesdayLectures() {
        return tuesdayLectures;
    }

    public static void setTuesdayLectures(ArrayList<Lecture> aTuesdayLectures) {
        tuesdayLectures = aTuesdayLectures;
    }

    public static ArrayList<Lecture> getWednesdayLectures() {
        return wednesdayLectures;
    }

    public static void setWednesdayLectures(ArrayList<Lecture> aWednesdayLectures) {
        wednesdayLectures = aWednesdayLectures;
    }

    public static ArrayList<Lecture> getThursdayLectures() {
        return thursdayLectures;
    }

    public static void setThursdayLectures(ArrayList<Lecture> aThursdayLectures) {
        thursdayLectures = aThursdayLectures;
    }

    public static ArrayList<Lecture> getFridayLectures() {
        return fridayLectures;
    }

    public static void setFridayLectures(ArrayList<Lecture> aFridayLectures) {
        fridayLectures = aFridayLectures;
    }

    private static void logActivity(String activity) {
        MainLogger.logger("Methods > " + activity);
    }
}
