package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import java.util.Date;


/**
 * The persistent class for the role_member database table.
 * 
 */
@Entity
@Table(name="role_member")
@NamedQuery(name="RoleMember.findAll", query="SELECT r FROM RoleMember r")
public class RoleMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleMemberPK id;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	//uni-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="memberID", insertable = false, updatable = false)
	private Member member;

	//uni-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roleID")
	private Role role;

	//uni-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;

	public RoleMember() {
	}

	public RoleMemberPK getId() {
		return this.id;
	}

	public void setId(RoleMemberPK id) {
		this.id = id;
	}

	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getUserDateCreated() {
		return this.userDateCreated;
	}

	public void setUserDateCreated(Date userDateCreated) {
		this.userDateCreated = userDateCreated;
	}

	public Date getUserLastDateModify() {
		return this.userLastDateModify;
	}

	public void setUserLastDateModify(Date userLastDateModify) {
		this.userLastDateModify = userLastDateModify;
	}

	public String getUserLastModify() {
		return this.userLastModify;
	}

	public void setUserLastModify(String userLastModify) {
		this.userLastModify = userLastModify;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}