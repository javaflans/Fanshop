package com.paralucent.services;

import java.util.ArrayList;
import java.util.List;

import com.paralucent.model.KolData;
import com.paralucent.model.KolDatasChecked;
import com.paralucent.model.KolShare;
import com.paralucent.model.Kolprofitexpdata;
import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.Role;
import com.paralucent.model.WwwOrder;
import com.paralucent.model.WwwShop;

public class DataServiceImpl extends BaseService implements DataService {
	
	public DataServiceImpl() {
		super();
		genLogger();
	}

	@Override
	public List<Menus> searchMenus(Role role, Member member) {
		return dataDao.searhMenus(role, member);
	}

	@Override
	public Role searchRoleByMember(int id) {
		return dataDao.searchRoleByMember(id);
	}

	@Override
	public List<WwwShop> searchItemListByAuth(String auth) {
		return dataDao.searchItemListByAuth(auth);
	}

	@Override
	public boolean insertUpdateShopsList(ArrayList<WwwShop> shops) {
		return dataDao.insertUpdateShopsList(shops);
	}

	@Override
	public List<WwwShop> searchItemListByKOL(String auth, String state) {
		return dataDao.searchItemListByKOL(auth,state);
	}

	@Override
	public List<KolShare> searchKOLShareList(int id) {
		return dataDao.searchKOLShareList(id);
	}

	@Override
	public List<WwwShop> searchKOLShopList(Object[] ids) {
		return dataDao.searchKOLShopList(ids);
	}

	@Override
	public KolShare searchKOLShareByID(int id) {
		return dataDao.searchKOLShareByID(id);
	}

	@Override
	public boolean insertUpdateSharesList(List<KolShare> deleteKolShare, List<KolShare> insertKolShare) {
		return dataDao.insertUpdateSharesList(deleteKolShare,insertKolShare);
	}

	@Override
	public Role searchRoleByRoleName(String roleName) {
		return dataDao.searchRoleByRoleName(roleName);
	}

	@Override
	public Member searchMember(Member member) {
		return member!=null ? dataDao.searchMember(member):null;
	}

	@Override
	public List<KolData> searchPreVerifyKOL() {
		return dataDao.searchPreVerifyKOL();
	}

	@Override
	public Member searchMemberByID(int id) {
		return dataDao.searchMember(id);
	}

	@Override
	public boolean insertUpdateKolData(List<Member> updateData) {
		return dataDao.insertUpdateKolData(updateData);
	}

	@Override
	public Member searchMemberByID(String uuid) {
		return dataDao.searchMember(uuid);
	}

	@Override
	public List<KolDatasChecked> searchPreVerifyKOLChecked() {
		return dataDao.searchPreVerifyKOLChecked();
	}

	@Override
	public List<Kolprofitexpdata> searchKolProfitExp(String userUuid) {
		return dataDao.searchKolProfitExp(userUuid);
	}

	@Override
	public boolean insertUpdatekolprofitExpList(List<WwwOrder> kolprofitExpDatas) {
		return dataDao.insertUpdatekolprofitExpList(kolprofitExpDatas);
	}

	@Override
	public WwwOrder searchWwwOrderByID(int oid, String of49) {
		return dataDao.searchWwwOrderByID(oid,of49);
	}

	@Override
	public List<Level> searchLevelAll() {
		return dataDao.searchLevelAll();
	}

	@Override
	public Level searchLevelByName(String level) {
		return dataDao.searchLevelByName(level);
	}

	@Override
	public KolDatasChecked searchKOLCheckedByUUID(String userUuid) {
	    return dataDao.searchKOLCheckedByUUID(userUuid);
	}

}