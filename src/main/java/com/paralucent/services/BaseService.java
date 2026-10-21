package com.paralucent.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.paralucent.controller.BaseController;
import com.paralucent.dao.DataDao;
import com.paralucent.dao.VerifyDao;

public class BaseService {
	
	@Autowired
	DataDao dataDao;
	
	@Autowired
	VerifyDao verifyDao;
	
	@Autowired
	SessionFactory sessionFactory;
	Logger log;
	
	protected void genLogger() {
		log = LoggerFactory.getLogger(BaseController.class.getClass().getName());
	}

}
