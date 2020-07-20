package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the role_member database table.
 * 
 */
@Embeddable
public class RoleMemberPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(insertable=false, updatable=false)
	private int memberID;

	public RoleMemberPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberID() {
		return this.memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoleMemberPK)) {
			return false;
		}
		RoleMemberPK castOther = (RoleMemberPK)other;
		return 
			(this.id == castOther.id)
			&& (this.memberID == castOther.memberID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.memberID;
		
		return hash;
	}
}