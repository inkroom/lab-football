package javadev.iip.util.validata;




import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import static java.lang.System.out;


/**
 * 此类用于V类的演示并无实际作用
 */
public class TestVali {
	
	private V v;
	
	
	public void before(){
		v = new V();
	}
	
	public TestVali(){
		v = new V();
	}
	
	public static void main(String[] args ){
		new TestVali().test();
	}
	
	public void test(){
		String[] orders = 
			{
			"N:ddd1/N P:LMax=12|LMin=4/P M:用户名必须在6-12位,dniwq/M"
			};
		
		Map map = new HashMap();
		map.put("ddd1", "134");
		map.put("ddd2","2222222222");
		map.put("ddd3", new int[]{1,2,3,4,5,6});
		Map map2 = v.validateData(orders,map);
		
		out.print(map2.toString());
		//out.println(v.compareObject(new Double(1.7), 1.8));
	}
	
}
