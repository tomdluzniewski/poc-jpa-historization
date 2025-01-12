package com.example.demo;

import com.example.demo.entities.SomeEntity;
import com.example.demo.repositories.HistoricalVersionRepository;
import com.example.demo.repositories.SomeEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomeService {

  private final SomeEntityRepository repository;

  @PostConstruct
  public void init() {

    SomeEntity newEntity = repository.save(generate());
    SomeEntity newEntity2 = repository.save(generate());

    newEntity.setEmail("new_email@gmail.com");
    repository.save(newEntity);

    newEntity2.setEmail("abc@gmail.com");
    newEntity2.setName("Adam");
    newEntity2= repository.save(newEntity2);

    newEntity2.setName("Aneta");
    repository.save(newEntity2);
  }

  private SomeEntity generate() {
    SomeEntity someEntity = new SomeEntity();
    someEntity.setName("Tomasz");
    someEntity.setSurname("Przytulas");
    someEntity.setEmail("tomasz_przytulas@gmail.com");

    return someEntity;
  }
}
