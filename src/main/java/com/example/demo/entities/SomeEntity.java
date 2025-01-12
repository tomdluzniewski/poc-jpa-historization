package com.example.demo.entities;

import com.example.demo.HistoricalEntityInterceptor;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(HistoricalEntityInterceptor.class)
public class SomeEntity extends CurrentVersionEntity {

  private String name;
  private String surname;
  private String email;
}
