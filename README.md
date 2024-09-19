# test

Create application.properties file under src/main/resources

The file should contain the following parameters for dev/test purposes:

spring.application.name=cleaning-wars
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.datasource.url=jdbc:h2:mem:cleaning-wars
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.datasource.driverClassName=org.h2.Driver

jwt.secret=secret_JWT_key
