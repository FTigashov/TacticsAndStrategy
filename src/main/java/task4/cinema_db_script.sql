
-- Создание базы данных кинотеатр
CREATE DATABASE cinema_db;

-- Подключение к созданной базе данных
--\c cinema_db;


-- Создание таблиц films, sessions, tickets
-- Таблица фильмов
CREATE TABLE films (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE,
                       duration TIME);

-- Таблица сеансов
CREATE TABLE sessions (
                          id SERIAL PRIMARY KEY,
                          film_id BIGINT REFERENCES films (id),
                          session_time TIME,
                          price BIGINT
);

-- Таблица билетов
CREATE TABLE tickets (
                         id SERIAL PRIMARY KEY,
                         ticket_num BIGINT,
                         session_id BIGINT REFERENCES sessions (ID)
);



-- Заполнение таблиц
-- Таблица фильмов
INSERT INTO
    films (name, duration)
VALUES
    ('Star Wars. Revenge of the sith', '01:30'),
    ('Lord of the rings. King is return', '02:00'),
    ('Iron Man', '01:30'),
    ('Matrix', '01:00'),
    ('Garfield', '01:00');


-- Таблица сеансов
INSERT INTO
    sessions (film_id, session_time, price)
VALUES
    (1, '09:00', 200),
    (1, '10:00', 200),
    (1, '11:00', 200),
    (2, '12:35', 250),
    (2, '13:00', 250),
    (2, '13:30', 250),
    (3, '14:00', 300),
    (3, '14:30', 300),
    (3, '14:45', 300),
    (4, '15:10', 350),
    (4, '16:20', 350),
    (4, '17:20', 350),
    (5, '18:55', 400),
    (5, '20:40', 400),
    (5, '21:45', 400),
    (5, '23:30', 400);

-- Таблица билетов
INSERT INTO
    tickets (ticket_num, session_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),
    (1, 2),
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 2),
    (1, 4),
    (2, 4),
    (3, 4),
    (4, 4),
    (5, 4),
    (1, 5),
    (2, 5),
    (3, 5),
    (4, 5),
    (5, 5),
    (1, 7),
    (2, 7),
    (3, 7),
    (4, 7),
    (5, 7),
    (1, 8),
    (2, 8),
    (3, 8),
    (4, 8),
    (5, 8),
    (1, 10),
    (2, 10),
    (3, 10),
    (4, 10),
    (5, 10),
    (1, 11),
    (2, 11),
    (3, 11),
    (4, 11),
    (5, 11),
    (1, 13),
    (2, 13),
    (3, 13),
    (4, 13),
    (5, 13),
    (1, 14),
    (2, 14),
    (3, 14),
    (4, 14),
    (5, 14),
    (1, 15),
    (2, 15),
    (3, 15),
    (4, 15),
    (5, 15);


/*Задание Сделать запросы, считающие и выводящие в понятном виде:
1) ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность».
*/
WITH t1 AS (SELECT
                s.id,
                f.name,
                s.session_time,
                f.duration,
                s.session_time + (f.duration)::interval AS session_end_time
            FROM films f JOIN sessions s ON f.id = s.film_id),
     t2 AS (SELECT
                s.id,
                f.name,
                s.session_time,
                f.duration,
                s.session_time + (f.duration)::interval AS session_end_time
            FROM films f JOIN sessions s ON f.id = s.film_id)

SELECT
    t1.name as film_1,
    t1.session_time as session_time_start,
    t1.duration as duration,
    t2.name as film_2,
    t2.session_time as session_time_start,
    t2.duration as duration
FROM t1
         JOIN t2 ON t1.id != t2.id AND t1.session_time BETWEEN t2.session_time AND t2.session_end_time
ORDER BY t1.session_time;

/*
2) Перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва».
*/

SELECT
    *
FROM (
         WITH table_view AS (SELECT
                                 s.id,
                                 f.name,
                                 s.session_time,
                                 f.duration,
                                 s.session_time + (f.duration)::INTERVAL AS session_end_time
                             FROM films f JOIN sessions s ON f.id = s.film_id
                             ORDER BY session_time)
         SELECT
             name,
             session_time as f1_start_session_time,
             duration,
             LEAD(session_time, 1) OVER () AS f2_start_session_time,
                 DATE_PART('minute', LEAD(session_time, 1) OVER () - session_end_time) as break_time
         FROM table_view) AS result
WHERE break_time >= 30
ORDER BY break_time DESC;


/*
3) Список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу.
*/

WITH t1 AS (SELECT f.name              as film_name,
                   COUNT(t.ticket_num) as visitors_amount,
                   AVG(t.ticket_num)   as avg_visitors_amount,
                   SUM(s.price)        as sum
FROM tickets t
    JOIN sessions s on t.session_id = s.id
    JOIN films f on s.film_id = f.id
GROUP BY f.name
ORDER BY sum),
    t2 AS (SELECT
    'Total',
    SUM(visitors_amount),
    AVG(avg_visitors_amount),
    SUM(sum)
FROM t1)

SELECT
    *
FROM t1

UNION

SELECT
    *
FROM t2;


/*
4) Число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
*/

SELECT
    f.name AS film_name,
    SUM(CASE
            WHEN s.session_time >= '09:00:00' AND s.session_time <= '15:00:00' THEN 1
            ELSE 0
        END) AS "09-15_visitors_amount",
    SUM(CASE
            WHEN s.session_time >= '09:00:00' AND s.session_time < '15:00:00' THEN s.price
            ELSE 0
        END) AS "09-15_sum",
    SUM(CASE
            WHEN s.session_time >= '15:00:00' AND s.session_time < '18:00:00' THEN 1
            ELSE 0 END) AS "15-18_visitors_amount",
    SUM(CASE
            WHEN s.session_time >= '15:00:00' AND s.session_time < '18:00:00' THEN s.price
            ELSE 0 END) AS "15-18_sum",
    SUM(CASE
            WHEN s.session_time >= '18:00:00' AND s.session_time < '21:00:00' THEN 1
            ELSE 0 END) AS "18-21_visitors_amount",
    SUM(CASE
            WHEN s.session_time >= '18:00:00' AND s.session_time < '21:00:00' THEN s.price
            ELSE 0 END) AS "18-21_sum",
    SUM(CASE
            WHEN s.session_time >= '21:00:00' AND s.session_time <= '23:59:59' THEN 1
            ELSE 0 END) AS "21-00_visitors_amount",
    SUM(CASE
            WHEN s.session_time >= '21:00:00' AND s.session_time <= '23:59:59' THEN s.price
            ELSE 0 END) AS "21-00_sum"
FROM tickets t
         JOIN sessions s ON s.id = t.session_id
         JOIN films f ON f.id = s.film_id
GROUP BY f.id;