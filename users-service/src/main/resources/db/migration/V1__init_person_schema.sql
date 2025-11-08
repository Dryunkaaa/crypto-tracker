CREATE TABLE users.users
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    email      VARCHAR(1024) UNIQUE                NOT NULL
);

CREATE TABLE users.alert_rules
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    active     BOOLEAN   DEFAULT true              NOT NULL,
    operator   VARCHAR(2)                          NOT NULL
        CONSTRAINT alert_rules_operator_check
            CHECK (operator IN ('GT', 'LT')),
    symbol     VARCHAR(10)                         NOT NULL,
    threshold  NUMERIC(38, 2)                      NOT NULL,
    user_id    UUID
        CONSTRAINT fk_user_id
            REFERENCES users.users (id)
);
