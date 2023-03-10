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
    active                      boolean DEFAULT false,
    room_number                 varchar(255) NOT NULL, 
    est_number_of_students      integer, 
    comments                    varchar(255));

CREATE TABLE Attendances (
    ID             SERIAL NOT NULL PRIMARY KEY, 
    FK_ClassesID   integer NOT NULL REFERENCES Classes,
    FK_StudentID   integer NOT NULL REFERENCES Users, 
    coming_time    timestamp NOT NULL, 
    leaving_time   timestamp, 
    comments       varchar(255));