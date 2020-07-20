package com.paralucent.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
	session.saveOrUpdate(member);
	trx.commit();
	Serializable id = session.getIdentifier(member);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public List<Member> search() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	@SuppressWarnings("unchecked")
	List<Member> list = criteria.list();
	clearSession(session);
	return list;
    }

    @Override
    public Member searchById(String id) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	criteria.setCacheable(Boolean.TRUE);
	criteria.add(Restrictions.eq("id", id));
	Member result = (Member) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public int update(Member member) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.saveOrUpdate(member);
	trx.commit();
	Serializable id = session.getIdentifier(member);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public int delete(Member member) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	session.delete(member);
	trx.commit();
	Serializable id = session.getIdentifier(member);
	clearSession(session);
	return (Integer) id;
    }

    @Override
    public int deleteById(String id) {
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	Member member = searchById(id);
	session.delete(member);
	trx.commit();
	Serializable ids = session.getIdentifier(member);
	clearSession(session);
	return (Integer) ids;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Menus> searhMenus(Role role, Member member) {
	Session session = sessionFactory.openSession();
	List<Menus> result = null;
	if (StringUtils.equals(member.getMemberStatus().getStuType(), "checked")
		&& StringUtils.equals(member.getMemberStatus().getUsageTable(), "member")) {
	    Criteria criteria = session.createCriteria(RoleMenu.class);
	    criteria.add(Restrictions.eq("role.id", role.getId()));
	    criteria.createCriteria("status").add(Restrictions.and(Restrictions.eq("stuType", "enabled"), Restrictions.eq("usageTable", "role")));
	    criteria.addOrder(Order.asc("menus.id"));
	    List<RoleMenu> list = criteria.list();
	    Object[] ids = new Object[list.size()];
	    for (int i = 0; i < list.size(); i++) {
		ids[i] = list.get(i).getMenus().getId();
	    }
	    session.clear();
	    criteria = session.createCriteria(Menus.class);
	    criteria.add(Restrictions.in("id", ids));
	    criteria.createCriteria("status").add(Restrictions.and(Restrictions.eq("stuType", "enabled"), Restrictions.eq("usageTable", "menus")));
	    result = criteria.list();
	} else {
	    Criteria criteria = session.createCriteria(Menus.class);
	    criteria.add(Restrictions.eq("name", "overview"));
	    result = criteria.list();
	}
	clearSession(session);
	return result;
    }

    @Override
    public Role searchRoleByMember(int id) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(RoleMember.class);
	criteria.add(Restrictions.eq("id.memberID", id));
	RoleMember result = (RoleMember) criteria.uniqueResult();
	clearSession(session);
	return result.getRole();
    }

    @Override
    public List<WwwShop> searchItemListByAuth(String auth) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(WwwShop.class);
	criteria.add(Restrictions.eq("f99", auth));
	List<WwwShop> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdateShopsList(ArrayList<WwwShop> shops) {
	log.info("enter insertUpdateShopsList");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (WwwShop shop : shops) {
	    session.update(shop);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public List<WwwShop> searchItemListByKOL(String auth, String state) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(WwwShop.class);
	criteria.add(Restrictions.and(Restrictions.eq("f99", auth), Restrictions.eq("f98", state)));
	List<WwwShop> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public List<KolShare> searchKOLShareList(int id) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(KolShare.class);
	criteria.add(Restrictions.eq("member.id", id));
	List<KolShare> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public List<WwwShop> searchKOLShopList(Object[] ids) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(WwwShop.class);
	criteria.add(Restrictions.in("id", ids));
	List<WwwShop> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public KolShare searchKOLShareByID(int id) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(KolShare.class);
	criteria.add(Restrictions.eq("id", id));
	KolShare result = (KolShare) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdateSharesList(List<KolShare> deleteKolShare, List<KolShare> insertKolShare) {
	log.info("enter insertUpdateSharesList");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (KolShare share : deleteKolShare) {
	    session.delete(share);
	}
	for (KolShare share : insertKolShare) {
	    session.saveOrUpdate(share);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public Role searchRoleByRoleName(String roleName) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Role.class);
	criteria.add(Restrictions.eq("roleName", roleName));
	Role result = (Role) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public Member searchMember(Member member) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	if (StringUtils.isNotBlank(member.getUserMail())) {
	    criteria.add(Restrictions.eq("userMail", member.getUserMail()));
	}
	if (StringUtils.isNotBlank(member.getUserPassword())) {
	    criteria.add(Restrictions.eq("userPassword", member.getUserPassword()));
	}
	Member result = (Member) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public List<KolData> searchPreVerifyKOL() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(KolData.class);
	List<KolData> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public Member searchMember(int id) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	criteria.add(Restrictions.eq("id", id));
	Member result = (Member) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public boolean insertUpdateKolData(List<Member> updateData) {
	log.info("enter insertUpdateKolData");
	Session session = sessionFactory.openSession();
	Transaction trx = session.beginTransaction();
	for (Member member : updateData) {
	    session.update(member);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public Member searchMember(String uuid) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Member.class);
	criteria.add(Restrictions.eq("userUuid", uuid));
	Member result = (Member) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public List<KolDatasChecked> searchPreVerifyKOLChecked() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(KolDatasChecked.class);
	List<KolDatasChecked> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public List<Kolprofitexpdata> searchKolProfitExp(String userUuid) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Kolprofitexpdata.class);
	criteria.add(Restrictions.eq("of49", userUuid));
	List<Kolprofitexpdata> result = criteria.list();
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
	    session.update(order);
	}
	trx.commit();
	clearSession(session);
	return Boolean.TRUE;
    }

    @Override
    public WwwOrder searchWwwOrderByID(int oid, String of49) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(WwwOrder.class);
	criteria.add(Restrictions.and(Restrictions.eq("id", oid), Restrictions.eq("f49", of49)));
	WwwOrder result = (WwwOrder) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public List<Level> searchLevelAll() {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Level.class);
	List<Level> result = criteria.list();
	clearSession(session);
	return result;
    }

    @Override
    public Level searchLevelByName(String level) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Level.class);
	criteria.add(Restrictions.eq("level", level));
	Level result = (Level) criteria.uniqueResult();
	clearSession(session);
	return result;
    }

    @Override
    public KolDatasChecked searchKOLCheckedByUUID(String userUuid) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(KolDatasChecked.class);
	criteria.add(Restrictions.eq("userUuid", userUuid));
	return (KolDatasChecked) criteria.uniqueResult();
    }
}
