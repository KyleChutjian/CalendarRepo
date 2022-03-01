package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import calendar.ScheduleEvent;

public class AppointmentsTest {

	ArrayList<ScheduleEvent> appointments;
	Scanner IO = new Scanner(System.in);
	
	@Before
	public void setUp() throws Exception {
		appointments = new ArrayList<>();
	}

	@Test
	public void testInsert() {
		InputStream resetInput = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("Test".getBytes());
		System.setIn(in);
		
		
		
		
		Scanner nameInput = new Scanner(in);
		System.out.println(nameInput);
		assertEquals("Test", nameInput);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testChange() {
		fail("Not yet implemented");
	}

}
