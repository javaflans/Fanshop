package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kolprofitexpdata database table.
 * 
 */
@Entity
@Table(name="kolprofitexpdata")
@NamedQuery(name="Kolprofitexpdata.findAll", query="SELECT k FROM Kolprofitexpdata k")
public class Kolprofitexpdata implements Serializable {
	private static final long serialVersionUID = 1L;

	private String level;

	private String of00;

	private String of01;

	private String of02;

	private String of03;

	private String of04;

	private String of05;

	private String of10;

	private String of11;

	private String of13;

	private String of40;

	private String of46;

	private String of47;

	private String of48;

	private String of49;

	@Id
	private int oid;

	private String sf00;

	@Lob
	private String sf01;

	@Lob
	private String sf02;

	private String sf03;

	private String sf04;

	private String sf05;

	private String sf06;

	private String sf93;

	private String sf95;

	private String sf96;

	private String sf97;

	private String sf98;

	private String sf99;

	private int sid;

	public Kolprofitexpdata() {
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getOf00() {
		return this.of00;
	}

	public void setOf00(String of00) {
		this.of00 = of00;
	}

	public String getOf01() {
		return this.of01;
	}

	public void setOf01(String of01) {
		this.of01 = of01;
	}

	public String getOf02() {
		return this.of02;
	}

	public void setOf02(String of02) {
		this.of02 = of02;
	}

	public String getOf03() {
		return this.of03;
	}

	public void setOf03(String of03) {
		this.of03 = of03;
	}

	public String getOf04() {
		return this.of04;
	}

	public void setOf04(String of04) {
		this.of04 = of04;
	}

	public String getOf05() {
		return this.of05;
	}

	public void setOf05(String of05) {
		this.of05 = of05;
	}

	public String getOf10() {
		return this.of10;
	}

	public void setOf10(String of10) {
		this.of10 = of10;
	}

	public String getOf11() {
		return this.of11;
	}

	public void setOf11(String of11) {
		this.of11 = of11;
	}

	public String getOf13() {
		return this.of13;
	}

	public void setOf13(String of13) {
		this.of13 = of13;
	}

	public String getOf40() {
		return this.of40;
	}

	public void setOf40(String of40) {
		this.of40 = of40;
	}

	public String getOf46() {
		return this.of46;
	}

	public void setOf46(String of46) {
		this.of46 = of46;
	}

	public String getOf47() {
		return this.of47;
	}

	public void setOf47(String of47) {
		this.of47 = of47;
	}

	public String getOf48() {
		return this.of48;
	}

	public void setOf48(String of48) {
		this.of48 = of48;
	}

	public String getOf49() {
		return this.of49;
	}

	public void setOf49(String of49) {
		this.of49 = of49;
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getSf00() {
		return this.sf00;
	}

	public void setSf00(String sf00) {
		this.sf00 = sf00;
	}

	public String getSf01() {
		return this.sf01;
	}

	public void setSf01(String sf01) {
		this.sf01 = sf01;
	}

	public String getSf02() {
		return this.sf02;
	}

	public void setSf02(String sf02) {
		this.sf02 = sf02;
	}

	public String getSf03() {
		return this.sf03;
	}

	public void setSf03(String sf03) {
		this.sf03 = sf03;
	}

	public String getSf04() {
		return this.sf04;
	}

	public void setSf04(String sf04) {
		this.sf04 = sf04;
	}

	public String getSf05() {
		return this.sf05;
	}

	public void setSf05(String sf05) {
		this.sf05 = sf05;
	}

	public String getSf06() {
		return this.sf06;
	}

	public void setSf06(String sf06) {
		this.sf06 = sf06;
	}

	public String getSf93() {
		return this.sf93;
	}

	public void setSf93(String sf93) {
		this.sf93 = sf93;
	}

	public String getSf95() {
		return this.sf95;
	}

	public void setSf95(String sf95) {
		this.sf95 = sf95;
	}

	public String getSf96() {
		return this.sf96;
	}

	public void setSf96(String sf96) {
		this.sf96 = sf96;
	}

	public String getSf97() {
		return this.sf97;
	}

	public void setSf97(String sf97) {
		this.sf97 = sf97;
	}

	public String getSf98() {
		return this.sf98;
	}

	public void setSf98(String sf98) {
		this.sf98 = sf98;
	}

	public String getSf99() {
		return this.sf99;
	}

	public void setSf99(String sf99) {
		this.sf99 = sf99;
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

}