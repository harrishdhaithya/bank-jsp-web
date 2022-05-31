package com.filegen.meta;

import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.*;
public class RecordMetaData {
    public static String getMetaData(){
        FileReader reader = null;
        JSONObject data = null;
        String jsonString = null;
        JSONParser parser = new JSONParser();
        try {
            reader = new FileReader("records/metadata/format.json");
            data = (JSONObject)parser.parse(reader);
            jsonString = data.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
