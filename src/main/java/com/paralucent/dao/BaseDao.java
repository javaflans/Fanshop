package com.paralucent.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.paralucent.controller.BaseController;

public class BaseDao {

    @Autowired
    SessionFactory sessionFactory;
    Logger log;

    protected void genLogger() {
	log = Logger.getLogger(BaseController.class.getClass().getName());
    }

    protected void clearSession(Session session) {
	session.clear();
	session.close();
    }
}
