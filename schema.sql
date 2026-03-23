-- Bank Application Schema
-- PostgreSQL DDL

CREATE TABLE public."User" (
    accno       VARCHAR(10)     PRIMARY KEY,
    fname       VARCHAR(50)     NOT NULL,
    lname       VARCHAR(50)     NOT NULL,
    phone       VARCHAR(10)     NOT NULL UNIQUE,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    balance     NUMERIC(15, 2)  NOT NULL DEFAULT 0.00,
    passwordhash VARCHAR(64)    NOT NULL
);

CREATE TABLE public."Admin" (
    empid       VARCHAR(10)     PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    phone       VARCHAR(10)     NOT NULL UNIQUE,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    passwordhash VARCHAR(64)    NOT NULL
);

CREATE TABLE public."UserSecret" (
    accno       VARCHAR(10)     PRIMARY KEY,
    secret      VARCHAR(100)    NOT NULL,
    CONSTRAINT fk_usersecret_user FOREIGN KEY (accno) REFERENCES public."User"(accno) ON DELETE CASCADE
);

CREATE TABLE public."AdminSecret" (
    empid       VARCHAR(10)     PRIMARY KEY,
    secret      VARCHAR(100)    NOT NULL,
    CONSTRAINT fk_adminsecret_admin FOREIGN KEY (empid) REFERENCES public."Admin"(empid) ON DELETE CASCADE
);

CREATE TABLE public."Transaction" (
    id          SERIAL          PRIMARY KEY,
    src         VARCHAR(10)     NOT NULL,
    dest        VARCHAR(10)     NOT NULL,
    amount      NUMERIC(15, 2)  NOT NULL,
    date        DATE            NOT NULL,
    time        TIME            NOT NULL,
    CONSTRAINT fk_transaction_src  FOREIGN KEY (src)  REFERENCES public."User"(accno),
    CONSTRAINT fk_transaction_dest FOREIGN KEY (dest) REFERENCES public."User"(accno)
);
