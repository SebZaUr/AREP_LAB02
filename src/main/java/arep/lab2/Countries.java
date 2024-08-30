package arep.lab2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Countries {
    private static Map<String,List<JSONObject>> cache = new HashMap<>();
    private Countries(){}

    public static List<JSONObject> getCache(String sport, String list){
        if(!cache.containsKey(sport)){
            List<JSONObject> jsonList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(list);
            for(int i =0; i< jsonArray.length();i++){
                jsonList.add(jsonArray.getJSONObject(i));
            }
            cache.put(sport,jsonList);
            return jsonList;
        }else{
            return cache.get(sport);
        }
    }
}
