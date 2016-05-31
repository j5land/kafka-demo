package com.test.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonHelper {
	/** 从普通的Bean转换为字符串 * */
	public static String getJson(Object o) {
		JSONObject jo = JSONObject.fromObject(o);
		return jo.toString();
	}

	/** 从Java的列表转换为字符串 * */
	public static String getJson(List list) {
		JSONArray ja = JSONArray.fromObject(list);
		return ja.toString();
	}

	/** 从Java对象数组转换为字符串 * */
	public static String getJson(Object[] arry) {
		JSONArray ja = JSONArray.fromObject(arry);
		return ja.toString();
	}

	/** 从json格式的字符串转换为Map对象 * */
	public static Map getObject(String s) {
		return JSONObject.fromObject(s);
	}

	/** 从json格式的字符串转换为List数组 * */
	public static List getArray(String s) {
		return JSONArray.fromObject(s);
	}

	/** 从json格式的字符串转换为某个Bean * */
	public static Object getObject(String s, Class cls) {
		JSONObject jo = JSONObject.fromObject(s);
		return JSONObject.toBean(jo, cls);
	}

	/** 从json格式的字符串转换为某类对象的数组 * */
	public static Object getArray(String s, Class cls) {
		JSONArray ja = JSONArray.fromObject(s);
		return JSONArray.toArray(ja, cls);
	}
	
	public static void main(String[] args) {
		
		String json = "{\"took\":3,\"timed_out\":false,\"_shards\":{\"total\":5,\"successful\":5,\"failed\":0},\"hits\":{\"total\":442,\"max_score\":null,\"hits\":[{\"_index\":\"dating\",\"_type\":\"dating\",\"_id\":\"101719\",\"_score\":null,\"_source\":{\"id\":101719,\"filmid\":\"051200832015\",\"filmname\":\"火星救援\",\"cinemaid\":null,\"cinemaname\":null,\"type\":1,\"datingcount\":1,\"datesex\":\"f\",\"datecontent\":\"观影容易,相遇不易;一场电影,一段奇遇。亲,让我在影院灯光熄 灭前看到你哟~\",\"datedate\":null,\"datetime\":null,\"customerid\":1636789,\"picture\":\"albumpic/201409/1410415019885.jpg\",\"alias\":\"Zhouhl\",\"username\":\"zhouhl1@spider.com.cn\",\"datedcustomerid\":0,\"datedusername\":\"\",\"datedalias\":\"\",\"status\":\"y\",\"createdate\":\"2015-12-08T14:15:35.307+08:00\",\"orderfilmid\":null,\"successdate\":null,\"platform\":\"apple\",\"verifystaff\":null,\"verifydate\":null,\"datingstatus\":\"n\",\"filmpic\":\"\",\"userpoint\":1,\"birthday\":\"1990-01-01\",\"applycount\":0,\"sex\":\"m\",\"orderstatus\":\"n\",\"seqNo\":null,\"regionname\":\"宝山\",\"usersign\":0,\"contactCount\":null,\"dateLongtitude\":121.35055,\"dateLatitude\":31.219015,\"recommendStatus\":\"n\",\"dateContentType\":\"0 \",\"datingCity\":\"shanghai\"},\"sort\":[101719]},{\"_index\":\"dating\",\"_type\":\"dating\",\"_id\":\"101718\",\"_score\":null,\"_source\":{\"id\":101718,\"filmid\":\"051200832015\",\"filmname\":\"火星救援\",\"cinemaid\":null,\"cinemaname\":null,\"type\":1,\"datingcount\":1,\"datesex\":\"f\",\"datecontent\":\"\",\"datedate\":null,\"datetime\":null,\"customerid\":1636789,\"picture\":\"albumpic/201409/1410415019885.jpg\",\"alias\":\"Zhouhl\",\"username\":\"zhouhl1@spider.com.cn\",\"datedcustomerid\":0,\"datedusername\":\"\",\"datedalias\":\"\",\"status\":\"y\",\"createdate\":\"2015-11-26T18:13:59.957+08:00\",\"orderfilmid\":null,\"successdate\":null,\"platform\":\"apple\",\"verifystaff\":null,\"verifydate\":null,\"datingstatus\":\"n\",\"filmpic\":\"\",\"userpoint\":1,\"birthday\":\"1990-01-01\",\"applycount\":2,\"sex\":\"m\",\"orderstatus\":\"n\",\"seqNo\":null,\"regionname\":\"虹口\",\"usersign\":0,\"contactCount\":1,\"dateLongtitude\":121.35067,\"dateLatitude\":31.218979,\"recommendStatus\":\"n\",\"dateContentType\":\"0 \",\"datingCity\":\"shanghai\"},\"sort\":[101718]}]}}";
		JsonHelper jh = new JsonHelper();
		Map map = jh.jsonToMap(json);
		String hits = (String) map.get("hits");
		System.out.println(hits);
		
	}
	   
    /**  
     * 将java对象转换成json字符串  
     *
     * @param bean  
     * @return  
     */
    public static String beanToJson(Object bean, String[] _nory_changes, boolean nory) {
 
        JSONObject json = null;
         
        if(nory){//转换_nory_changes里的属性
             
            Field[] fields = bean.getClass().getDeclaredFields();
            String str = "";
            for(Field field : fields){
//              System.out.println(field.getName());
                str+=(":"+field.getName());
            }
            fields = bean.getClass().getSuperclass().getDeclaredFields();
            for(Field field : fields){
//              System.out.println(field.getName());
                str+=(":"+field.getName());
            }
            str+=":";
            for(String s : _nory_changes){
                str = str.replace(":"+s+":", ":");
            }
            json = JSONObject.fromObject(bean,configJson(str.split(":")));
             
        }else{//转换除了_nory_changes里的属性
             
 
             
            json = JSONObject.fromObject(bean,configJson(_nory_changes));
        }
         
         
         
        return json.toString();
 
    }
     private static JsonConfig configJson(String[] excludes) {   
 
                JsonConfig jsonConfig = new JsonConfig();   
 
                jsonConfig.setExcludes(excludes);   
//
                jsonConfig.setIgnoreDefaultExcludes(false);   
//
//              jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
 
//              jsonConfig.registerJsonValueProcessor(Date.class,   
//
//                  new DateJsonValueProcessor(datePattern));   
 
               
 
                return jsonConfig;   
 
            }  
 
 
 
 
 
    /**
     * 将java对象List集合转换成json字符串  
     * @param beans
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String beanListToJson(@SuppressWarnings("rawtypes") List beans) {
         
        StringBuffer rest = new StringBuffer();
         
        rest.append("[");
         
        int size = beans.size();
         
        for (int i = 0; i < size; i++) {
            rest.append(getJson(beans.get(i))+((i<size-1)?",":""));
        }
        rest.append("]");
        return rest.toString();
 
    }
     
    /**
     * 
     * @param beans
     * @param _no_changes
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String beanListToJson(List beans, String[] _nory_changes, boolean nory) {
         
        StringBuffer rest = new StringBuffer();
         
        rest.append("[");
         
        int size = beans.size();
         
        for (int i = 0; i < size; i++) {
            try{
                rest.append(beanToJson(beans.get(i),_nory_changes,nory));
                if(i<size-1){
                    rest.append(",");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
         
        rest.append("]");
         
        return rest.toString();
 
    }
 
    /**  
     * 从json HASH表达式中获取一个map，改map支持嵌套功能  
     *
     * @param jsonString  
     * @return  
     */
    @SuppressWarnings({ "unchecked" })
    public static Map jsonToMap(String jsonString) {
         
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();
 
        while (keyIter.hasNext()) {
             
            key = (String) keyIter.next();
            value = jsonObject.get(key).toString();
            valueMap.put(key, value);
             
        }
 
        return valueMap;
    }
     
    /**
     * map集合转换成json格式数据
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, ?> map, String[] _nory_changes, boolean nory){
         
        String s_json = "{";
         
         Set<String> key = map.keySet();
         for (Iterator<?> it = key.iterator(); it.hasNext();) {
             String s = (String) it.next();
             if(map.get(s) == null){
                  
             }else if(map.get(s) instanceof List<?>){
                 s_json+=(s+":"+beanListToJson((List<?>)map.get(s), _nory_changes, nory));
                 
             }else{
                 JSONObject json = JSONObject.fromObject(map);
                 s_json += (s+":"+json.toString());;
             }
              
             if(it.hasNext()){
                 s_json+=",";
             }
        }
 
         s_json+="}";
        return s_json; 
    }
 
    /**  
     * 从json数组中得到相应java数组  
     *
     * @param jsonString  
     * @return  
     */
    public static Object[] jsonToObjectArray(String jsonString) {
         
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
         
        return jsonArray.toArray();
 
    }
     
    public static String listToJson(List<?> list) {
         
        JSONArray jsonArray = JSONArray.fromObject(list);
         
        return jsonArray.toString();
 
    }
 
    /**  
     * 从json对象集合表达式中得到一个java对象列表  
     *
     * @param jsonString  
     * @param beanClass  
     * @return  
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {
 
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        T bean;
        int size = jsonArray.size();
        List<T> list = new ArrayList<T>(size);
 
        for (int i = 0; i < size; i++) {
 
            jsonObject = jsonArray.getJSONObject(i);
            bean = (T) JSONObject.toBean(jsonObject, beanClass);
            list.add(bean);
 
        }
         
        return list;
 
    }
 
    /**  
     * 从json数组中解析出java字符串数组  
     *
     * @param jsonString  
     * @return  
     */
    public static String[] jsonToStringArray(String jsonString) {
 
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        int size = jsonArray.size();
 
        for (int i = 0; i < size; i++) {
 
            stringArray[i] = jsonArray.getString(i);
 
        }
 
        return stringArray;
    }
 
    /**  
     * 从json数组中解析出javaLong型对象数组  
     *
     * @param jsonString  
     * @return  
     */
    public static Long[] jsonToLongArray(String jsonString) {
 
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        int size = jsonArray.size();
        Long[] longArray = new Long[size];
         
        for (int i = 0; i < size; i++) {
             
            longArray[i] = jsonArray.getLong(i);
 
        }
         
        return longArray;
         
    }
 
    /**  
     * 从json数组中解析出java Integer型对象数组  
     *
     * @param jsonString  
     * @return  
     */
    public static Integer[] jsonToIntegerArray(String jsonString) {
 
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        int size = jsonArray.size();
        Integer[] integerArray = new Integer[size];
         
        for (int i = 0; i < size; i++) {
             
            integerArray[i] = jsonArray.getInt(i);
 
        }
         
        return integerArray;
         
    }
 
    /**  
     * 从json数组中解析出java Double型对象数组  
     *
     * @param jsonString  
     * @return  
     */
    public static Double[] jsonToDoubleArray(String jsonString) {
 
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        int size = jsonArray.size();
        Double[] doubleArray = new Double[size];
         
        for (int i = 0; i < size; i++) {
             
            doubleArray[i] = jsonArray.getDouble(i);
 
        }
         
        return doubleArray;
         
    }
    
    
	
	/**
	 * 将一个json对象追加到另外一个json对象
	 * @param url url链接
	 * @param interfacenName 接口名称
	 * @param params 接口参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject copyJsonToJson(JSONObject target ,JSONObject source){
		Iterator<String> keys = source.keys();
		while (keys.hasNext()) {
			String key =keys.next();
			Object object = source.get(key);
			target.put(key, object);
		}
		return target;
	}
	
	
	
	
}
