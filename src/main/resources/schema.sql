CREATE TABLE IF NOT EXISTS report_daily(
    id serial,
    date_report date NOT NULL,
    description text NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS description_daily(
    id serial,
    description text,
    PRIMARY KEY(id)
);

DELETE FROM description_daily;

INSERT INTO description_daily(description)
VALUES ('Работал над задачей'),
('Составлял ТЗ'),
('Участвовал в дейлике'),
('Обед'),
('Ничего не делал'),
('Изучал документацию'),
('Изучал Spring'),
('Изучал Java core'),
('Изучал SQL'),
('Закончил задачу');