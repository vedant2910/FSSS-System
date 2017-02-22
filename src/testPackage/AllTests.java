package testPackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdjusterGroupTest.class, AdjusterTest.class,
		MachineGroupTest.class, MachineTest.class, MachineTypeTest.class })
public class AllTests {

}
