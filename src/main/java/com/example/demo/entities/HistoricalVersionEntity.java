package com.example.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(IdVer.class)
public abstract class HistoricalVersionEntity implements Serializable {

  @Id
  private Long id;
  @Id
  private int ver;

  @Column(name = "valid_from")
  private LocalDate validFrom;

  @Column(name = "valid_to")
  private LocalDate validTo;
}
