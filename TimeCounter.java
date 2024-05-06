/*
 * @author ArtjomsBogatirjovs
 */


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeCounter {
    //Artjoms Bogatirjovs PD1
    //Time counter as utility class for time calculation of method execution.
    private long startTime;
    private long endTime;
    private LocalTime startLocalTime;
    private String task;

    public TimeCounter(String task) {
        this.task = task;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void start() {
        startTime = System.nanoTime();
        startLocalTime = LocalTime.now();
        System.out.println("\nStart Time of " + task + ": " + startLocalTime.format(formatter));
    }

    public void stop() {
        endTime = System.nanoTime();
        LocalTime stopLocalTime = LocalTime.now();
        System.out.println("Stop Time: " + stopLocalTime.format(formatter));

        long elapsedTime = endTime - startTime;
        double seconds = elapsedTime / 1_000_000_000.0; // Convert nanoseconds to seconds
        System.out.printf(task + " takes: %.3f seconds%n", seconds);
    }
}
