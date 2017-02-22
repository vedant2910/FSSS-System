package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import fsss_package.AdjusterGroup;

public class AdjusterGroupTest {

	@Test
	public void testAdjusterGroupStringIntArrayListOfStringArrayListOfInteger() {
		ArrayList<Integer> Days = new ArrayList<Integer>();
		Days.add(100);Days.add(200);
		ArrayList<String> Types = new ArrayList<String>();
		Types.add("Lathe");Types.add("Drill");
		
		AdjusterGroup obj = new AdjusterGroup("A1",50,Types,Days);
		assertEquals(50, obj.numOfAdj);
		assertEquals("A1", obj.ID);
		assertEquals(Days, obj.macDays);
		assertEquals(Types, obj.macTypes);	
	}

	@Test
	public void testAdjusterGroup() {
		AdjusterGroup obj = new AdjusterGroup();
		assertEquals(0, obj.numOfAdj);
		assertEquals(null, obj.ID);
		assertEquals(null, obj.macDays);
		assertEquals(null, obj.macTypes);
	}

}
