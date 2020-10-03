package ch.sbb.mongodb.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
  
}

