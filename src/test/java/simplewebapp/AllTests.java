package simplewebapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EnitiesDbTest.class, ValidationTest.class, CarDaoTest.class })
public class AllTests {

}
