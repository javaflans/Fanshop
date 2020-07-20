package com.paralucent.dao;

import java.util.List;

import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.RoleMember;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

public interface VerifyDao {

    public Member searchByMember(Member member);

    public int insertVerify(VerifyAccount verify);

    public List<Member> queryDuplicateMember(Member member);

    public int insertUpdateMember(Member member);

    public Status searchStatus(String type, String table);

    public Member searchByKOL(Member memberData);

    public void insertUpdateRoleMemeber(RoleMember roleMember);

    public Level searchLevel(String level);
}
