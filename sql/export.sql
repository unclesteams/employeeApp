CREATE TABLE ea_employee (
    id bigint NOT NULL,
    name character varying(100),
    lastname character varying(100),
    email character varying(100),
    description text
);

CREATE SEQUENCE ea_employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ONLY ea_employee ALTER COLUMN id SET DEFAULT nextval('ea_employee_id_seq'::regclass);

ALTER TABLE ONLY ea_employee
    ADD CONSTRAINT ea_employee_pkey PRIMARY KEY (id);