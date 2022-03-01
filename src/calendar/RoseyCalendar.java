package calendar;
/**
 * RoseyCalendar.java 1.0
 *
 */

import java.io.*;

/**
 * The class which serves as the primary object for this project.
 *
 * @author Andrew Boese.
 */ 
public class RoseyCalendar {

	/**
	 * The application starts here in MAIN;
	 * MAIN contains the read-eval-print loop for the command line driven 
	 * program (version 1.0 only).
	 *
	 * @param args  Array of command-line arguments
	 */ 
	public static void main(String args[]) {
		// Setup the streams
		PrintWriter out = new PrintWriter(System.out, true);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";  // For reading user input lines
		
		out.print("Welcome to Rosey Calendar!\n");
		out.print("Please remember that all times are in military time.\n\n");
		
		// Appointments is the actual calendar
	    Appointments appointments = new Appointments(in, out);
		appointments.load();
		
		for(;;) {
			out.print("% RC? ");
			out.flush();			
			try {
				line = in.readLine();
			} catch(Exception e) {
			}
			
			if(line.equalsIgnoreCase("insert"))
				appointments.insert();		
			else if(line.equalsIgnoreCase("delete"))
				appointments.delete();
			else if(line.equalsIgnoreCase("change"))
				appointments.change();
			else if(line.equalsIgnoreCase("print")) 
				appointments.print();
			else if(line.equalsIgnoreCase("quit")) {
				appointments.save();
				out.print("Thank you for using Rosey Calendar...\n\n");
				out.flush();
				return;
			} else 
				out.println(line + " is not a valid command.");
		}
	}
}
