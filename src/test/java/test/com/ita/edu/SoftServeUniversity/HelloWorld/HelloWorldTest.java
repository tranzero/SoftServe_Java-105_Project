/**
 * 
 */
package test.com.ita.edu.SoftServeUniversity.HelloWorld;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ita.edu.SoftServeUniversity.HelloWorld.HelloWorld;

/**
 * @author tranzero
 *
 */
public class HelloWorldTest {
	
	HelloWorld hi = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String name = "Valentin";
		hi = new HelloWorld(name);
	}

	/**
	 * Test method for {@link com.ita.edu.SoftServeUniversity.HelloWorld.HelloWorld#main(java.lang.String[])}.
	 */
	@Test
	public final void testMain() {
	//	fail("Not yet implemented"); // TODO
	}

}
