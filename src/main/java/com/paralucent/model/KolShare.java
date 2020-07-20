package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the kol_share database table.
 * 
 */
@Entity
@Table(name="kol_share")
@NamedQuery(name="KolShare.findAll", query="SELECT k FROM KolShare k")
public class KolShare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	//uni-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="kolID")
	private Member member;

	//uni-directional many-to-one association to WwwShop
	@ManyToOne
	@JoinColumn(name="prodID")
	private WwwShop wwwShop;

	public KolShare() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public WwwShop getWwwShop() {
		return this.wwwShop;
	}

	public void setWwwShop(WwwShop wwwShop) {
		this.wwwShop = wwwShop;
	}

}