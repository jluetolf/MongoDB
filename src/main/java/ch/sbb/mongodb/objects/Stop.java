package ch.sbb.mongodb.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Document("Stop")
public class Stop {

  @Id
  private String id;

  private String stop_id;
  private String stop_name;
  private String stop_lat;
  private String stop_lon;

  public Map<String, Object> convertToMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("stop_id", stop_id);
    map.put("stop_name", stop_name);
    map.put("stop_lat", stop_lat);
    map.put("stop_lon", stop_lon);
    return map;
  }
}

