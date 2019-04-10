package com.hibernate.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.hibernate.interfaces.IMapper;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtil {
   private static Logger logger = Logger.getLogger(JsonUtil.class);

   public static Object convert(Class<? extends IMapper> clazzMapper, String json) {
      Object data = null;
      try {
         IMapper iMapper = clazzMapper.newInstance();
         JsonParser jsonParser = new JsonParser();
         JsonElement jsonElement = jsonParser.parse(json);
         if (!jsonElement.isJsonNull()) {
            if (jsonElement.isJsonArray()) {
               data = convertToCollection(iMapper, jsonElement.getAsJsonArray());
            } else if (jsonElement.isJsonObject()) {
               data = convertToObject(iMapper, jsonElement.getAsJsonObject());
            } else {
               data = jsonElement.getAsString();
            }
         }
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
      return data;
   }

   private static Object convertToObject(IMapper iMapper, JsonObject jsonObject) {
      return iMapper.fromJson(jsonObject);
   }

   private static List<Object> convertToCollection(IMapper iMapper, JsonArray jsonArray) {
      List<Object> objectCollection = new ArrayList<>();
      Iterator<JsonElement> iterator = jsonArray.iterator();
      while (iterator.hasNext()) {
         JsonElement element = iterator.next();
         Object value = iMapper.fromJson(element.getAsJsonObject());
         objectCollection.add(value);
      }
      return objectCollection;
   }

   /**
    * Returns the content of the file obtained by the path parameter
    *
    * @param path File path
    * @return The content of the file as String.
    */
   public static String getContentFile(String path) {
      String content = "";
      JsonParser jsonParser = new JsonParser();
      try (FileReader fileReader = new FileReader(path)) {
         JsonReader jsonReader = new JsonReader(fileReader);
         content = jsonParser.parse(jsonReader).toString();
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
      return content;
   }
}
