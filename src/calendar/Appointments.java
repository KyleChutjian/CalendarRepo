package calendar;

import java.util.*;
import java.io.*;

/** 
 * This class represents the appointments.  They are kept in sorted order.
 * The class contains methods to manipulate the calendar.
 *
 */
public class Appointments {
	
	private ArrayList<ScheduleEvent> appointments;  // To hold all of the appointments
	private BufferedReader in;		 // Local copies of the streams
	private PrintWriter out;		 
	
	/**
	 * Creates a new Appoinments object.  This object acts as the actual set of 
	 * appointments.
	 *
	 * @param i The programs in stream
	 * @param o The programs out stream
	 */
	public Appointments(BufferedReader i, PrintWriter o) {
		in = i;
		out = o;
		appointments = new ArrayList<>(); 
	}

	/**
	 * This method uses user input to create a new appointment, and add it to the calendar.
	 *
	 */ 
	public void insert() {
		// To hold the various attributes of the new event.
		String name;
		String startTime;
		String stopTime;
		String date;
		ScheduleEvent newEvent; // The event to be added

    	name = InputCollectors.inputName(in, out, "Insert", "New appointment name?");
        for(;;) {
        	date = InputCollectors.inputDate(in, out, "Insert", "New appointment date?");
    		if(!CalendarUtils.precedesCurDate(date)) break;
    		out.println("	   INCORRECT VALUE FOR DATE!!  THAT DATE HAS PASSED");
    	} 
    	
    	for(;;) {	
    		startTime = InputCollectors.inputTime(in, out, "Insert",
    											   "New appointment start time?");
    		if(CalendarUtils.equalsCurDate(date)) {
    			if(!CalendarUtils.precedesCurTime(startTime)) break;
    			out.println("	   INCORRECT VALUE FOR TIME!!  THAT TIME HAS PASSED");
    		} else break;									   
    	}
    	
    	for(;;) { 
    		stopTime = InputCollectors.inputTime(in, out, "Insert", 
    											 "New appointment end time?");
     		if(CalendarUtils.precedingTime(startTime, stopTime)) break;
     		out.println("	   INCORRECT VALUE FOR STOP TIME!!  MUST FOLLOW START TIME");
     	}
     
    	newEvent = new ScheduleEvent(name, date, startTime, stopTime);
		
		appointments.add(this.findIndexFor(newEvent), newEvent);
	}
	
	/**
	 * Requests a time, date, and name, and deletes the appointment scheduled then.
	 *
	 */
	public void delete() {
		// To hold the various attributes of the deleted event
		String name;
		String startTime;
		String date;
    	int index = 0; // Index of event being examined
    	ScheduleEvent event; // Holds the even being examined
    
    	name = InputCollectors.inputName(in, out, "Delete", "Name of appointment?");
    	date = InputCollectors.inputDate(in, out, "Delete", "Date of appointment?");
    	startTime = InputCollectors.inputTime(in, out, "Delete", "Time of appointment?");
     	
     	while(index < appointments.size()) {
     		event = (ScheduleEvent)appointments.get(index);
     		if(name.equals(event.getName()) &&
     		   CalendarUtils.equalDates(event.getDate(), date) &&
     		   CalendarUtils.equalTimes(event.getStartTime(), startTime)) {
     		   appointments.remove(index);
     		}
     		index++;
     	}
     	out.println("      NO SUCH APPOINTMENT EXISTS");
	}
	
