package ys.gme.julyrpg.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * 物件與json的轉換
 * @author yoskir
 */
@Component
public class JsonMapper {
    private final ObjectMapper objectMapper=new ObjectMapper();
    public String objectToJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            Constant.DebugLog("轉換為Json失敗 "+e.getMessage());
            return "";
        }
    }
    public <T>T jsonToObject(String jsonStr,Class<T> object){
        try {
            return objectMapper.readValue(jsonStr,object);
        } catch (JsonProcessingException e) {
            Constant.DebugLog("Json讀取失敗 ",e.getMessage());
            return null;
        }
    }
}
