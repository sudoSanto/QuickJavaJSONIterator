import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.HashMap;

public class ProcessJSON {

  private static HashMap<String, String> jsonMap = new HashMap<>();

  public static void main(String[] args) throws Exception {

    JSONObject jsonObject = (JSONObject) new JSONParser().parse(new SampleJSONObject().getJsonString());
    processJSONObject(jsonObject);

    jsonMap.entrySet().forEach(entry -> {
      System.out.println(entry.getKey() + " " + entry.getValue());
    });
  }

  private static void processJSONObject(JSONObject jsonObject) {

    // Sample 'do stuff' code
    if (jsonObject.containsKey("id") && jsonObject.containsKey("value")) {
      if (jsonObject.get("id") != null && jsonObject.get("value") != null) {
        jsonMap.put(jsonObject.get("id").toString(), jsonObject.get("value").toString());
      }
    }

    // Recursively iterate through nested JSONObjects and Arrays.
    for (Object key : jsonObject.keySet()) {
      Object value = jsonObject.get(key);

      if (value instanceof JSONObject)
        processJSONObject((JSONObject) value);
      if (value instanceof JSONArray) {
        for (Object element : (JSONArray) value) {
          processJSONObject((JSONObject) element);
        }
      }
    }
  }
}
