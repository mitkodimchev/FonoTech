package mainPackage;
import java.time.Duration;

public class DurationFono {
	public static Duration durationOfSong(long minutes, long seconds){
		Duration durationMinutes = Duration.ofMinutes(minutes);
		Duration durationSeconds = Duration.ofSeconds(seconds);
		Duration totalDuration = Duration.ofSeconds(0);
		totalDuration = totalDuration.plus(durationSeconds).plus(durationMinutes);
		//String duration = String.format("%d:%02d%n", totalDuration.toMinutes(), totalDuration.minusMinutes(totalDuration.toMinutes()).getSeconds());
		//System.out.println("Duration: "+duration);
	    return totalDuration;
	}
<<<<<<< HEAD
	public static String toString(Duration duration){
=======
	public static String durationToString(Duration duration){
>>>>>>> origin/master
		if(duration!=null)
			return String.format("%d:%02d", duration.toMinutes(), duration.minusMinutes(duration.toMinutes()).getSeconds());
		else
			return "0:00";
	}
<<<<<<< HEAD
	public static Duration toDuration(String duration){
=======
	public static Duration stringToDuration(String duration){
>>>>>>> origin/master
		Duration length;
		String[] minAndSec = duration.split(":");
		length = durationOfSong (Long.parseLong(minAndSec[0]), Long.parseLong(minAndSec[1]));
		return length;
	}
	
}
