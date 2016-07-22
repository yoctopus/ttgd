package ttgs.rules.exception;

/**
 *
 * @author Vin
 */
public class TTGSExceptions extends Exception {
    /**
     * Creates a new instance of <code>TTGSExceptions</code> without detail
     * message.
     */
    public TTGSExceptions() { this(null); }
    /**
     * Constructs an instance of <code>TTGSExceptions</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TTGSExceptions(String msg) { super(msg);}
    public static class InvalidTimeArguments extends TTGSExceptions {
        public InvalidTimeArguments() {super("Invalid time arguments !!!");}
    }
    public static class LectureOutOfBoundsException extends TTGSExceptions {
        public LectureOutOfBoundsException() {super("A Lecture is out of bounds !!!");}
    }
    public static class RoomActiveException extends TTGSExceptions {
        public RoomActiveException() { super("This room cant accomodate any lectures now!!!"); }
    }
    
    public static class TimeIsOccupiedException extends TTGSExceptions {
        public TimeIsOccupiedException() { super("This time is already occupied with a lecture");}
    }
    
    public static class LectureBusyException extends TTGSExceptions {
        public LectureBusyException() { super(" Lecturer is on another lecture");}
    } 
}
