CREATE TABLE companies (
  company_id       SERIAL       NOT NULL CONSTRAINT companies_pkey PRIMARY KEY,
  address          VARCHAR(255) NOT NULL,
  company_name     VARCHAR(255) NOT NULL,
  email            VARCHAR(255) NOT NULL UNIQUE,
  telephone_number VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE customers (
  customer_id              SERIAL       NOT NULL CONSTRAINT customers_pkey PRIMARY KEY,
  address                  VARCHAR(255) NOT NULL,
  company_telephone_number VARCHAR(255) NOT NULL UNIQUE,
  customer_name            VARCHAR(255) NOT NULL UNIQUE,
  email                    VARCHAR(255) NOT NULL UNIQUE,
  mobile_telephone_number  VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE medical_examinations (
  medical_examination_id SERIAL NOT NULL CONSTRAINT medical_examinations_pkey PRIMARY KEY,
  medical_examination_fk INTEGER,
  validate               TIMESTAMP
);

CREATE TABLE driver_licenses (
  driver_license_id SERIAL       NOT NULL CONSTRAINT driver_licenses_pkey PRIMARY KEY,
  categories        VARCHAR(255) NOT NULL,
  driver_license_fk INTEGER      NOT NULL UNIQUE,
  special_notes     VARCHAR(255),
  validate          TIMESTAMP
);

CREATE TABLE drivers (
  driver_id              SERIAL       NOT NULL CONSTRAINT drivers_pkey PRIMARY KEY,
  address                VARCHAR(255),
  email                  VARCHAR(255) NOT NULL UNIQUE,
  first_name             VARCHAR(255) NOT NULL,
  last_name              VARCHAR(255) NOT NULL,
  status                 BOOLEAN,
  telephone_number       VARCHAR(255),
  year_of_issue          TIMESTAMP    NOT NULL,
  company_id             INTEGER CONSTRAINT fk55y0s3pkbobngmrj4efglbgjp REFERENCES companies,
  driver_license_fk      INTEGER CONSTRAINT fklgy5o391155ao5qeepouf7jsc REFERENCES driver_licenses,
  medical_examination_fk INTEGER CONSTRAINT fkejetujo1vsxlbhq1bn1k7dn5c REFERENCES medical_examinations,
  truck_fk               INTEGER
);

CREATE TABLE insurance_policies (
  insurance_policy_id   SERIAL           NOT NULL CONSTRAINT insurance_policies_pkey PRIMARY KEY,
  cost_of_payment       DOUBLE PRECISION NOT NULL,
  type_insurance_policy VARCHAR(255)     NOT NULL,
  validate              TIMESTAMP        NOT NULL,
  driver_id             INTEGER CONSTRAINT fkl2ox7ew210m6rf30wj927djom REFERENCES drivers
);


CREATE TABLE orders (
  order_id          SERIAL           NOT NULL CONSTRAINT orders_pkey PRIMARY KEY,
  coast             DOUBLE PRECISION NOT NULL,
  completed         BOOLEAN,
  download_address  VARCHAR(255)     NOT NULL,
  issued            BOOLEAN,
  paid              BOOLEAN,
  unloading_address VARCHAR(255),
  customer_id       INTEGER CONSTRAINT fkpxtb8awmi0dk6smoh2vp1litg REFERENCES customers,
  driver_id         INTEGER CONSTRAINT fkqohd0dujmkcb12rcjy4b1fj4u REFERENCES drivers
);

CREATE TABLE goods (
  goods_id   SERIAL           NOT NULL CONSTRAINT goods_pkey PRIMARY KEY,
  goods_type VARCHAR(255)     NOT NULL,
  name       VARCHAR(255)     NOT NULL,
  volume     DOUBLE PRECISION NOT NULL,
  weight     DOUBLE PRECISION NOT NULL,
  order_id   INTEGER CONSTRAINT fkhh1q63rgg42pbe1tnmn1q8q0j REFERENCES orders
);

CREATE TABLE trailers (
  trailer_id    SERIAL           NOT NULL CONSTRAINT trailers_pkey PRIMARY KEY,
  color         VARCHAR(255)     NOT NULL,
  height        DOUBLE PRECISION NOT NULL,
  longest       DOUBLE PRECISION NOT NULL,
  register_sign VARCHAR(255)     NOT NULL UNIQUE,
  trailer_fk    INTEGER,
  trailer_type  VARCHAR(255)     NOT NULL,
  volume        INTEGER          NOT NULL,
  weight        DOUBLE PRECISION NOT NULL,
  year_of_issue TIMESTAMP        NOT NULL
);

CREATE TABLE trucks (
  truck_id      SERIAL           NOT NULL CONSTRAINT trucks_pkey PRIMARY KEY,
  body_number   VARCHAR(255)     NOT NULL UNIQUE,
  color         VARCHAR(255)     NOT NULL,
  register_sign VARCHAR(255)     NOT NULL UNIQUE,
  truck_fk      INTEGER,
  weight        DOUBLE PRECISION NOT NULL,
  year_of_issue TIMESTAMP        NOT NULL,
  trailer_fk    INTEGER CONSTRAINT fktv8tcibw3yy4xl2d6gjvhkym REFERENCES trailers
);