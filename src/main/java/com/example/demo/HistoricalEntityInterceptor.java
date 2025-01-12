package com.example.demo;

import com.example.demo.entities.CurrentVersionEntity;
import com.example.demo.entities.HistoricalVersionEntity;
import com.example.demo.repositories.HistoricalVersionRepository;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoricalEntityInterceptor {

  private final ApplicationContext applicationContext;

  @PrePersist
  public void prePersist(CurrentVersionEntity entity) {
    entity.setVer(1);
    entity.setValidTo(null);
    entity.setValidFrom(LocalDate.now());
  }

  @PostLoad
  public void postLoad(CurrentVersionEntity entity) {
    entity.setPreviousState(entity);
  }

  @PostPersist
  public void postPersist(CurrentVersionEntity entity) {
    entity.setPreviousState(entity);
  }

  @PostUpdate
  public void postUpdate(CurrentVersionEntity entity) {
    entity.setPreviousState(entity);
  }

  @PreUpdate
  public <T extends CurrentVersionEntity> void preUpdate(T entity)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ExecutionException, InterruptedException {

    // dynamically recognize entity that is historical version of current one
    String histClassName = entity.getClass().getName() + "Hist";
    Class<?> histClass = Class.forName(histClassName);

    // create historical entity and copy all properties from entity before update
    HistoricalVersionEntity histEntity = (HistoricalVersionEntity) histClass.getDeclaredConstructor().newInstance();
    BeanUtils.copyProperties(entity.getPreviousState(), histEntity, "previousState");
    histEntity.setValidTo(LocalDate.now());

    // get repository and save historical entity
    HistoricalVersionRepository<HistoricalVersionEntity> histRepository = getRepository(histClass);
    histRepository.save(histEntity);

    // set properties of new entity
    entity.setVer(entity.getVer() + 1);
    entity.setValidFrom(LocalDate.now());
  }

  @SuppressWarnings("unchecked")
  private <T extends HistoricalVersionEntity> HistoricalVersionRepository<T> getRepository(Class<?> clazz) {

    return (HistoricalVersionRepository<T>) applicationContext.getBean(firstLetterToLowerCase(clazz.getSimpleName()) + "Repository"
        , HistoricalVersionRepository.class);
  }

  private static String firstLetterToLowerCase(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    return input.substring(0, 1).toLowerCase() + input.substring(1);
  }
}
