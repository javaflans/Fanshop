package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the verify_account database table.
 * 
 */
@Entity
@Table(name="verify_account")
@NamedQuery(name="VerifyAccount.findAll", query="SELECT v FROM VerifyAccount v")
public class VerifyAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutDate;

	private String memberLocalName;

	private String memberUsername;

	private String remoteHost;

	private String remoteIpAddress;

	private String remoteUser;

	private boolean verifyed;

	//uni-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="memberID")
	private Member member;

	public VerifyAccount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return this.logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getMemberLocalName() {
		return this.memberLocalName;
	}

	public void setMemberLocalName(String memberLocalName) {
		this.memberLocalName = memberLocalName;
	}

	public String getMemberUsername() {
		return this.memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	public String getRemoteHost() {
		return this.remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemoteIpAddress() {
		return this.remoteIpAddress;
	}

	public void setRemoteIpAddress(String remoteIpAddress) {
		this.remoteIpAddress = remoteIpAddress;
	}

	public String getRemoteUser() {
		return this.remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public boolean getVerifyed() {
		return this.verifyed;
	}

	public void setVerifyed(boolean verifyed) {
		this.verifyed = verifyed;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}