package com.paralucent.viewmodel;

import java.io.Serializable;
import javax.persistence.*;


public class kolDatasCheckedBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int businessTarget;

	private int hotTarget;

	private int id;

	private String level;

	private String mailStatus;

	private int shareTarget;

	private int transTarget;

	private String userAddress;

	private String userArea;

	private String userBirthday;

	private String userCity;

	private String userDateCreated;

	private String userLastDateModify;

	private String userLocalName;

	private String userMail;

	private String userName;

	private String userPassword;

	private String userPhone;

	private String userPhotoPath;

	private int userProductCount;

	private String userStatus;

	private String userUuid;

	private int zipCode;
	
	private String koltotalProfitExp;
	
	private int totalProfitExp;

	public kolDatasCheckedBO() {
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMailStatus() {
		return this.mailStatus;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
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

	public String getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserDateCreated() {
		return this.userDateCreated;
	}

	public void setUserDateCreated(String userDateCreated) {
		this.userDateCreated = userDateCreated;
	}

	public String getUserLastDateModify() {
		return this.userLastDateModify;
	}

	public void setUserLastDateModify(String userLastDateModify) {
		this.userLastDateModify = userLastDateModify;
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

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
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

	public String getKoltotalProfitExp() {
		return koltotalProfitExp;
	}

	public void setKoltotalProfitExp(String koltotalProfitExp) {
		this.koltotalProfitExp = koltotalProfitExp;
	}

	public int getTotalProfitExp() {
		return totalProfitExp;
	}

	public void setTotalProfitExp(int totalProfitExp) {
		this.totalProfitExp = totalProfitExp;
	}


}