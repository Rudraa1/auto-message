ALTER TABLE product
DROP
COLUMN customer_name;

ALTER TABLE customers
DROP
COLUMN customer_phone;

ALTER TABLE customers
    ADD customer_phone VARCHAR(255) NULL;