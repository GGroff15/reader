CREATE TABLE subscription
(
    id UUID NOT NULL,
    name        VARCHAR(255),
    destination VARCHAR(255),
    CONSTRAINT pk_subscription PRIMARY KEY (id)
);