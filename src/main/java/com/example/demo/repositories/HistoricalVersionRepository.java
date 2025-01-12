package com.example.demo.repositories;

import com.example.demo.entities.CurrentVersionEntity;
import com.example.demo.entities.HistoricalVersionEntity;
import org.springframework.data.repository.CrudRepository;

public interface HistoricalVersionRepository<T extends HistoricalVersionEntity> extends CrudRepository<T, Long> {

}
