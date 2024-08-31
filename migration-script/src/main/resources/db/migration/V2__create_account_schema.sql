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
EXECUTE FUNCTION update_account_updated_at_column();
--=========================================================================
