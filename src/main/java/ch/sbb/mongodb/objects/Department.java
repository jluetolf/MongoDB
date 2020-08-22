package ch.sbb.mongodb.objects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Document("Department")
public class Department {
  @Id
  private String id;

  @Indexed(name = "deptName")
  private String name;
  private String description;

  //@DBRef
  private List employees;

}
