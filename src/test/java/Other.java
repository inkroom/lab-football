import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Other {
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }

    private static String[] getClass(String value) {
        Pattern pattern = Pattern.compile("<(.*)>");
        int index = value.indexOf("<");
        if (index != -1) {
            Matcher matcher = pattern.matcher(value.subSequence(index, value.indexOf(">") + 1));
            if (matcher.find()) {
                String temp = matcher.group(1);
                if (temp.isEmpty()) {//没有泛型
                    return new String[]{value.substring(0, index)};
                } else if (temp.contains(",")) {//两个泛型
                    int nextIndex = temp.indexOf(",");
                    return new String[]{
                            value.substring(0, index),
                            temp.substring(0, nextIndex),
                            temp.substring(nextIndex + 1)
                    };
                } else {//一个泛型
                    return new String[]{
                            value.substring(0, index),
                            temp
                    };
                }
            }
        }
        return null;

    }

    @Test
    public void testD() {
        String value[] = Other.getClass("java.util.HashMap<>");
        for (String v :
                value) {
            System.out.println(v);
        }
    }

    @Test
    public void testClass() {

//        Map<String, String> object = new HashMap<>();
//        object.put("test", "test");

        Object obj = "123";
        StringBuilder builder = new StringBuilder(obj.getClass().toString().substring(6) + "<");
        if (obj instanceof List) {
            List list = ((List) obj);
            if (!list.isEmpty()) {
                Object temp = list.get(0);
                builder.append(temp.getClass().toString().substring(6));
            }
        } else if (obj instanceof Map) {
            Map map = ((Map) obj);
            Iterator iterator = map.keySet().iterator();
            if (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                builder.append(key.getClass().toString().substring(6)).append(",").append(value.getClass().toString().substring(6));
            }
        } else if (obj instanceof Set) {
            Set set = ((Set) obj);
            Iterator iterator = set.iterator();

            if (iterator.hasNext()) {
                Object value = iterator.next();
                builder.append(value.getClass().toString().substring(6));
            }
        }
        builder.append(">");
        System.out.println(builder.toString());
    }

    @Test
    public void test() {
        Object object = new ArrayList<String>();
        Type superclassType = object.getClass().getGenericSuperclass();
        if (ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            for (Type t1 : ((ParameterizedType) superclassType).getActualTypeArguments()) {
                System.out.println(t1.getTypeName() + "   " + t1.getClass() + "  ");
            }


//            System.out.println(();
        }


//        List<String> list = new ArrayList<>();
//
//        Type t = list.getClass().getGenericSuperclass();
//        if (ParameterizedType.class.isAssignableFrom(t.getClass())){
//            for(Type t1: ((ParameterizedType) t).getActualTypeArguments()){
//                System.out.println(t1.getTypeName()+"   "+t1.getClass());
//            }
//        }
//        ParameterizedType.class.
//        String result[]=Md5EncryptUtil.parseMd5WithSalt("inkbox");
//        System.out.println(result[0]);
//        System.out.println(result[1]);

//        int count = 6;
//        Random random = new Random();
//        int result = (int) (random.nextInt((int) (Math.pow(10,count-1)-1))+Math.pow(10,count+1));
//
//       result = (int) (random.nextInt((int) (Math.pow(10,count)-1-Math.pow(10,count-1)))+Math.pow(10,count-1));
//
//
//        System.out.println(result);
    }

    private List list;
    private Map<String, Object> map;

    /***
     * 获取List中的泛型
     */
    public static void testList(Object obj) throws NoSuchFieldException, SecurityException {
        Type t = obj.getClass().getDeclaredField("list").getGenericType();
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                System.out.print(t1 + ",");
            }
            System.out.println();
        }
    }

    /***
     * 获取Map中的泛型
     */
    public static void testMap() throws NoSuchFieldException, SecurityException {
        Type t = Other.class.getDeclaredField("map").getGenericType();
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                System.out.print(t1 + ",");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) throws Exception {
        List<String> list = new ArrayList<>();
        Other o = new Other();
        o.list = list;
        testList(o);
        testMap();
//        System.out.println(">>>>>>>>>>>testList>>>>>>>>>>>");
//        testList();
//        System.out.println("<<<<<<<<<<<testList<<<<<<<<<<<\n");
//        System.out.println(">>>>>>>>>>>testMap>>>>>>>>>>>");
//        testMap();
//        System.out.println("<<<<<<<<<<<testMap<<<<<<<<<<<\n");
//        System.out.println(">>>>>>>>>>>testClassA>>>>>>>>>>>");
//       // new Other().testClassA();
//        System.out.println("<<<<<<<<<<<testClassA<<<<<<<<<<<");
    }

    @Test
    public void testAddIndex(){
        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("4");
        list.add("5");

        list.add(1+1,"sdf");


        System.out.println(list);
    }
}
