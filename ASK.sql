create database ask;

USE ASK; 

CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    image LONGBLOB,
    user_type ENUM('ADMIN', 'USER') NOT NULL,
    criation_date DATETIME
);

CREATE TABLE POST (
	id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(200) not null,
    description varchar(225) not null,
    status Enum('P', 'R', 'S'),
    criation_date DATETIME,
    user_id bigint,
    FOREIGN KEY (user_id) REFERENCES User(id)
)

SELECT * FROM USER;