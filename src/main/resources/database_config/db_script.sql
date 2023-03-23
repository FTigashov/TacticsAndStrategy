-- Создание базы данных для работы с hibernate
CREATE DATABASE hibernate_db;

-- Создание таблицы Student
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    mark BIGINT
);