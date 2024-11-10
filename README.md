# caloriesCalculator
Telegram-bot based Spring Boot application for calculating calories

# Running command
## Local
Connect into docker's container
```bash
docker exec calories_postgres
```
Connect to DB
``` bash
psql -U myuser -d mydatabase
```
Init DB with default data
``` sql
INSERT INTO user_ration (id_person, day, id_product, weight) VALUES
(566604986, '2021-07-06', 3, 2),
(566604986, '2023-04-29', 1, 10),
(566604986, '2023-07-15', 2, 100),
(566604986, '2024-03-30', 1, 100),
(566604986, '2024-04-01', 1, 100);

INSERT INTO dictionary (id, product_name, calories, n, f, c, description) VALUES
(1, 'гречка', 110, 4.2, 0, 21.3, 'Гречка сваренная'),
(2, 'куриная грудка', 189, 23, 1, 0, 'Куриная грудка вареная или запеченная'),
(3, 'сметана 15% president', 158.6, 2.9, 15, 3, NULL),
(4, 'сметана пряженная 10%', 200, 3, 10, 4.8, 'Каллорийность указана неверно');

INSERT INTO profiles (id_person, id_chat) VALUES
(566604986, 566604986);
```
