import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
public class MyJunit {
	@Test
	public boolean testCorrect(){
		return true;
	}
	@Test
	public boolean testReturn(){
		return true;
	}

	@Test
	public boolean testAccess(){
		return false;
	}

	@Test 
	public boolean testParam(){
		return false;
	}
	@Before
	public void before(){
		System.out.println("Before");
	}
	@After
	public void after(){
		System.out.println("After");
	}
	
	public static void main(String[] args){
		try {
			TestRunner test = new TestRunner();
			test.run("MyJunit");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
