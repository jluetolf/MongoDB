package ch.sbb.mongodb.objects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Product {
  @Id
  private String id;
  private String name;
  private String price;
  private String description;
}
