package ch.sbb.postgres.entities;


import ch.sbb.mongodb.objects.Trip;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="triptable")
@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonStringType.class),
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class TripTable {

  @Id
  private Long id;

  public TripTable() {
  }

  public TripTable(Trip trip) {
    this.trip = trip;
  }

  public TripTable(Trip trip, Long id) {
    this.trip = trip;
    this.id = id;
  }

  @Type(type = "jsonb")
  @Column(columnDefinition = "jsonb")
  private Trip trip;
  
}
