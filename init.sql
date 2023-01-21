CREATE TABLE Users (
    ID              SERIAL PRIMARY KEY, 
    login           varchar(255) NOT NULL UNIQUE, 
    password        varchar(255) NOT NULL, 
    name            varchar(255) NOT NULL, 
    surname         varchar(255) NOT NULL,
    gender          varchar(1),
    role            varchar(255),
    date_of_birth   date, 
    email           varchar(255) NOT NULL,
    index_number    varchar(6),
    card_number     varchar(255) UNIQUE);

CREATE TABLE Classes (
    ID                          SERIAL PRIMARY KEY, 
    FK_TeacherID                integer NOT NULL REFERENCES Users, 
    name                        varchar(255) NOT NULL, 
    start_time                  timestamp NOT NULL, 
    end_time                    timestamp, 
    room_number                 varchar(255) NOT NULL, 
    est_number_of_students      integer, 
    comments                    varchar(255));

CREATE TABLE Attendances (
    ID             SERIAL NOT NULL PRIMARY KEY, 
    FK_ClassesID   integer NOT NULL REFERENCES Users, 
    FK_StudentID   integer NOT NULL REFERENCES Users, 
    coming_time    timestamp NOT NULL, 
    leaving_time   timestamp, 
    comments       varchar(255));

INSERT INTO public.users (login, password, name, surname, pesel, gender, role, date_of_birth, email, phone_number, index_number, card_number) VALUES ('teacher', '$2a$10$h3FHbtqm4biDydKAlMXDmOnYx9tWRtGNP7rpNhsaf5pjUi4uvCznS', 'name', 'surname', '12345678901', 'M', 'TEACHER', '2023-01-20', 'teacher.master@email.com', '123456789', null, null);
INSERT INTO public.users (login, password, name, surname, pesel, gender, role, date_of_birth, email, phone_number, index_number, card_number) VALUES ('student', '$2a$10$h3FHbtqm4biDydKAlMXDmOnYx9tWRtGNP7rpNhsaf5pjUi4uvCznS', 'name', 'surname', '09876543210', 'F', 'STUDENT', '2000-01-12', 'student.debil@email.com', '987654321', '260000', '07409235025302985');
