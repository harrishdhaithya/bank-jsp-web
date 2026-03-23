package com.filegen.meta;

import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.*;
import org.json.simple.parser.*;

public class RecordMetaData {
    private static final String META_PATH = "records/metadata/format.json";

    public static String getMetaData() {
        JSONObject data = null;
        JSONParser parser = new JSONParser();
        // Try classpath first (works in fat JAR), fall back to filesystem
        try (InputStream is = RecordMetaData.class.getClassLoader().getResourceAsStream(META_PATH)) {
            if (is != null) {
                data = (JSONObject) parser.parse(new InputStreamReader(is));
                return data.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Fallback: filesystem relative path (dev mode)
        try (java.io.FileReader reader = new java.io.FileReader(META_PATH)) {
            data = (JSONObject) parser.parse(reader);
            return data.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
