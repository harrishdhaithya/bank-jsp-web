package com.filegen.util.common;

import java.util.List;
import java.util.Map;
import com.filegen.meta.RecordMetaData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MetaDataHandler {
    public static String generateRecordDesc(Class<?> cls,List<Object[]> records){
        int size = records.size();
        String model = cls.getSimpleName();
        String desc = null;
        if(size>0){
            Object[] first = records.get(0);
            Object[] last = records.get(size-1);
            if(model.equals("Transaction")){
                desc = "Start Date: "+first[4]+"\n"+
                        "End Date: "+last[4]+"\n"+
                        "Total Transactions: "+size;
            }else if(model.equals("User")){
                desc = "Total Number of Users: "+size;
            }else if(model.equals("Admin")){
                desc = "Total Number of Admins: "+size;
            }
        }
        return desc;
    }  
    public static String generateFilter(Class<?> cls,Map<String,String> filter){
        if(filter==null){
            return null;
        }
        String model = cls.getSimpleName();
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject metadata = new JSONObject(RecordMetaData.getMetaData());
            JSONObject entityObject = metadata.getJSONObject("entities");
            JSONObject modelObject = entityObject.getJSONObject(model);
            JSONObject modelFilter = modelObject.getJSONObject("filters");
            JSONObject filterObject = modelFilter.getJSONObject(filter.get("name"));
            String filterBy = filterObject.getString("name");
            sb.append("Filter By: "+filterBy);
            JSONObject dispObj = filterObject.getJSONObject("display");
            String[] keys = JSONObject.getNames(dispObj);
            for(String key:keys){
                sb.append("\n"+dispObj.getString(key)+": "+filter.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    } 
    public static String[] getLabels(Class<?> cls){
        String model = cls.getSimpleName();
        String[] str = null;
        try {
            JSONObject metadata = new JSONObject(RecordMetaData.getMetaData());
            JSONObject entityObject = metadata.getJSONObject("entities");
            JSONObject modelObject = entityObject.getJSONObject(model);
            JSONArray labelArray = modelObject.getJSONArray("labels");
            str = new String[labelArray.length()];
            for(int i=0;i<labelArray.length();i++){
                str[i]=labelArray.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }
    public static String generateTitle(Class<?> cls){
        String model = cls.getSimpleName();
        String title = null;
        try{
            JSONObject metadata = new JSONObject(RecordMetaData.getMetaData());
            JSONObject entityObject = metadata.getJSONObject("entities");
            JSONObject modelObject = entityObject.getJSONObject(model);
            title = modelObject.getString("Title");
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return title;
    }
}
    

