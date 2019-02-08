import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.annotation.Annotation;

public class TestRunner{

	static int numPass;
	static int numFail;
	static int numErr;

	public static void run(String className){
		try {
			check(className);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void check(String name) throws Exception{
		try {
		Class objClass = Class.forName(name);
		Object obj = objClass.newInstance();
		Method[] methods = objClass.getDeclaredMethods();
		for (Method m : methods){
			Annotation[] annotations = m.getAnnotations();
			for (Annotation a : annotations){
				if (a.toString().equals("@Test()")){
					int modifiers = m.getModifiers();
					if (!Modifier.isPublic(modifiers)){
						throw new Exception("exptected public modifier: " + m.getName());
					}
					if (!(m.getReturnType().equals(Boolean.TYPE))){
						throw new Exception("expected boolean return type: " + m.getName());
					}
					if (!(m.getParameterTypes().length == 0)){
						throw new Exception("expected 0 parameters: " + m.getName());
					}
					Object ret = m.invoke(obj);
					if (((Boolean)ret).booleanValue() == true){
						System.out.println(m.getName() + " passed");
						numPass++;
					}else{
						System.out.println(m.getName() + " failed");
						numFail++;
					}
				}
				if (a.toString().equals("@Before()")){
					int modifiers = m.getModifiers();
					if (!Modifier.isPublic(modifiers)){
						throw new Exception("exptected public modifier: " + m.getName());
					}
					if (!(m.getReturnType().equals(Void.TYPE))){
						throw new Exception("expected boolean return type: " + m.getName());
					}
					if (!(m.getParameterTypes().length == 0)){
						throw new Exception("expected 0 parameters: " + m.getName());
					}
					Object ret = m.invoke(obj);
				}
				if (a.toString().equals("@After()")){
					int modifiers = m.getModifiers();
					if (!Modifier.isPublic(modifiers)){
						throw new Exception("exptected public modifier: " + m.getName());
					}
					if (!(m.getReturnType().equals(Void.TYPE))){
						throw new Exception("expected boolean return type: " + m.getName());
					}
					if (!(m.getParameterTypes().length == 0)){
						throw new Exception("expected 0 parameters: " + m.getName());
					}
					Object ret = m.invoke(obj);
				}
			}
		}
		System.out.println("Passed = " + numPass + " Failed = " + numFail);
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
