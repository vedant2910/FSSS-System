package testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import fsss_package.Machine;
import fsss_package.MachineGroup;

public class MachineGroupTest {

	@Test
	public void testMachineGroup() {
		MachineGroup obj = new MachineGroup("Lathe",100,1000);
		assertEquals(1000, obj.mach.length);
		for(int i=0; i<1000; i++)
		{
			assertEquals(100, obj.mach[i].getMTTF());
			assertEquals("Lathe", obj.mach[i].getName());
		}
	}

}
