# poc-jpa-historization
poc that creates related records in historical tables from changed entity

## compile
mvn clean package

## start
mvn spring-boot:run

# how it works
1. It creates two tables (entities) in h2 database: some_entity and some_entity_hist
2. It creates new and updates records in some_entity table (SomeService class)
3. As an effect old versions of records (updates) are persisted in som_entity_hist table

Solution uses @PrePersist, @PreLoad, @PreUpdate and post related method in HistoricalEntityInterceptor class
You can see results of work in H2 cosole using this address http://localhost:8080/h2-console
    