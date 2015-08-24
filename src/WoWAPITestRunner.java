import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A Junit test runner. Calling this will parse the classes in the
 * associated array for any Junit methods and run them.
 * 
 * @author Robert MacLeod
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({WoWAPITestClass.class}) // Array containing test classes to run
public class WoWAPITestRunner {}
