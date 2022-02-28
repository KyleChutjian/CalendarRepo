
import java.io.*; 

/**
 * This class contains all of the methods that are used to get input from the user.
 * Each of these methods includes the request, as well as error checking of the input.
 *
 */
public class InputCollectors {
	
	/**
	 * This method gets an event name from the user.
	 *
	 * @param in Input stream
	 * @param out Output stream
	 * @param command The name of the command that initiated this input
	 * @param request The question to pose to the user
	 * @return The name supplied by the user
	 */
	public static String inputName(BufferedReader in, PrintWriter out, String command,
								   String request) {
		String name = ""; // For reading user input lines
		
		for(;;) {
			out.print(command + "> " + request + " ");
			out.flush();
			try {
				name = in.readLine();
			} catch(Exception e) {
			}
			if(!name.equals("")) return name;
			out.println("      INCORRECT VALUE FOR NAME!!");
		}	
	}
	
	/**
	 * This method gets an event date from the user.
	 *
	 * @param in Input stream
	 * @param out Output stream
	 * @param command The name of the command that initiated this input
	 * @param request The question to pose to the user
	 * @return The date supplied by the user
	 */
	public static String inputDate(BufferedReader in, PrintWriter out, String command,
								   String request) {
		String date = ""; // For reading user input lines
		
		for(;;) {
			out.print(command + "> " + request + " ");
			out.flush();
			try {
				date = in.readLine();
			} catch(Exception e) {
			}
			if(CalendarUtils.validDate(date)) return date;
			out.println("      INCORRECT VALUE FOR DATE!!  MUST BE OF FORM MM/DD/YYYY");	
		}
	}
	
	/**
	 * This method gets an event start time from the user.
	 *
	 * @param in Input stream
	 * @param out Output stream
	 * @param command The name of the command that initiated this input
	 * @param request The question to pose to the user
	 * @return The time supplied by the user
	 */
	public static String inputTime(BufferedReader in, PrintWriter out, String command,
								   String request) { 
		String time = ""; // For reading user input
		
		for(;;) {
			out.print(command + "> " + request + " ");
			out.flush();
			try {
				time = in.readLine();
			} catch(Exception e) {
			}
			if(CalendarUtils.validTime(time)) return time;
			out.println("      INCORRECT VALUE FOR TIME!!  MUST BE OF FORM HH:MM");	
		}
	}
	
}
