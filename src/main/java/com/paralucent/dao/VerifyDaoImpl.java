package com.paralucent.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.RoleMember;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

@Repository
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
	log.info("userMail: " + member.getUserMail());
	if (StringUtils.isBlank(member.getUserMail())) {
	    return null;
	}
	log.info("password: " + member.getUserPassword());
	if (StringUtils.isBlank(member.getUserPassword())) {
	    return null;
	}
	List<Member> result = session.createQuery(
		"select m from Member m join m.mailStatus s"
			+ " where m.userMail = :userMail and m.userPassword = :userPassword"
			+ " and s.stuType = :stuType and s.usageTable = :usageTable", Member.class)
		.setParameter("userMail", member.getUserMail())
		.setParameter("userPassword", member.getUserPassword())
		.setParameter("stuType", "validated")
		.setParameter("usageTable", "member")
		.list();
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
	VerifyAccount merged = session.merge(verify);
	trx.commit();
	Object id = session.getIdentifier(merged);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public List<Member> queryDuplicateMember(Member member) {
	log.info("enter queryDuplicateMember");
	Session session = sessionFactory.openSession();
	if (StringUtils.isBlank(member.getUserMail())) {
	    return null;
	}
	List<Member> result = session.createQuery("from Member where userMail = :userMail", Member.class)
		.setParameter("userMail", member.getUserMail())
		.list();
	log.info("Query duplicate member size: " + result.size());
	clearSession(session);
	return result;
    }

    @Override
    public int insertUpdateMember(Member member) {
	log.info("enter insertUpdateMember");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	Member merged = session.merge(member);
	trx.commit();
	Object id = session.getIdentifier(merged);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public Status searchStatus(String type, String table) {
	log.info("enter searchStatusByCode");
	Session session = sessionFactory.openSession();
	Status result = session.createQuery("from Status where stuType = :stuType and usageTable = :usageTable", Status.class)
		.setParameter("stuType", type)
		.setParameter("usageTable", table)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public Member searchByKOL(Member memberData) {
	Session session = sessionFactory.openSession();
	StringBuilder hql = new StringBuilder("from Member where 1 = 1");
	if (StringUtils.isNotBlank(memberData.getUserMail())) {
	    hql.append(" and userMail = :userMail");
	}
	if (StringUtils.isNotBlank(memberData.getUserUuid())) {
	    hql.append(" and userUuid = :userUuid");
	}
	if (StringUtils.isNotBlank(memberData.getUserName())) {
	    hql.append(" and userName = :userName");
	}
	Query<Member> query = session.createQuery(hql.toString(), Member.class);
	if (StringUtils.isNotBlank(memberData.getUserMail())) {
	    query.setParameter("userMail", memberData.getUserMail());
	}
	if (StringUtils.isNotBlank(memberData.getUserUuid())) {
	    query.setParameter("userUuid", memberData.getUserUuid());
	}
	if (StringUtils.isNotBlank(memberData.getUserName())) {
	    query.setParameter("userName", memberData.getUserName());
	}
	Member result = query.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public void insertUpdateRoleMemeber(RoleMember roleMember) {
	log.info("enter insertUpdateRoleMemeber");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.merge(roleMember);
	trx.commit();
	clearSession(session);
    }

    @Override
    public Level searchLevel(String level) {
	Session session = sessionFactory.openSession();
	StringBuilder hql = new StringBuilder("from Level where 1 = 1");
	if (StringUtils.isNotBlank(level)) {
	    hql.append(" and level = :level");
	}
	Query<Level> query = session.createQuery(hql.toString(), Level.class);
	if (StringUtils.isNotBlank(level)) {
	    query.setParameter("level", level);
	}
	Level result = query.uniqueResult();
	clearSession(session);
	return result;
    }
}
