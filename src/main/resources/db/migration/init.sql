CREATE DATABASE cambay
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE TABLE IF NOT EXISTS public.organization
(
    id integer NOT NULL DEFAULT nextval('organization_id_seq'::regclass),
    created_at date,
    updated_at date,
    active boolean,
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    phone_no character varying(255) COLLATE pg_catalog."default",
    website character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT organization_pkey PRIMARY KEY (id)
)

ALTER TABLE IF EXISTS public.organization
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.product
(
    id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT product_pkey PRIMARY KEY (id)
)

ALTER TABLE IF EXISTS public.product
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.product_mapping
(
    id integer NOT NULL DEFAULT nextval('product_mapping_id_seq'::regclass),
    status boolean,
    expiry_date date,
    no_of_users integer,
    organization_id integer,
    product_id integer,
    CONSTRAINT product_mapping_pkey PRIMARY KEY (id),
    CONSTRAINT ukj60lcrelw02960pmhi2c5tccr UNIQUE (product_id, organization_id),
    CONSTRAINT fkjkk254hrkvi0v1ld4pgvy2g7p FOREIGN KEY (id)
        REFERENCES public.product_mapping (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkm3ebp0qpf59sqkk3x56qx8rgj FOREIGN KEY (organization_id)
        REFERENCES public.organization (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpexfuacit3rhwiirkgqe43ya2 FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

ALTER TABLE IF EXISTS public.product_mapping
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.role
(
    id integer NOT NULL DEFAULT nextval('role_id_seq'::regclass),
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
)

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.user_base
(
    id integer NOT NULL DEFAULT nextval('user_base_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    organization_id integer NOT NULL,
    role_id integer,
    CONSTRAINT user_base_pkey PRIMARY KEY (id),
    CONSTRAINT fk3etxxvg6ktexysbss67ht2e9r FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fki0vpq9dsidsa00qjl8s6mrwfc FOREIGN KEY (organization_id)
        REFERENCES public.organization (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

ALTER TABLE IF EXISTS public.user_base
    OWNER to postgres;