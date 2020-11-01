package ch.sbb.mongodb.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Document("Trip")
public class Trip {

  @Id
  private String id;

  private String route_id;
  private String service_id;
  private String trip_id;
  private String trip_headsign;
  private Integer trip_short_name;
  private Integer direction_id;

  private List<Stoptime> stoptimes;

  public Map<String, Object> convertToMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("route_id", route_id);
    map.put("service_id", service_id);
    map.put("trip_id", trip_id);
    map.put("trip_headsign", trip_headsign);
    map.put("trip_short_name", trip_short_name);
    map.put("direction_id", direction_id);
    map.put("stoptimes", convertStoptimes());

    return map;
  }
  
  private List<Map<String, Object>> convertStoptimes() {
    List<Map<String, Object>> mapList = new ArrayList<>();
    stoptimes.forEach(stoptime -> mapList.add(stoptime.convertToMap()));
    return mapList;
  }
}

