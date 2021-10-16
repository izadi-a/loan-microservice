DROP TABLE IF EXISTS LOAN;

CREATE TABLE LOAN
(
    id              NUMBER(10) AUTO_INCREMENT PRIMARY KEY,
    national_code   VARCHAR(10),
    type            VARCHAR(1),
    amount          NUMBER(10),
    request_date    DATE
);