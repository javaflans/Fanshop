package com.paralucent.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.paralucent.model.KolData;
import com.paralucent.model.KolDatasChecked;
import com.paralucent.model.KolShare;
import com.paralucent.model.Kolprofitexpdata;
import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.Role;
import com.paralucent.model.RoleMember;
import com.paralucent.model.RoleMenu;
import com.paralucent.model.WwwOrder;
import com.paralucent.model.WwwShop;

@Repository
public class DataDaoImpl
	extends
	BaseDao
	implements
	DataDao {

    public DataDaoImpl() {
	super();
	genLogger();
    }

    @Override
    @Transactional
    public int insert(Member member) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	Member merged = session.merge(member);
	trx.commit();
	Object id = session.getIdentifier(merged);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public List<Member> search() {
	Session session = sessionFactory.openSession();
	List<Member> list = session.createQuery("from Member", Member.class).list();
	clearSession(session);
	return list;
    }

    @Override
    public Member searchById(String id) {
	Session session = sessionFactory.openSession();
	Query<Member> query = session.createQuery("from Member where id = :id", Member.class);
	query.setCacheable(Boolean.TRUE);
	query.setParameter("id", Integer.valueOf(id));
	Member result = query.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public int update(Member member) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	Member merged = session.merge(member);
	trx.commit();
	Object id = session.getIdentifier(merged);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public int delete(Member member) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.remove(session.merge(member));
	trx.commit();
	clearSession(session);
	return member.getId();
    }

    @Override
    public int deleteById(String id) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	Member member = searchById(id);
	session.remove(session.merge(member));
	trx.commit();
	clearSession(session);
	return member.getId();
    }

    @Override
    public List<Menus> searhMenus(Role role, Member member) {
	Session session = sessionFactory.openSession();
	List<Menus> result = null;
	if (StringUtils.equals(member.getMemberStatus().getStuType(), "checked")
		&& StringUtils.equals(member.getMemberStatus().getUsageTable(), "member")) {
	    List<RoleMenu> list = session.createQuery(
		    "select rm from RoleMenu rm join rm.status s"
			    + " where rm.role.id = :roleId and s.stuType = :stuType and s.usageTable = :usageTable"
			    + " order by rm.menus.id asc", RoleMenu.class)
		    .setParameter("roleId", role.getId())
		    .setParameter("stuType", "enabled")
		    .setParameter("usageTable", "role")
		    .list();
	    Object[] ids = new Object[list.size()];
	    for (int i = 0; i < list.size(); i++) {
		ids[i] = list.get(i).getMenus().getId();
	    }
	    session.clear();
	    result = session.createQuery(
		    "select m from Menus m join m.status s"
			    + " where m.id in :ids and s.stuType = :stuType and s.usageTable = :usageTable", Menus.class)
		    .setParameterList("ids", Arrays.asList(ids))
		    .setParameter("stuType", "enabled")
		    .setParameter("usageTable", "menus")
		    .list();
	} else {
	    result = session.createQuery("from Menus where name = :name", Menus.class)
		    .setParameter("name", "overview")
		    .list();
	}
	clearSession(session);
	return result;
    }

    @Override
    public Role searchRoleByMember(int id) {
	Session session = sessionFactory.openSession();
	RoleMember result = session.createQuery("from RoleMember rm where rm.id.memberID = :memberID", RoleMember.class)
		.setParameter("memberID", id)
		.uniqueResult();
	clearSession(session);
	return result.getRole();
    }

    @Override
    public List<WwwShop> searchItemListByAuth(String auth) {
	Session session = sessionFactory.openSession();
	List<WwwShop> result = session.createQuery("from WwwShop where f99 = :auth", WwwShop.class)
		.setParameter("auth", auth)
		.list();
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdateShopsList(ArrayList<WwwShop> shops) {
	log.info("enter insertUpdateShopsList");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (WwwShop shop : shops) {
	    session.merge(shop);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public List<WwwShop> searchItemListByKOL(String auth, String state) {
	Session session = sessionFactory.openSession();
	List<WwwShop> result = session.createQuery("from WwwShop where f99 = :auth and f98 = :state", WwwShop.class)
		.setParameter("auth", auth)
		.setParameter("state", state)
		.list();
	clearSession(session);
	return result;
    }

    @Override
    public List<KolShare> searchKOLShareList(int id) {
	Session session = sessionFactory.openSession();
	List<KolShare> result = session.createQuery("from KolShare ks where ks.member.id = :id", KolShare.class)
		.setParameter("id", id)
		.list();
	clearSession(session);
	return result;
    }

    @Override
    public List<WwwShop> searchKOLShopList(Object[] ids) {
	Session session = sessionFactory.openSession();
	List<WwwShop> result = session.createQuery("from WwwShop where id in :ids", WwwShop.class)
		.setParameterList("ids", Arrays.asList(ids))
		.list();
	clearSession(session);
	return result;
    }

    @Override
    public KolShare searchKOLShareByID(int id) {
	Session session = sessionFactory.openSession();
	KolShare result = session.createQuery("from KolShare where id = :id", KolShare.class)
		.setParameter("id", id)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdateSharesList(List<KolShare> deleteKolShare, List<KolShare> insertKolShare) {
	log.info("enter insertUpdateSharesList");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (KolShare share : deleteKolShare) {
	    session.remove(session.merge(share));
	}
	for (KolShare share : insertKolShare) {
	    session.merge(share);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public Role searchRoleByRoleName(String roleName) {
	Session session = sessionFactory.openSession();
	Role result = session.createQuery("from Role where roleName = :roleName", Role.class)
		.setParameter("roleName", roleName)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public Member searchMember(Member member) {
	Session session = sessionFactory.openSession();
	StringBuilder hql = new StringBuilder("from Member where 1 = 1");
	if (StringUtils.isNotBlank(member.getUserMail())) {
	    hql.append(" and userMail = :userMail");
	}
	if (StringUtils.isNotBlank(member.getUserPassword())) {
	    hql.append(" and userPassword = :userPassword");
	}
	Query<Member> query = session.createQuery(hql.toString(), Member.class);
	if (StringUtils.isNotBlank(member.getUserMail())) {
	    query.setParameter("userMail", member.getUserMail());
	}
	if (StringUtils.isNotBlank(member.getUserPassword())) {
	    query.setParameter("userPassword", member.getUserPassword());
	}
	Member result = query.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public List<KolData> searchPreVerifyKOL() {
	Session session = sessionFactory.openSession();
	List<KolData> result = session.createQuery("from KolData", KolData.class).list();
	clearSession(session);
	return result;
    }

    @Override
    public Member searchMember(int id) {
	Session session = sessionFactory.openSession();
	Member result = session.createQuery("from Member where id = :id", Member.class)
		.setParameter("id", id)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdateKolData(List<Member> updateData) {
	log.info("enter insertUpdateKolData");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (Member member : updateData) {
	    session.merge(member);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public Member searchMember(String uuid) {
	Session session = sessionFactory.openSession();
	Member result = session.createQuery("from Member where userUuid = :userUuid", Member.class)
		.setParameter("userUuid", uuid)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public List<KolDatasChecked> searchPreVerifyKOLChecked() {
	Session session = sessionFactory.openSession();
	List<KolDatasChecked> result = session.createQuery("from KolDatasChecked", KolDatasChecked.class).list();
	clearSession(session);
	return result;
    }

    @Override
    public List<Kolprofitexpdata> searchKolProfitExp(String userUuid) {
	Session session = sessionFactory.openSession();
	List<Kolprofitexpdata> result = session.createQuery("from Kolprofitexpdata where of49 = :of49", Kolprofitexpdata.class)
		.setParameter("of49", userUuid)
		.list();
	log.info("size: " + result.size());
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdatekolprofitExpList(List<WwwOrder> orderList) {
	log.info("enter insertUpdatekolprofitExpList");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (WwwOrder order : orderList) {
	    session.merge(order);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public WwwOrder searchWwwOrderByID(int oid, String of49) {
	Session session = sessionFactory.openSession();
	WwwOrder result = session.createQuery("from WwwOrder where id = :id and f49 = :f49", WwwOrder.class)
		.setParameter("id", oid)
		.setParameter("f49", of49)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public List<Level> searchLevelAll() {
	Session session = sessionFactory.openSession();
	List<Level> result = session.createQuery("from Level", Level.class).list();
	clearSession(session);
	return result;
    }

    @Override
    public Level searchLevelByName(String level) {
	Session session = sessionFactory.openSession();
	Level result = session.createQuery("from Level where level = :level", Level.class)
		.setParameter("level", level)
		.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public KolDatasChecked searchKOLCheckedByUUID(String userUuid) {
	Session session = sessionFactory.openSession();
	return session.createQuery("from KolDatasChecked where userUuid = :userUuid", KolDatasChecked.class)
		.setParameter("userUuid", userUuid)
		.uniqueResult();
    }
}
