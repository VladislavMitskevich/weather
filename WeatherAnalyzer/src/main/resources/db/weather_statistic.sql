CREATE TABLE weather_statistic (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  from_date DATE NOT NULL,
  to_date DATE NOT NULL,
  average_temperature DOUBLE NOT NULL
);
