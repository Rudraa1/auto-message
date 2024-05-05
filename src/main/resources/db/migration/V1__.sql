CREATE TABLE customers
(
    cust_id        BIGINT       NOT NULL,
    customer_name  VARCHAR(255) NULL,
    company_name   VARCHAR(255) NULL,
    customer_email VARCHAR(255) NULL,
    customer_phone BIGINT       NULL,
    CONSTRAINT pk_customers PRIMARY KEY (cust_id)
);

CREATE TABLE inventory
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    product_id BIGINT                NULL,
    quantity   INT                   NOT NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);

CREATE TABLE invoice
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    order_id       BIGINT                NULL,
    customer_email VARCHAR(255)          NULL,
    CONSTRAINT pk_invoice PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    order_no               VARCHAR(255)          NULL,
    company_name           VARCHAR(255)          NULL,
    product_name           VARCHAR(255)          NULL,
    quantity               DOUBLE                NULL,
    price_per_kg           DOUBLE                NULL,
    delivery_terms         VARCHAR(255)          NULL,
    destination            VARCHAR(255)          NULL,
    payment_terms          VARCHAR(255)          NULL,
    dispatch_date          VARCHAR(255)          NULL,
    estimated_transit_days INT                   NULL,
    customer_name          VARCHAR(255)          NULL,
    customer_email         VARCHAR(255)          NULL,
    status                 VARCHAR(255)          NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    product_name  VARCHAR(255)          NULL,
    price         DOUBLE                NOT NULL,
    quantity      DOUBLE                NOT NULL,
    customer_name VARCHAR(255)          NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE inventory
    ADD CONSTRAINT FK_INVENTORY_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE invoice
    ADD CONSTRAINT FK_INVOICE_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);