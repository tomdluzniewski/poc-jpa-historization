package com.example.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.SerializationUtils;


@Getter
@Setter
@MappedSuperclass
public abstract class CurrentVersionEntity implements Serializable {

  @Transient
  private CurrentVersionEntity previousState;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "valid_from")
  private LocalDate validFrom;

  @Column(name = "valid_to")
  private LocalDate validTo;

  private int ver;

  public void setPreviousState(CurrentVersionEntity previousState) {
    this.previousState = SerializationUtils.clone(previousState);
  }
}
