--Customer
CREATE TABLE "Customer" (
    customer_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    address VARCHAR(255),
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- To automatically update the `updated_at` column whenever a row is updated, use a trigger:
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_user_updated_at
BEFORE UPDATE ON "Customer"
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
--==============================================================================

--Account
CREATE TABLE "Account" (
    account_id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(30),
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY(customer_id) REFERENCES "Customer"(customer_id),
    account_type VARCHAR(20) NOT NULL,
    balance NUMERIC(15,3) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- To automatically update the `updated_at` column whenever a row is updated, use a trigger:
CREATE OR REPLACE FUNCTION update_account_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_account_updated_at
BEFORE UPDATE ON "Account"
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();
--=========================================================================
