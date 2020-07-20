package com.paralucent.services;

import org.apache.log4j.Logger;
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
		log = Logger.getLogger(BaseController.class.getClass().getName());
	}

}
