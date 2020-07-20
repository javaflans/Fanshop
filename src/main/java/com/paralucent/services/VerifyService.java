package com.paralucent.services;

import java.util.List;

import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.RoleMember;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

public interface VerifyService {

    public boolean logAccoundData(VerifyAccount verify);

    public Member login(Member employee);

    public boolean checkDuplicateMember(Member member);

    public boolean insertUpdateMember(Member member);

    public Status searchStatus(String type, String table);

    public Member checkMemberVerifyed(String kolUuid, String kolName, String kolmail);

    public List<Member> queryDuplicateMember(Member member);

    public Member checkMemberVerifyed(String kolmail);

    public void insertUpdateRoleMemeber(RoleMember roleMember);

    public Level searchLevel(String level);
}
