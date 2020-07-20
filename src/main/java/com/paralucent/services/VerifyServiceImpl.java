package com.paralucent.services;

import java.util.List;

import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.RoleMember;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

public class VerifyServiceImpl
	extends
	BaseService
	implements
	VerifyService {

    public VerifyServiceImpl() {
	super();
	genLogger();
    }

    @Override
    public Member login(Member member) {
	return verifyDao.searchByMember(member);
    }

    @Override
    public boolean logAccoundData(VerifyAccount verify) {
	int insertId = verifyDao.insertVerify(verify);
	if (insertId > 0) {
	    return Boolean.TRUE;
	} else {
	    return Boolean.FALSE;
	}
    }

    @Override
    public boolean checkDuplicateMember(Member member) {
	List<Member> members = (List<Member>) verifyDao.queryDuplicateMember(member);
	log.info("query Duplicate size: " + (members != null ? members.size() : 0));
	return (members != null ? members.size() <= 0 : Boolean.TRUE);
    }

    @Override
    public boolean insertUpdateMember(Member member) {
	int insertId = verifyDao.insertUpdateMember(member);
	return Boolean.TRUE;
    }

    @Override
    public Status searchStatus(String type, String table) {
	return verifyDao.searchStatus(type, table);
    }

    @Override
    public Member checkMemberVerifyed(String kolUuid, String kolName, String kolmail) {
	Member memberData = new Member();
	memberData.setUserMail(kolmail);
	memberData.setUserName(kolName);
	memberData.setUserUuid(kolUuid);
	Member result = verifyDao.searchByKOL(memberData);
	return result;
    }

    @Override
    public List<Member> queryDuplicateMember(Member member) {
	List<Member> members = (List<Member>) verifyDao.queryDuplicateMember(member);
	log.info("query Duplicate size: " + (members != null ? members.size() : 0));
	return members;
    }

    @Override
    public Member checkMemberVerifyed(String kolmail) {
	Member memberData = new Member();
	memberData.setUserMail(kolmail);
	Member result = verifyDao.searchByKOL(memberData);
	return result;
    }

    @Override
    public void insertUpdateRoleMemeber(RoleMember roleMember) {
	verifyDao.insertUpdateRoleMemeber(roleMember);
    }

    @Override
    public Level searchLevel(String level) {
	return verifyDao.searchLevel(level);
    }
}
