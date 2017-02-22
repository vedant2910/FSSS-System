package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import fsss_package.Machine;

public class MachineTest {

	@Test
	public void testMachineStringInt() {
		Machine obj = new Machine("Lathe",100);
		assertEquals("Lathe", obj.getName());
		assertEquals(100, obj.getMTTF());
		assertEquals(0, obj.getRunningDays());
	}

	@Test
	public void testMachine() {
		Machine obj = new Machine();
		assertEquals("", obj.getName());
		assertEquals(0, obj.getMTTF());
		assertEquals(0, obj.getRunningDays());
	}

	@Test
	public void testSetData() {
		Machine obj = new Machine();
		obj.setData("Lathe", 100);
		assertEquals(100, obj.getMTTF());
		assertEquals("Lathe", obj.getName());
		assertEquals(0, obj.getRunningDays());
	}

	@Test
	public void testSetRunningDays() {
		Machine obj = new Machine();
		obj.setRunningDays(100);
		assertEquals(100, obj.getRunningDays());
	}

	@Test
	public void testResetRunningDays() {
		Machine obj = new Machine();
		obj.resetRunningDays();
		assertEquals(0, obj.getRunningDays());
	}

	@Test
	public void testAddRunningDays() {
		Machine obj = new Machine();
		int days = obj.getRunningDays();
		obj.addRunningDays(200);
		assertEquals(days+200,obj.getRunningDays());
	}

	@Test
	public void testGetRunningDays() {
		Machine obj = new Machine();
		obj.setRunningDays(100);
		assertEquals(100,obj.getRunningDays());
	}

}
