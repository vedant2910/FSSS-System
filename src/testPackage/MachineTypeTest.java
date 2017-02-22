package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import fsss_package.MachineType;

public class MachineTypeTest {

	@Test
	public void testMachineTypeStringInt() {
		MachineType obj = new MachineType("Lathe",100);
		assertEquals(100, obj.getMTTF());
		assertEquals("Lathe",obj.getName());
	}

	@Test
	public void testMachineType() {
		MachineType obj = new MachineType();
		assertEquals(0, obj.getMTTF());
		assertEquals("", obj.getName());
	}

	@Test
	public void testGetName() {
		MachineType obj = new MachineType();
		obj.setName("Lathe");
		assertEquals("Lathe", obj.getName());
	}

	@Test
	public void testSetName() {
		MachineType obj = new MachineType();
		obj.setName("Lathe");
		assertEquals("Lathe", obj.getName());		
	}

	@Test
	public void testGetMTTF() {
		MachineType obj = new MachineType();
		obj.setMTTF(100);
		assertEquals(100, obj.getMTTF());
	}

	@Test
	public void testSetMTTF() {
		MachineType obj = new MachineType();
		obj.setMTTF(100);
		assertEquals(100, obj.getMTTF());
	}

}
