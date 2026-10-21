--
-- PostgreSQL database dump
--

\restrict mT5czJvH1ZotgtpDNBHa50cNaezzdJDJ41M6HNV1SFxpPW1UNeFftpqB5Vt6N1Q

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: kol_datas; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.kol_datas (
    businesstarget integer NOT NULL,
    hottarget integer NOT NULL,
    id integer NOT NULL,
    sharetarget integer NOT NULL,
    transtarget integer NOT NULL,
    userproductcount integer NOT NULL,
    zipcode integer NOT NULL,
    level character varying(255),
    mailstatus character varying(255),
    useraddress character varying(255),
    userarea character varying(255),
    userbirthday character varying(255),
    usercity character varying(255),
    userdatecreated character varying(255),
    userlastdatemodify character varying(255),
    userlocalname character varying(255),
    usermail character varying(255),
    username character varying(255),
    userpassword character varying(255),
    userphone character varying(255),
    userphotopath character varying(255),
    userstatus character varying(255),
    useruuid character varying(255)
);


--
-- Name: kol_datas_checked; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.kol_datas_checked (
    businesstarget integer NOT NULL,
    hottarget integer NOT NULL,
    id integer NOT NULL,
    sharetarget integer NOT NULL,
    transtarget integer NOT NULL,
    userproductcount integer NOT NULL,
    zipcode integer NOT NULL,
    level character varying(255),
    mailstatus character varying(255),
    useraddress character varying(255),
    userarea character varying(255),
    userbirthday character varying(255),
    usercity character varying(255),
    userdatecreated character varying(255),
    userlastdatemodify character varying(255),
    userlocalname character varying(255),
    usermail character varying(255),
    username character varying(255),
    userpassword character varying(255),
    userphone character varying(255),
    userphotopath character varying(255),
    userstatus character varying(255),
    useruuid character varying(255)
);


