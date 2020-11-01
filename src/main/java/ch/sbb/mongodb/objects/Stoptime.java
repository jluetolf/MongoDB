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
@Document("Stoptime")
public class Stoptime {

  @Id
  private String id;

  private String trip_id;
  private String arrival_time;
  private String departure_time;
  private String stop_id;
  private Integer stop_sequence;
  private Integer pickup_type;
  private Integer drop_off_type;
  private Stop stop;

  public Map<String, Object> convertToMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("trip_id", trip_id);
    map.put("arrival_time", arrival_time);
    map.put("departure_time", departure_time);
    map.put("stop_id", stop_id);
    map.put("stop_sequence", stop_sequence);
    map.put("pickup_type", pickup_type);
    map.put("drop_off_type", drop_off_type);
    map.put("stop", stop.convertToMap());
    return map;
  }
}

