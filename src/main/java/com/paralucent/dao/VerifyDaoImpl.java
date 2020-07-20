package com.paralucent.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.RoleMember;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

public class VerifyDaoImpl
	extends
	BaseDao
	implements
	VerifyDao {

    public VerifyDaoImpl() {
	super();
	genLogger();
    }

    @Override
    public Member searchByMember(Member member) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	log.info("userMail: " + member.getUserMail());
	if (StringUtils.isNotBlank(member.getUserMail())) {
	    criteria.add(Restrictions.eq("userMail", member.getUserMail()));
	} else {
	    return null;
	}
	log.info("password: " + member.getUserPassword());
	if (StringUtils.isNotBlank(member.getUserPassword())) {
	    criteria.add(Restrictions.eq("userPassword", member.getUserPassword()));
	} else {
	    return null;
	}
	criteria.createCriteria("mailStatus").add(Restrictions.and(Restrictions.eq("stuType", "validated"), Restrictions.eq("usageTable", "member")));
	@SuppressWarnings("unchecked")
	List<Member> result = criteria.list();
	log.info("login search result: " + result);
	clearSession(session);
	if (!result.isEmpty() && result.size() > 0) {
	    return result.get(0);
	}
	return null;
    }

    @Override
    public int insertVerify(VerifyAccount verify) {
	log.info("enter insert");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.saveOrUpdate(verify);
	trx.commit();
	Serializable id = session.getIdentifier(verify);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public List<Member> queryDuplicateMember(Member member) {
	log.info("enter queryDuplicateMember");
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	if (StringUtils.isNotBlank(member.getUserMail())) {
	    criteria.add(Restrictions.eq("userMail", member.getUserMail()));
	} else {
	    return null;
	}
	List<Member> result = criteria.list();
	log.info("Query duplicate member size: " + result.size());
	clearSession(session);
	return result;
    }

    @Override
    public int insertUpdateMember(Member member) {
	log.info("enter insertUpdateMember");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.saveOrUpdate(member);
	trx.commit();
	Serializable id = session.getIdentifier(member);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public Status searchStatus(String type, String table) {
	log.info("enter searchStatusByCode");
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Status.class);
	criteria.add(Restrictions.and(Restrictions.eq("stuType", type), Restrictions.eq("usageTable", table)));
	Status result = (Status) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public Member searchByKOL(Member memberData) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	if (StringUtils.isNotBlank(memberData.getUserMail())) {
	    criteria.add(Restrictions.eq("userMail", memberData.getUserMail()));
	}
	if (StringUtils.isNotBlank(memberData.getUserUuid())) {
	    criteria.add(Restrictions.eq("userUuid", memberData.getUserUuid()));
	}
	if (StringUtils.isNotBlank(memberData.getUserName())) {
	    criteria.add(Restrictions.eq("userName", memberData.getUserName()));
	}
	Member result = (Member) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public void insertUpdateRoleMemeber(RoleMember roleMember) {
	log.info("enter insertUpdateRoleMemeber");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.saveOrUpdate(roleMember);
	trx.commit();
	clearSession(session);
    }

    @Override
    public Level searchLevel(String level) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Level.class);
	if (StringUtils.isNotBlank(level)) {
	    criteria.add(Restrictions.eq("level", level));
	}
	@SuppressWarnings("unchecked")
	Level result = (Level) criteria.uniqueResult();
	clearSession(session);
	return result;
    }
}
