-- -----------------------------------------------------
-- Table USER
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS USER (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(150) NOT NULL,
    surname VARCHAR(150) NOT NULL,
    telephone VARCHAR(15) NOT NULL,
    user_identity VARCHAR(45) NOT NULL,
    date_created DATETIME NOT NULL,
    uuid VARCHAR(36) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX UQ_USER_uuid (uuid) VISIBLE,
    UNIQUE INDEX UQ_USER_identity (user_identity) VISIBLE
);
