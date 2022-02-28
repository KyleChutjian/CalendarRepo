
public class ScheduleEvent {
	
	private String date;		// Some date, in the form mm/dd/yyyy
	private String startTime;	// hh:mm, times are in military time
	private String stopTime;		 
    private String name;		// Descriptive name of the particular event
    
    /**
     * Creates a new ScheduleEvent
     * @param _name Descriptive name of the particular event
     * @param _date The day of the month as an integer
     * @param _startTime hh:mm, start time in military time
     * @param _stopTime hh:mm, stop time in military time
     */
    public ScheduleEvent(String _name, String _date, String _startTime, String _stopTime) {		
 	   	name = _name;
    	date = _date;
    	startTime = _startTime;
    	stopTime = _stopTime;
    }
    
    /**
     * Returns the start time of the event
     *
     * @return The start time of the event
     */
    public String getStartTime() {
    	return startTime;
    }
    
    /**
     * Returns the stop time of the event
     *
     * @return The stop time of the event
     */
    public String getStopTime() {
    	return stopTime;
    }
    
    /**
     * Returns the date of the event
     *
     * @return The date of the event
     */
    public String getDate() {
    	return date;
    }
        
    /**
     * Returns the name of the event
     *
     * @return The name of the event
     */	
    public String getName() {
    	return name;
    }
    
    /**
     * Sets the name of the event
     *
     * @param The new name of the event
     */
    public void setName(String _name) {
    	name = _name;
    }
    
    /**
     * Sets the date of the event
     *
     * @param The new date of the event
     */
    public void setDate(String _date) {
    	date = _date;
    }
    
    /**
     * Sets the start time of the event
     *
     * @param The new start time of the event
     */
    public void setStartTime(String _startTime) {
    	startTime = _startTime;
    }
    
    /**
     * Sets the stop time of the event
     *
     * @param The new stop time of the event
     */
    public void setStopTime(String _stopTime) {
    	stopTime = _stopTime;
    }
   	
}