	/**
	 * Allows the user to change attributes of a particular appointment
	 *
	 */
	public void change() {
		// To hold the various attributes of the event to be changed
		String name;
		String startTime;
		String date;
		String newName;
		String newStart;
		String newStop;
		String newDate;
    	int index = 0; // Index of event being examined
    	ScheduleEvent event = null; // Holds the event being examined
    
    	name = InputCollectors.inputName(in, out, "Change", "Existing appointment name?");
    	date = InputCollectors.inputDate(in, out, "Change", "Existing appointment date?");
    	startTime = InputCollectors.inputTime(in, out, "Change", 
    										  "Existing appointment start time?");
     	
     	while(index < appointments.size()) {
     		event = (ScheduleEvent)appointments.get(index);
     		if(name.equals(event.getName()) &&
     		   CalendarUtils.equalDates(event.getDate(), date) &&
     		   CalendarUtils.equalTimes(event.getStartTime(), startTime)) break;
     		index++;
     	}
     	
     	if(index >= appointments.size()) {
     		out.println("      NO SUCH APPOINTMENT EXISTS");
     		return;
     	}
     	
     	newName = InputCollectors.inputName(in, out, "Change", 
     								     "New appointment name? [" + name + "] ");
    	newDate = InputCollectors.inputDate(in, out, "Change", 
    										"New appointment date? [" + date + "] ");
    	newStart = InputCollectors.inputTime(in, out, "Change",
    									"New appointment start time? [" + startTime + "] ");
    	for(;;) { 
    		newStop = InputCollectors.inputTime(in, out, "Change", 
    					       "New appointment stop time? [" + event.getStopTime() + "] ");    								    	
     		if(CalendarUtils.precedingTime(newStart, newStop)) break;
     		out.println("	   INCORRECT VALUE FOR STOP TIME!!  MUST FOLLOW START TIME");
     	}
     	
     	event.setName(newName);
     	event.setDate(newDate);
     	event.setStartTime(newStart);
     	event.setStopTime(newStop);
	}
	
	
	/**
	 * Loads the appointments in the file appointments.txt.
	 */
	public void load() {
		try {
			File appointmentFile = new File("appointments.txt");
			appointmentFile.createNewFile();
			BufferedReader readFile = new BufferedReader(
								  new FileReader(appointmentFile));
			String currentLine = readFile.readLine();
			while (currentLine != null) {
				ScheduleEvent tempEvent;
				StringTokenizer st = new StringTokenizer(currentLine,"*");
				String date = st.nextToken();
				String name = st.nextToken();
				String stop = st.nextToken();
				String start = st.nextToken();
				tempEvent = new ScheduleEvent(name, date, start, stop);
				appointments.add(tempEvent);
				currentLine = readFile.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error loading appointments: " + e.getMessage());
		}
	}
	
	
	/**
	 * Saves all of the appointments in the collection to the file appointments.txt
	 */
	public void save() {
		try {
			File appointmentFile = new File("appointments.txt");
			BufferedWriter fileOut = new BufferedWriter(
									 new FileWriter(appointmentFile));
			int numItems = this.appointments.size();
			for (int i = 0; i < numItems; i++) {
				ScheduleEvent currentEvent = (ScheduleEvent) appointments.get(i);
				fileOut.write(currentEvent.getDate() + "*");
				fileOut.write(currentEvent.getName() + "*");
				fileOut.write(currentEvent.getStartTime() + "*");
				fileOut.write(currentEvent.getStopTime() + "\n");
			}
			fileOut.close();
		} catch (Exception e) {
			System.out.println("Error saving appointments: " + e.getMessage());
		}
	}
	
	/**
	 * Requests a pair of date/time restraints from the user, and prints all appointsments 
	 * that begin within those dates.
	 */
	public void print() {
		// To hold the bounding dates and times of the print
		String startDate;
		String startTime;
		String stopDate;
		String stopTime;
        // Events for the start and stop events, so that they are compatible with existing constructs
		ScheduleEvent start;
		ScheduleEvent stop;
		int index = 0; // Current index to print
		ScheduleEvent curEvent; // The current event in the iteration 
		String printDate = "00/00/0000"; // The last date that was printed 
		
		startDate = InputCollectors.inputDate(in, out, "Print", "Starting date?");
		startTime = InputCollectors.inputTime(in, out, "Print", "Starting time?");
		start = new ScheduleEvent("Start", startDate, startTime, startTime);
		for(;;) { 
    		stopDate = InputCollectors.inputDate(in, out, "Print", "Ending date?");
     		if(CalendarUtils.precedingDate(stopDate, startDate)) break;
     		out.println("	   INCORRECT VALUE FOR STOP DATE!!  CANNOT PRECEDE STARTDATE");
     	} 		
		for(;;) { 
    		stopTime = InputCollectors.inputTime(in, out, "Print", "Ending time?");
     		if(!CalendarUtils.precedingTime(startTime, stopTime)) break;
     		if(!CalendarUtils.equalDates(startDate, stopDate)) break;
     		out.println("	   INCORRECT VALUE FOR STOP TIME!!  MUST FOLLOW START TIME");
     	}
     	stop = new ScheduleEvent("Stop", stopDate, stopTime, stopTime);
     	
     	index = this.findIndexFor(start);
     	
     	while(index < appointments.size()) {
     		curEvent = (ScheduleEvent)appointments.get(index);
     		if(CalendarUtils.precedingEvent(stop, curEvent)) break;
     		if(!CalendarUtils.equalDates(printDate, curEvent.getDate())) {
     			printDate = curEvent.getDate();
     			out.println(printDate);
     		}
     		out.println(curEvent.getStartTime() + "   " + curEvent.getName());
			index++;
     	}
     } 		
	
	/**
	 * Finds the index at which this event would be inserted.
	 * 
	 * @param The event to place in the sorted ArrayList of ScheduleEvents
	 * @return The index at which this event would be inserted
	 */
	private int findIndexFor(ScheduleEvent event) {
		int index = 0; // The current index being examined.
		ScheduleEvent compare; // To store the current comparison item
		
		while(index < appointments.size()) {
			compare = (ScheduleEvent)appointments.get(index);

			if(!CalendarUtils.precedingEvent(compare, event)) 
				return index;
			if(CalendarUtils.concurrentEvents(compare, event)) 
				return index;
			index++;
		}

		return index;		
	}
	
}
