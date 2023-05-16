--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bill (
    id integer NOT NULL,
    orderid integer,
    total integer,
    date date
);


ALTER TABLE public.bill OWNER TO postgres;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id integer NOT NULL,
    name character varying(255),
    address character varying(255),
    email character varying(255),
    age bigint NOT NULL
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.log_id_seq OWNER TO postgres;

--
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.bill.id;


--
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    id integer NOT NULL,
    clientid bigint NOT NULL,
    productid bigint NOT NULL,
    quantity bigint NOT NULL
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_id_seq OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_id_seq OWNED BY public."order".id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255),
    price bigint,
    stock bigint NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: bill id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order" ALTER COLUMN id SET DEFAULT nextval('public.order_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Data for Name: bill; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bill (id, orderid, total, date) VALUES (1, 31, 40, '2023-05-12');
INSERT INTO public.bill (id, orderid, total, date) VALUES (2, 32, 800, '2023-05-12');


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client (id, name, address, email, age) VALUES (21, 'Pop', 'balcescucornal@gmail.com', 'Cluj-Napoca', 28);
INSERT INTO public.client (id, name, address, email, age) VALUES (2, 'Rogoz Lorena', 'lorerogo@yahoo.com', 'Zalha nr.2', 21);
INSERT INTO public.client (id, name, address, email, age) VALUES (6, 'Breha Paula', 'brehapaula@gmail.com', 'Baia-Mare', 20);
INSERT INTO public.client (id, name, address, email, age) VALUES (7, 'Popa Alex', 'alexpopasimplu@gmail.com', 'Petrosani', 20);
INSERT INTO public.client (id, name, address, email, age) VALUES (8, 'Hruban Andrada Bianca', 'hrubanandrada@gmail.com', 'Gherla, str Clujului', 20);
INSERT INTO public.client (id, name, address, email, age) VALUES (10, 'Hruban Nelu', 'nhruban@yahoo.com', 'Gherla, str.Apei', 52);


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (11, 21, 3, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (12, 8, 5, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (13, 2, 5, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (14, 10, 4, 3);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (17, 21, 5, 2);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (18, 6, 4, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (19, 6, 4, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (20, 6, 4, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (21, 6, 4, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (22, 6, 4, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (23, 6, 5, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (24, 6, 5, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (25, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (26, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (27, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (28, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (29, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (30, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (31, 6, 6, 1);
INSERT INTO public."order" (id, clientid, productid, quantity) VALUES (32, 6, 6, 20);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, name, price, stock) VALUES (3, 'Laptop Legion', 5900, 4);
INSERT INTO public.product (id, name, price, stock) VALUES (4, 'Laptop Asus', 4599, 0);
INSERT INTO public.product (id, name, price, stock) VALUES (5, 'Apple Watch seria 8', 3000, 4);
INSERT INTO public.product (id, name, price, stock) VALUES (6, 'Folie sticla Iphone X', 40, 29);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 10, true);


--
-- Name: log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.log_id_seq', 2, true);


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_id_seq', 32, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 6, true);


--
-- Name: client pk_client; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT pk_client PRIMARY KEY (id);


--
-- Name: bill pk_log; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT pk_log PRIMARY KEY (id);


--
-- Name: order pk_order; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT pk_order PRIMARY KEY (id);


--
-- Name: product pk_product; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT pk_product PRIMARY KEY (id);


--
-- Name: order fk_order_on_client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk_order_on_client FOREIGN KEY (clientid) REFERENCES public.client(id);


--
-- Name: order fk_order_on_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT fk_order_on_product FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- PostgreSQL database dump complete
--