--
-- Name: kol_share; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.kol_share (
    kolid integer,
    prodid integer,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    id character varying(255) NOT NULL,
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: kolprofitexpdata; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.kolprofitexpdata (
    oid integer NOT NULL,
    sid integer NOT NULL,
    level character varying(255),
    of00 character varying(255),
    of01 character varying(255),
    of02 character varying(255),
    of03 character varying(255),
    of04 character varying(255),
    of05 character varying(255),
    of10 character varying(255),
    of11 character varying(255),
    of13 character varying(255),
    of40 character varying(255),
    of46 character varying(255),
    of47 character varying(255),
    of48 character varying(255),
    of49 character varying(255),
    sf00 character varying(255),
    sf03 character varying(255),
    sf04 character varying(255),
    sf05 character varying(255),
    sf06 character varying(255),
    sf93 character varying(255),
    sf95 character varying(255),
    sf96 character varying(255),
    sf97 character varying(255),
    sf98 character varying(255),
    sf99 character varying(255),
    sf01 oid,
    sf02 oid
);


--
-- Name: level; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.level (
    id integer NOT NULL,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    level character varying(255),
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: member; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.member (
    businesstarget integer NOT NULL,
    hottarget integer NOT NULL,
    id integer NOT NULL,
    mailstatus integer,
    sharetarget integer NOT NULL,
    transtarget integer NOT NULL,
    userbirthday date,
    userlevel integer,
    userproductcount integer NOT NULL,
    userstatus integer,
    zipcode integer NOT NULL,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    useraddress character varying(255),
    userarea character varying(255),
    usercity character varying(255),
    usercreated character varying(255),
    userlastmodify character varying(255),
    userlocalname character varying(255),
    usermail character varying(255),
    username character varying(255),
    userpassword character varying(255),
    userphone character varying(255),
    userphotopath character varying(255),
    useruuid character varying(255)
);


--
-- Name: menus; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.menus (
    id integer NOT NULL,
    parentid integer,
    status integer,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    description character varying(255),
    icon character varying(255),
    label character varying(255),
    name character varying(255),
    path character varying(255),
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role (
    id integer NOT NULL,
    status integer,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    roledesc character varying(255),
    rolename character varying(255),
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: role_member; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role_member (
    id integer NOT NULL,
    memberid integer NOT NULL,
    roleid integer,
    status integer,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: role_menu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.role_menu (
    id integer NOT NULL,
    menuid integer,
    roleid integer,
    status integer,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: status; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.status (
    id integer NOT NULL,
    userdatecreated timestamp(6) without time zone,
    userlastdatemodify timestamp(6) without time zone,
    studesc character varying(255),
    stutype character varying(255),
    usagetable character varying(255),
    usercreated character varying(255),
    userlastmodify character varying(255)
);


--
-- Name: verify_account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.verify_account (
    id integer NOT NULL,
    memberid integer,
    verifyed boolean NOT NULL,
    logindate timestamp(6) without time zone,
    logoutdate timestamp(6) without time zone,
    memberlocalname character varying(255),
    memberusername character varying(255),
    remotehost character varying(255),
    remoteipaddress character varying(255),
    remoteuser character varying(255)
);


--
-- Name: www_order; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.www_order (
    boot integer NOT NULL,
    id integer NOT NULL,
    f00 character varying(255),
    f01 character varying(255),
    f02 character varying(255),
    f03 character varying(255),
    f04 character varying(255),
    f05 character varying(255),
    f06 character varying(255),
    f07 character varying(255),
    f08 character varying(255),
    f09 character varying(255),
    f10 character varying(255),
    f11 character varying(255),
    f12 character varying(255),
    f13 character varying(255),
    f14 character varying(255),
    f15 character varying(255),
    f16 character varying(255),
    f17 character varying(255),
    f18 character varying(255),
    f19 character varying(255),
    f20 character varying(255),
    f21 character varying(255),
    f22 character varying(255),
    f23 character varying(255),
    f24 character varying(255),
    f25 character varying(255),
    f26 character varying(255),
    f27 character varying(255),
    f28 character varying(255),
    f29 character varying(255),
    f30 character varying(255),
    f31 character varying(255),
    f32 character varying(255),
    f33 character varying(255),
    f34 character varying(255),
    f35 character varying(255),
    f36 character varying(255),
    f37 character varying(255),
    f38 character varying(255),
    f39 character varying(255),
    f40 character varying(255),
    f41 character varying(255),
    f42 character varying(255),
    f43 character varying(255),
    f44 character varying(255),
    f45 character varying(255),
    f46 character varying(255),
    f47 character varying(255),
    f48 character varying(255),
    f49 character varying(255)
);


--
-- Name: www_shop; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.www_shop (
    boot integer NOT NULL,
    f05 integer NOT NULL,
    f06 integer NOT NULL,
    f95 integer NOT NULL,
    f96 integer NOT NULL,
    f97 integer NOT NULL,
    id integer NOT NULL,
    f18 timestamp(6) without time zone,
    f19 timestamp(6) without time zone,
    f00 character varying(255),
    f01 character varying(255),
    f02 character varying(255),
    f03 character varying(255),
    f04 character varying(255),
    f07 character varying(255),
    f08 character varying(255),
    f09 character varying(255),
    f10 character varying(255),
    f11 character varying(255),
    f12 character varying(255),
    f13 character varying(255),
    f14 character varying(255),
    f15 character varying(255),
    f16 character varying(255),
    f17 character varying(255),
    f20 character varying(255),
    f21 character varying(255),
    f22 character varying(255),
    f23 character varying(255),
    f24 character varying(255),
    f25 character varying(255),
    f26 character varying(255),
    f27 character varying(255),
    f28 character varying(255),
    f29 character varying(255),
    f30 character varying(255),
    f31 character varying(255),
    f32 character varying(255),
    f33 character varying(255),
    f34 character varying(255),
    f35 character varying(255),
    f36 character varying(255),
    f37 character varying(255),
    f38 character varying(255),
    f39 character varying(255),
    f40 character varying(255),
    f41 character varying(255),
    f42 character varying(255),
    f43 character varying(255),
    f44 character varying(255),
    f45 character varying(255),
    f46 character varying(255),
    f47 character varying(255),
    f48 character varying(255),
    f49 character varying(255),
    f50 character varying(255),
    f51 character varying(255),
    f52 character varying(255),
    f53 character varying(255),
    f54 character varying(255),
    f55 character varying(255),
    f56 character varying(255),
    f57 character varying(255),
    f58 character varying(255),
    f59 character varying(255),
    f60 character varying(255),
    f61 character varying(255),
    f62 character varying(255),
    f63 character varying(255),
    f64 character varying(255),
    f65 character varying(255),
    f66 character varying(255),
    f67 character varying(255),
    f68 character varying(255),
    f69 character varying(255),
    f70 character varying(255),
    f71 character varying(255),
    f72 character varying(255),
    f73 character varying(255),
    f74 character varying(255),
    f75 character varying(255),
    f76 character varying(255),
    f77 character varying(255),
    f78 character varying(255),
    f79 character varying(255),
    f80 character varying(255),
    f81 character varying(255),
    f82 character varying(255),
    f83 character varying(255),
    f84 character varying(255),
    f85 character varying(255),
    f86 character varying(255),
    f87 character varying(255),
    f88 character varying(255),
    f89 character varying(255),
    f90 character varying(255),
    f91 character varying(255),
    f92 character varying(255),
    f93 character varying(255),
    f94 character varying(255),
    f98 character varying(255),
    f99 character varying(255)
);


--
-- Name: kol_datas_checked kol_datas_checked_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.kol_datas_checked
    ADD CONSTRAINT kol_datas_checked_pkey PRIMARY KEY (id);


--
-- Name: kol_datas kol_datas_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.kol_datas
    ADD CONSTRAINT kol_datas_pkey PRIMARY KEY (id);


--
-- Name: kol_share kol_share_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.kol_share
    ADD CONSTRAINT kol_share_pkey PRIMARY KEY (id);


--
-- Name: kolprofitexpdata kolprofitexpdata_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.kolprofitexpdata
    ADD CONSTRAINT kolprofitexpdata_pkey PRIMARY KEY (oid);


--
-- Name: level level_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.level
    ADD CONSTRAINT level_pkey PRIMARY KEY (id);


--
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- Name: menus menus_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT menus_pkey PRIMARY KEY (id);


--
-- Name: role_member role_member_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_member
    ADD CONSTRAINT role_member_pkey PRIMARY KEY (id, memberid);


--
-- Name: role_menu role_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_menu
    ADD CONSTRAINT role_menu_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: verify_account verify_account_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.verify_account
    ADD CONSTRAINT verify_account_pkey PRIMARY KEY (id);


--
-- Name: www_order www_order_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.www_order
    ADD CONSTRAINT www_order_pkey PRIMARY KEY (id);


--
-- Name: www_shop www_shop_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.www_shop
    ADD CONSTRAINT www_shop_pkey PRIMARY KEY (id);


--
-- Name: role_menu fk19erld63557br0i88ck0avrxp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_menu
    ADD CONSTRAINT fk19erld63557br0i88ck0avrxp FOREIGN KEY (roleid) REFERENCES public.role(id);


--
-- Name: member fk21g5i8oq0lxbcgd1ttbodno6m; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT fk21g5i8oq0lxbcgd1ttbodno6m FOREIGN KEY (userlevel) REFERENCES public.level(id);


--
-- Name: role_menu fk40mtc7ekjrhl5r85cr4j1jflo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_menu
    ADD CONSTRAINT fk40mtc7ekjrhl5r85cr4j1jflo FOREIGN KEY (menuid) REFERENCES public.menus(id);


--
-- Name: kol_share fk6gubqd59ifh22bx89enn6802y; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.kol_share
    ADD CONSTRAINT fk6gubqd59ifh22bx89enn6802y FOREIGN KEY (kolid) REFERENCES public.member(id);


--
-- Name: verify_account fka0660glrptldwbe7glp8kldo6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.verify_account
    ADD CONSTRAINT fka0660glrptldwbe7glp8kldo6 FOREIGN KEY (memberid) REFERENCES public.member(id);


--
-- Name: role_menu fkc2123b74h2j5exghitfrw819b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_menu
    ADD CONSTRAINT fkc2123b74h2j5exghitfrw819b FOREIGN KEY (status) REFERENCES public.status(id);


--
-- Name: member fkc56i5l0lv92q5ioq2ojdnp1ul; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT fkc56i5l0lv92q5ioq2ojdnp1ul FOREIGN KEY (userstatus) REFERENCES public.status(id);


--
-- Name: member fkekcr4nfnyrgxqmqdkx08pi5v1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT fkekcr4nfnyrgxqmqdkx08pi5v1 FOREIGN KEY (mailstatus) REFERENCES public.status(id);


--
-- Name: kol_share fkgax6vug6171ve611kf222tx7q; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.kol_share
    ADD CONSTRAINT fkgax6vug6171ve611kf222tx7q FOREIGN KEY (prodid) REFERENCES public.www_shop(id);


--
-- Name: menus fkipaed3f08ics1ust8ollkljso; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT fkipaed3f08ics1ust8ollkljso FOREIGN KEY (parentid) REFERENCES public.menus(id);


--
-- Name: role_member fkjdtau536no3ktov3nvusqn2ik; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_member
    ADD CONSTRAINT fkjdtau536no3ktov3nvusqn2ik FOREIGN KEY (status) REFERENCES public.status(id);


--
-- Name: menus fkpvim4cxkrqnb2e64hgtii4bwk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.menus
    ADD CONSTRAINT fkpvim4cxkrqnb2e64hgtii4bwk FOREIGN KEY (status) REFERENCES public.status(id);


--
-- Name: role_member fkr82ry3gq9mvy90q3tjb5as2jt; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_member
    ADD CONSTRAINT fkr82ry3gq9mvy90q3tjb5as2jt FOREIGN KEY (roleid) REFERENCES public.role(id);


--
-- Name: role_member fkrqfg36mwg1xkbyc5e86nrol2h; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role_member
    ADD CONSTRAINT fkrqfg36mwg1xkbyc5e86nrol2h FOREIGN KEY (memberid) REFERENCES public.member(id);


--
-- Name: role fkt4whsldg1mahsdkih8op88s5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT fkt4whsldg1mahsdkih8op88s5 FOREIGN KEY (status) REFERENCES public.status(id);


--
-- PostgreSQL database dump complete
--

\unrestrict mT5czJvH1ZotgtpDNBHa50cNaezzdJDJ41M6HNV1SFxpPW1UNeFftpqB5Vt6N1Q

