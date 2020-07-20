package com.paralucent.dao;

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

public interface DataDao {

    public int insert(Member member);

    public List<Member> search();

    public Member searchById(String id);

    public int update(Member member);

    public int delete(Member member);

    public int deleteById(String id);

    public List<Menus> searhMenus(Role role, Member member);

    public Role searchRoleByMember(int id);

    public List<WwwShop> searchItemListByAuth(String auth);

    public boolean insertUpdateShopsList(ArrayList<WwwShop> shops);

    public List<WwwShop> searchItemListByKOL(String auth, String state);

    public List<KolShare> searchKOLShareList(int id);

    public List<WwwShop> searchKOLShopList(Object[] kolShares);

    public KolShare searchKOLShareByID(int id);

    public boolean insertUpdateSharesList(List<KolShare> deleteKolShare, List<KolShare> insertKolShare);

    public Role searchRoleByRoleName(String roleName);

    public Member searchMember(Member member);

    public List<KolData> searchPreVerifyKOL();

    public Member searchMember(int id);

    public boolean insertUpdateKolData(List<Member> updateData);

    public Member searchMember(String uuid);

    public List<KolDatasChecked> searchPreVerifyKOLChecked();

    public List<Kolprofitexpdata> searchKolProfitExp(String userUuid);

    public boolean insertUpdatekolprofitExpList(List<WwwOrder> kolprofitExpDatas);

    public WwwOrder searchWwwOrderByID(int oid, String of49);

    public List<Level> searchLevelAll();

    public Level searchLevelByName(String level);

    public KolDatasChecked searchKOLCheckedByUUID(String userUuid);
}
