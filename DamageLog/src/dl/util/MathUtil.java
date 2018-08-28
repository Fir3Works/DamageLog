package dl.util;

public class MathUtil {
	
	public static double roundToQuarter(double d) {
		return Math.round(d*4)/4f;
	} 
	public static double roundToTenth(double d) {
		return Math.round(d*10)/10d;
	}
}
