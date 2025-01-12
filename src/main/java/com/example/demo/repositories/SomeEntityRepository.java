package com.example.demo.repositories;

import com.example.demo.entities.SomeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeEntityRepository extends CrudRepository<SomeEntity, Long> {
}
