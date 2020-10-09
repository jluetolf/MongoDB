package ch.sbb.postgres.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Trip")
public class TripTable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String trip;
  
}
