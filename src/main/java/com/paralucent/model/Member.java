package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@Table(name="member")
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int businessTarget;

	private int hotTarget;

	private int shareTarget;

	private int transTarget;

	private String userAddress;

	private String userArea;

	@Temporal(TemporalType.DATE)
	private Date userBirthday;

	private String userCity;

	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userLastDateModify;

	private String userLastModify;

	private String userLocalName;

	private String userMail;

	private String userName;

	private String userPassword;

	private String userPhone;

	private String userPhotoPath;

	private int userProductCount;

	private String userUuid;

	private int zipCode;

	//uni-directional many-to-one association to Level
	@ManyToOne
	@JoinColumn(name="userLevel")
	private Level level;

	//uni-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="mailStatus")
	private Status mailStatus;

	//uni-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="userStatus")
	private Status memberStatus;

	public Member() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusinessTarget() {
		return this.businessTarget;
	}

	public void setBusinessTarget(int businessTarget) {
		this.businessTarget = businessTarget;
	}

	public int getHotTarget() {
		return this.hotTarget;
	}

	public void setHotTarget(int hotTarget) {
		this.hotTarget = hotTarget;
	}

	public int getShareTarget() {
		return this.shareTarget;
	}

	public void setShareTarget(int shareTarget) {
		this.shareTarget = shareTarget;
	}

	public int getTransTarget() {
		return this.transTarget;
	}

	public void setTransTarget(int transTarget) {
		this.transTarget = transTarget;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserArea() {
		return this.userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public Date getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
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

	public String getUserLocalName() {
		return this.userLocalName;
	}

	public void setUserLocalName(String userLocalName) {
		this.userLocalName = userLocalName;
	}

	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPhotoPath() {
		return this.userPhotoPath;
	}

	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}

	public int getUserProductCount() {
		return this.userProductCount;
	}

	public void setUserProductCount(int userProductCount) {
		this.userProductCount = userProductCount;
	}

	public String getUserUuid() {
		return this.userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Level getLevel() {
		return this.level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Status getMailStatus() {
		return this.mailStatus;
	}

	public void setMailStatus(Status mailStatus) {
		this.mailStatus = mailStatus;
	}

	public Status getMemberStatus() {
		return this.memberStatus;
	}

	public void setMemberStatus(Status memberStatus) {
		this.memberStatus = memberStatus;
	}

}