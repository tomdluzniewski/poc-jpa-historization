package com.example.demo.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SomeEntityHist extends HistoricalVersionEntity {

  private String name;
  private String surname;
  private String email;
}
