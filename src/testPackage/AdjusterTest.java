package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import fsss_package.Adjuster;

public class AdjusterTest {

	@Test
	public void testAdjuster() {
		Adjuster obj = new Adjuster();
		assertEquals("", obj.getId());
		assertEquals(0,obj.DaysWorked);
	}

	@Test
	public void testAdjusterString() {
		Adjuster obj = new Adjuster("Lathe");
		assertEquals("Lathe", obj.getId());
		assertEquals(0,obj.DaysWorked);
	}

	@Test
	public void testAdjusterInt() {
		Adjuster obj = new Adjuster(100);
		assertEquals(null, obj.getId());
		assertEquals(100,obj.reDays);
	}

	@Test
	public void testAddRepairableMachine() {
		Adjuster obj = new Adjuster();
		assertEquals(0,obj.repairDays.size());
		assertEquals(0,obj.macType.size());
		obj.AddRepairableMachine("Lathe", 100);
		assertEquals(1,obj.repairDays.size());
		assertEquals(1,obj.macType.size());		
	}
	
	@Test
	public void testGetID() {
		Adjuster obj = new Adjuster();
		obj.setId("Lathe");
		assertEquals("Lathe", obj.getId());
	}

	@Test
	public void testSetID() {
		Adjuster obj = new Adjuster();
		obj.setId("Lathe");
		assertEquals("Lathe", obj.getId());
	}

	@Test
	public void testAddDays() {
		Adjuster obj = new Adjuster();
		int days = obj.getDays(); 
		obj.AddDays(100);
		assertEquals(days+100, obj.getDays());
	}

	@Test
	public void testGetDays() {
		Adjuster obj = new Adjuster();
		int days = obj.getDays(); 
		obj.AddDays(100);
		assertEquals(days+100, obj.getDays());
	}

}
