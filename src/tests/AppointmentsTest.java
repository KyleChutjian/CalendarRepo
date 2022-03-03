package tests;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.*;

import calendar.Appointments;
import calendar.CalendarUtils;
import calendar.ScheduleEvent;

public class CalendarUtilsTest {
	
	Appointments appointments;
	
	@Before
	public void setUp() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(System.out, true);
		BufferedReader in = new BufferedReader(new FileReader("test.txt"));
		appointments = new Appointments(in, out);
	}
	
	@Test 
	public void insertDeleteChange() {
		//Test insert -- lines 1-11 in test.txt
		appointments.insert(); 
		Assert.assertEquals(appointments.getApp().size(), 1);
		appointments.insert();
		Assert.assertEquals(appointments.getApp().size(), 2);
		
		//Test delete -- lines 12-23 in test.txt
		appointments.delete();
		Assert.assertEquals(appointments.getApp().size(), 1);
		appointments.delete();
		appointments.delete();
		appointments.delete();
		Assert.assertEquals(appointments.getApp().size(), 1);
		
		//Test change -- lines 24-40 in test.txt
		appointments.change();
		Assert.assertEquals(appointments.getApp().get(0).getName(), "newtest");
		appointments.change();
		appointments.change();
		appointments.change();
		Assert.assertEquals(appointments.getApp().get(0).getName(), "newtest");
	}
	
	@Test
	public void validDate() {
		//try catch block
		Assert.assertFalse(CalendarUtils.validDate("0220/1900"));
		
		//first if check
		Assert.assertFalse(CalendarUtils.validDate("00/12/1900"));
		Assert.assertFalse(CalendarUtils.validDate("13/12/1900"));
		Assert.assertFalse(CalendarUtils.validDate("01/00/1900"));
		
		//second if check
		Assert.assertFalse(CalendarUtils.validDate("01/33/1900"));
		
		//third if check
		Assert.assertFalse(CalendarUtils.validDate("04/31/1900"));
		Assert.assertFalse(CalendarUtils.validDate("06/31/1900"));
		Assert.assertFalse(CalendarUtils.validDate("09/31/1900"));
		Assert.assertFalse(CalendarUtils.validDate("11/31/1900"));
		Assert.assertTrue(CalendarUtils.validDate("04/30/1900"));
		
		//fourth if check
		Assert.assertFalse(CalendarUtils.validDate("02/29/1900"));
		Assert.assertFalse(CalendarUtils.validDate("02/29/1901"));
		Assert.assertFalse(CalendarUtils.validDate("02/30/2024"));
		Assert.assertTrue(CalendarUtils.validDate("03/30/400"));
		Assert.assertTrue(CalendarUtils.validDate("02/24/400"));
		Assert.assertTrue(CalendarUtils.validDate("02/24/2021"));
		Assert.assertTrue(CalendarUtils.validDate("02/25/2024"));
		Assert.assertTrue(CalendarUtils.validDate("02/29/1904"));
		
		//last if check
		Assert.assertFalse(CalendarUtils.validDate("05/24/5000"));
		Assert.assertFalse(CalendarUtils.validDate("05/24/-1"));
		
	}

	@Test
	public void validTime() {
		Assert.assertFalse(CalendarUtils.validTime(""));
		Assert.assertTrue(CalendarUtils.validTime("1:00"));
		
		//if check
		Assert.assertFalse(CalendarUtils.validTime("-1:0"));
		Assert.assertFalse(CalendarUtils.validTime("0:-1"));
		Assert.assertFalse(CalendarUtils.validTime("25:00"));
		Assert.assertFalse(CalendarUtils.validTime("00:65"));
	}
	
	@Test
	public void precedingDate() {
		
		//first if check
		Assert.assertTrue(CalendarUtils.precedingDate("3/2/2022", "2/20/2023"));
		
		//second if check
		Assert.assertFalse(CalendarUtils.precedingDate("3/2/2023", "2/20/2022"));
		Assert.assertTrue(CalendarUtils.precedingDate("2/20/2022", "3/2/2022"));
		
		//third if check
		Assert.assertTrue(CalendarUtils.precedingDate("3/2/2022", "3/3/2022"));
		Assert.assertFalse(CalendarUtils.precedingDate("3/3/2022", "3/2/2022"));
		Assert.assertFalse(CalendarUtils.precedingDate("3/2/2022", "2/20/2022"));
	}
	
	@Test
	public void precedingEvent() {
		//first if check
		Assert.assertTrue(CalendarUtils.precedingEvent(
				new ScheduleEvent("test", "3/2/2022", "0", "0"),
				new ScheduleEvent("test2", "3/3/2022", "0", "0")
				));
		Assert.assertFalse(CalendarUtils.precedingEvent(
				new ScheduleEvent("test", "3/3/2022", "0", "0"),
				new ScheduleEvent("test2", "3/2/2022", "0", "0")
				));
		
		//second if check
		Assert.assertTrue(CalendarUtils.precedingEvent(
				new ScheduleEvent("test", "3/2/2022", "1:00", "2:00"),
				new ScheduleEvent("test2", "3/2/2022", "3:00", "4:00")
				));
		Assert.assertFalse(CalendarUtils.precedingEvent(
				new ScheduleEvent("test2", "3/2/2022", "3:00", "4:00"),
				new ScheduleEvent("test", "3/2/2022", "1:00", "2:00")
				
				));
	}
}
