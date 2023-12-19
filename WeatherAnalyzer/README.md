# Приложение анализа погоды

Это приложение предоставляет функции для анализа информации о погоде. Оно использует стороннее API для получения данных о погоде в заданном городе и сохраняет эти данные в базе данных для последующего анализа.

## Техническое задание

Подробное техническое задание доступно в файле [Task.md](Task.md).

## Использование

1. Установите все необходимые зависимости, указанные в файле pom.xml.
2. Настройте базу данных MySQL и обновите настройки в файле application.yaml.
3. Запустите приложение с помощью команды mvn spring-boot:run или запустите класс WeatherApplication в своей среде разработки.
4. Приложение будет доступно по адресу http://localhost:8080.
5. Взаимодействуйте с приложением, используя следующие эндпоинты:

    - GET /weather/current: Получить текущую погоду.
    - GET /weather/average?from={date}&to={date}: Получить среднюю температуру за указанный период.
    - GET /weather/range?from={date}&to={date}: Получить данные о погоде в указанном диапазоне дат.
    - POST /weather/save: Сохранить данные о погоде.

### Примеры запросов и ответов

1. Запрос на получение текущей погоды:

   GET /weather/current

   Ответ:

   {
   "date": "2021-08-25T12:00:00",
   "temperature": 25.0,
   "windSpeed": 10.0,
   "pressure": 1013.25,
   "humidity": 50,
   "condition": "SUNNY",
   "location": "Minsk"
   }

2. Запрос на получение средней температуры за период:

   GET /weather/average?from=2021-08-01T00:00:00&to=2021-08-31T23:59:59

   Ответ:

   {
   "average_temp": 22.15
   }

3. Запрос на получение данных о погоде в заданном диапазоне:

   GET /weather/range?from=2021-08-01T00:00:00&to=2021-08-31T23:59:59

   Ответ:

   [
   {
   "date": "2021-08-15T12:00:00",
   "temperature": 20.5,
   "windSpeed": 10.0,
   "pressure": 1013.25,
   "humidity": 50,
   "condition": "SUNNY",
   "location": "Minsk"
   },
   {
   "date": "2021-08-20T15:00:00",
   "temperature": 23.8,
   "windSpeed": 7.5,
   "pressure": 1010.5,
   "humidity": 60,
   "condition": "PARTLY_CLOUDY",
   "location": "Minsk"
   }
   ]

4. Запрос на сохранение данных о погоде:

   POST /weather/save
   Content-Type: application/json

   {
   "date": "2021-08-25T12:00:00",
   "temperature": 25.0,
   "windSpeed": 10.0,
   "pressure": 1013.25,
   "humidity": 50,
   "condition": "SUNNY",
   "location": "Minsk"
   }

   Ответ:

   {
   "date": "2021-08-25T12:00:00",
   "temperature": 25.0,
   "windSpeed": 10.0,
   "pressure": 1013.25,
   "humidity": 50,
   "condition": "SUNNY",
   "location": "Minsk"
   }

## Зависимости

- Java 8 или выше
- Spring Boot
- MySQL