CREATE TABLE user_tb
(
    id          Bigint AUTO_INCREMENT PRIMARY KEY,
    username    varchar(255)  NOT NULL UNIQUE,
    password    varchar(65535)      NOT NULL,
    email       varchar(255)        NOT NULL,
    name        varchar(255)        NOT NULL,
    profile     varchar(65535),
    rate_id     Bigint       NOT NULL UNIQUE ,
    type        varchar(255),
    role        varchar(255)        NOT NULL,
    status_code int                 NOT NULL,
    created_at  timestamp           NOT NULL
);

CREATE TABLE rate
(
    id         Bigint AUTO_INCREMENT PRIMARY KEY,
    user_id    Bigint UNIQUE,
    rate_name  varchar(255)   NOT NULL,
    rate_point int            NOT NULL,
    rate_img   varchar(65535) NOT NULL
);

CREATE TABLE category
(
    id   Bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL
);


CREATE TABLE board
(
    id           Bigint AUTO_INCREMENT PRIMARY KEY,
    category_id  Bigint,
    title        varchar(255) NOT NULL,
    organizer_id Bigint       NOT NULL,
    content      varchar(65535),
    img          varchar(65535),
    event_id     Bigint       NOT NULL,
    status_code  int          NOT NULL,
    views        int          NOT NULL,
    recommend    boolean,
    created_at   timestamp    NOT NULL
);

CREATE TABLE event
(
    id           Bigint AUTO_INCREMENT PRIMARY KEY,
    latitude     double       NOT NULL,
    longtitude   double       NOT NULL,
    qty          int          NOT NULL,
    payment_type varchar(255) NOT NULL,
    start_at     timestamp    NOT NULL,
    end_at       timestamp    NOT NULL,
    status_code  int          NOT NULL,
    price        int          NOT NULL
);

CREATE TABLE tag
(
    id       Bigint AUTO_INCREMENT PRIMARY KEY,
    board_id Bigint NOT NULL,
    comment  varchar(65535)
);

CREATE TABLE participant
(
    id          Bigint AUTO_INCREMENT PRIMARY KEY,
    event_id    Bigint    NOT NULL,
    user_id     Bigint    NOT NULL,
    qty         int       NOT NULL,
    created_at  timestamp NOT NULL,
    limit_time  timestamp,
    status_code int       NOT NULL
);

CREATE TABLE payment
(
    id             Bigint AUTO_INCREMENT PRIMARY KEY,
    participant_id Bigint       NOT NULL,
    payment_type   varchar(255) NOT NULL,
    status_code    int          NOT NULL,
    confirmed      boolean      NOT NULL,
    created_at     timestamp    NOT NULL
);

CREATE TABLE wishlist
(
    id       Bigint AUTO_INCREMENT PRIMARY KEY,
    user_id  Bigint NOT NULL,
    board_id Bigint NOT NULL
);

CREATE TABLE my_category
(
    id          Bigint AUTO_INCREMENT PRIMARY KEY,
    user_id     Bigint NOT NULL,
    category_id Bigint NOT NULL
);

CREATE TABLE chatroom
(
    id          Bigint AUTO_INCREMENT PRIMARY KEY,
    status_code int       NOT NULL,
    created_at  timestamp NOT NULL
);

CREATE TABLE chatter_list
(
    id          Bigint AUTO_INCREMENT PRIMARY KEY,
    chatroom_id Bigint NOT NULL,
    user_id     Bigint NOT NULL
);

CREATE TABLE my_location
(
    id      Bigint AUTO_INCREMENT PRIMARY KEY,
    user_id Bigint       NOT NULL,
    state   varchar(255) NOT NULL,
    city    varchar(255) NOT NULL,
    town    varchar(255) NOT NULL
);

CREATE TABLE blacklist
(
    id              Bigint PRIMARY KEY AUTO_INCREMENT,
    user_id         Bigint    NOT NULL,
    blocked_user_id Bigint    NOT NULL,
    created_at      timestamp NOT NULL
);

CREATE TABLE report
(
    id          Bigint PRIMARY KEY AUTO_INCREMENT,
    reporter_id Bigint         NOT NULL,
    reported_id Bigint         NOT NULL,
    board_id    Bigint,
    title       varchar(255)   NOT NULL,
    content     varchar(65535) NOT NULL,
    report_type varchar(255)   NOT NULL,
    status_code int            NOT NULL,
    created_at  timestamp      NOT NULL
);

CREATE TABLE review
(
    id          Bigint PRIMARY KEY AUTO_INCREMENT,
    reviewer_id Bigint    NOT NULL,
    reviewed_id Bigint    NOT NULL,
    score       int       NOT NULL,
    comment     varchar(255),
    created_at  timestamp NOT NULL
);

CREATE TABLE my_account
(
    id             Bigint PRIMARY KEY AUTO_INCREMENT,
    user_id        Bigint       NOT NULL,
    brand          varchar(255) NOT NULL,
    account_number varchar(255) NOT NULL
);

CREATE TABLE status_code
(
    id     int PRIMARY KEY,
    type   varchar(255) NOT NULL,
    status varchar(255) NOT NULL
);