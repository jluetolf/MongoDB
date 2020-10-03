package ch.sbb.mongodb.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
}

