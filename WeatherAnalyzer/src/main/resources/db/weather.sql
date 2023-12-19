CREATE TABLE weather (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  date DATE NOT NULL,
  temperature DOUBLE NOT NULL,
  wind_speed DOUBLE NOT NULL,
  pressure DOUBLE NOT NULL,
  humidity INT NOT NULL,
  condition ENUM('CLEAR', 'CLOUDY', 'RAIN', 'SNOW', 'STORM') NOT NULL,
  location VARCHAR(255) NOT NULL
);
