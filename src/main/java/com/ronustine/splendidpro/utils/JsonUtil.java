package com.ronustine.splendidpro.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Description 将各个json工具包解耦到这里使用
 * @Author ronustine
 * @Date 2018/7/3
 */
public class JsonUtil {

    /**
     * 递归，复杂json转map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr){
        if(jsonStr!=null&&jsonStr.startsWith("{")&&jsonStr.endsWith("}")){
            Map<String, Object> map = new LinkedHashMap();

            JSONObject json = JSONObject.parseObject(jsonStr);
            for(Object k : json.keySet()){
                Object v = json.get(k);
                // 如果内层为数组，继续解析
                if(v instanceof JSONArray){
                    List<Map<String, Object>> list = new ArrayList<>();
                    Iterator<Object> it = ((JSONArray)v).iterator();
                    while(it.hasNext()){
                        JSONObject json2 = (JSONObject)it.next();
                        if (null == json2) {
                            continue;
                        }
                        list.add(parseJSON2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else if(v instanceof JSONObject){
                    Map<String, Object> m = parseJSON2Map(v.toString());
                    if(m == null) {
                        map.put(k.toString(), v);
                    } else {
                        map.put(k.toString(), m);
                    }
                }else {
                    map.put(k.toString(), v);
                }
            }
            return map;
        }else{
            return null;
        }
    }

    /**
     * 递归，复杂json转map，键全设为小写
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSON2MapLowerCase(String jsonStr){
        if(jsonStr!=null&&jsonStr.startsWith("{")&&jsonStr.endsWith("}")){
            Map<String, Object> map = new LinkedHashMap();

            JSONObject json = JSONObject.parseObject(jsonStr);
            for(Object k : json.keySet()){
                Object v = json.get(k);
                // 如果内层为数组，继续解析
                if(v instanceof JSONArray){
                    List<Map<String, Object>> list = new ArrayList<>();
                    Iterator<Object> it = ((JSONArray)v).iterator();
                    while(it.hasNext()){
                        JSONObject json2 = (JSONObject)it.next();
                        if (null == json2) {
                            continue;
                        }
                        list.add(parseJSON2MapLowerCase(json2.toString()));
                    }
                    map.put(k.toString().toLowerCase(), list);
                } else if(v instanceof JSONObject){
                    Map<String, Object> m = parseJSON2MapLowerCase(v.toString());
                    if(m == null) {
                        map.put(k.toString().toLowerCase(), v);
                    } else {
                        map.put(k.toString().toLowerCase(), m);
                    }
                }else {
                    map.put(k.toString().toLowerCase(), v);
				}
            }
            return map;
        }else{
            return null;
        }
    }

	public static Map<String, Object> object2Map(Object object){
		JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
		Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
		Map<String, Object> map = new HashMap<>(16);
		for (Map.Entry<String, Object> entry : entrySet) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

    /**
     * 递归，用作过滤Map中的List，只获取标准结构
     * @param fields
     */
    public static void filterList(Map<String, Object> fields){
        Iterator entries = fields.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            if(value instanceof List){
                Map<String, Object> subFileds = (Map<String, Object>) ((List) value).get(0);
                fields.put(key, subFileds);
                filterList(subFileds);
            }
        }
    }

    /**
     * 递归，将jsonStr以平铺的形式转成map，形式为
     * key=v
     * key.subkey1=v1
     * key.subkey2.subkey1=v2
     * key.subkey2.subkey2=v3
     * @param jsonStr
     * @param map
     */
    public static Map<String, String> flatTheJson2Map(String jsonStr, Map<String, String> map){
        return flatTheJson2Map(jsonStr, map, new StringBuilder(""));
    }

    /**
     * 递归，将jsonStr以平铺的形式转成map，形式为
     * key=v
     * key.subkey1=v1
     * key.subkey2.subkey1=v2
     * key.subkey2.subkey2=v3
     * @param jsonStr
     * @param map
     * @param parentCode 给下一层递归指定父节点
     */
    private static Map<String, String> flatTheJson2Map(String jsonStr, Map<String, String> map, StringBuilder parentCode){
        if(jsonStr!=null&&jsonStr.startsWith("{")&&jsonStr.endsWith("}")){
            JSONObject json = JSONObject.parseObject(jsonStr);
            for(Object k : json.keySet()){
                Object v = json.get(k);
                // 如果内层为数组，继续解析
                if(v instanceof JSONArray){
                    Iterator<Object> it = ((JSONArray)v).iterator();
                    while(it.hasNext()){
                        JSONObject json2 = (JSONObject)it.next();
                        map.put(addPoint(parentCode, k).toString(), "+");// 父节点也放进去，"+"号标识
                        flatTheJson2Map(json2.toString(), map, addPoint(parentCode, k));
                    }
                } else if(v instanceof JSONObject){
                    flatTheJson2Map(v.toString(), map, addPoint(parentCode, k));
                } else {
                    map.put(addPoint(parentCode, k).toString(), v.toString());
                }
            }
        }
        return map;
    }

    /**
     * 路径加点，保持parentCode.k，不要出现.parentCode.k（头结点前面还有“.”）
     * @param parentCode
     * @param k
     */
    private static StringBuilder addPoint(StringBuilder parentCode, Object k){
        StringBuilder newStr = new StringBuilder(parentCode);
        if(!StringUtils.isEmpty(parentCode)){
            newStr.append(".").append(k.toString());
        }else{
            newStr.append(k.toString());
        }
        return newStr;
    }

    public static void main(String[] args) {

    }
}
