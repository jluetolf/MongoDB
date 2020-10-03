package ch.sbb.mongodb.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
  
}

