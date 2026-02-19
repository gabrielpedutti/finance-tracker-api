CREATE TABLE transactions (
    id bigserial PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    type VARCHAR(20) NOT NULL
);