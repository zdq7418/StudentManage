package com.student.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.student.bean.ClassFrom;

public class JsonTools {

    public static String createJsonString(Object value) {
        Gson gson = new Gson();
        String string = gson.toJson(value);
        return string;
    }
    
    
    public static <T> T jsonStringTo(String jsonString){
    	Gson gson = new Gson();
    	return gson.fromJson(jsonString,new TypeToken<T>(){}.getType());
    }
    

    
    public static void main(String[] args) {
		Map<String, Object> map=new HashMap<String, Object>();
		List list=new ArrayList();
		map.put("list", 1+"");
		list.add(map);
		ClassFrom classFrom=new ClassFrom("sss", "aada", "ddd");
		String a=JsonTools.createJsonString(map);
		String b=JsonTools.createJsonString(list);
		String c=JsonTools.createJsonString(classFrom);
		Map<String, Object> mapc=JsonTools.jsonStringTo(a);
		System.out.println(map);
		System.out.println(list);
		System.out.println(a);
		System.out.println(b);
		System.out.println(JsonTools.jsonStringTo(a));
		System.out.println(JsonTools.jsonStringTo(b));
		System.out.println(JsonTools.jsonStringTo(c));
		 Gson gson = new Gson();
		ClassFrom classFrom2=gson.fromJson(c, ClassFrom.class);
		System.out.println(classFrom2);
		if (list.equals(JsonTools.jsonStringTo(b))) {
			System.out.println("=");
		}
		
	}


}
