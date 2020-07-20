package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the www_order database table.
 * 
 */
@Entity
@Table(name="www_order")
@NamedQuery(name="WwwOrder.findAll", query="SELECT w FROM WwwOrder w")
public class WwwOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int boot;

	private String f00;

	private String f01;

	private String f02;

	private String f03;

	private String f04;

	private String f05;

	private String f06;

	private String f07;

	private String f08;

	private String f09;

	private String f10;

	private String f11;

	private String f12;

	private String f13;

	private String f14;

	private String f15;

	private String f16;

	private String f17;

	private String f18;

	private String f19;

	private String f20;

	private String f21;

	private String f22;

	private String f23;

	private String f24;

	private String f25;

	private String f26;

	private String f27;

	private String f28;

	private String f29;

	private String f30;

	private String f31;

	private String f32;

	private String f33;

	private String f34;

	private String f35;

	private String f36;

	private String f37;

	private String f38;

	private String f39;

	private String f40;

	private String f41;

	private String f42;

	private String f43;

	private String f44;

	private String f45;

	private String f46;

	private String f47;

	private String f48;

	private String f49;

	public WwwOrder() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoot() {
		return this.boot;
	}

	public void setBoot(int boot) {
		this.boot = boot;
	}

	public String getF00() {
		return this.f00;
	}

	public void setF00(String f00) {
		this.f00 = f00;
	}

	public String getF01() {
		return this.f01;
	}

	public void setF01(String f01) {
		this.f01 = f01;
	}

	public String getF02() {
		return this.f02;
	}

	public void setF02(String f02) {
		this.f02 = f02;
	}

	public String getF03() {
		return this.f03;
	}

	public void setF03(String f03) {
		this.f03 = f03;
	}

	public String getF04() {
		return this.f04;
	}

	public void setF04(String f04) {
		this.f04 = f04;
	}

	public String getF05() {
		return this.f05;
	}

	public void setF05(String f05) {
		this.f05 = f05;
	}

	public String getF06() {
		return this.f06;
	}

	public void setF06(String f06) {
		this.f06 = f06;
	}

	public String getF07() {
		return this.f07;
	}

	public void setF07(String f07) {
		this.f07 = f07;
	}

	public String getF08() {
		return this.f08;
	}

	public void setF08(String f08) {
		this.f08 = f08;
	}

	public String getF09() {
		return this.f09;
	}

	public void setF09(String f09) {
		this.f09 = f09;
	}

	public String getF10() {
		return this.f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}

	public String getF11() {
		return this.f11;
	}

	public void setF11(String f11) {
		this.f11 = f11;
	}

	public String getF12() {
		return this.f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
	}

	public String getF13() {
		return this.f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
	}

	public String getF14() {
		return this.f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
	}

	public String getF15() {
		return this.f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
	}

	public String getF16() {
		return this.f16;
	}

	public void setF16(String f16) {
		this.f16 = f16;
	}

	public String getF17() {
		return this.f17;
	}

	public void setF17(String f17) {
		this.f17 = f17;
	}

	public String getF18() {
		return this.f18;
	}

	public void setF18(String f18) {
		this.f18 = f18;
	}

	public String getF19() {
		return this.f19;
	}

	public void setF19(String f19) {
		this.f19 = f19;
	}

	public String getF20() {
		return this.f20;
	}

	public void setF20(String f20) {
		this.f20 = f20;
	}

	public String getF21() {
		return this.f21;
	}

	public void setF21(String f21) {
		this.f21 = f21;
	}

	public String getF22() {
		return this.f22;
	}

	public void setF22(String f22) {
		this.f22 = f22;
	}

	public String getF23() {
		return this.f23;
	}

	public void setF23(String f23) {
		this.f23 = f23;
	}

	public String getF24() {
		return this.f24;
	}

	public void setF24(String f24) {
		this.f24 = f24;
	}

	public String getF25() {
		return this.f25;
	}

	public void setF25(String f25) {
		this.f25 = f25;
	}

	public String getF26() {
		return this.f26;
	}

	public void setF26(String f26) {
		this.f26 = f26;
	}

	public String getF27() {
		return this.f27;
	}

	public void setF27(String f27) {
		this.f27 = f27;
	}

	public String getF28() {
		return this.f28;
	}

	public void setF28(String f28) {
		this.f28 = f28;
	}

	public String getF29() {
		return this.f29;
	}

	public void setF29(String f29) {
		this.f29 = f29;
	}

	public String getF30() {
		return this.f30;
	}

	public void setF30(String f30) {
		this.f30 = f30;
	}

	public String getF31() {
		return this.f31;
	}

	public void setF31(String f31) {
		this.f31 = f31;
	}

	public String getF32() {
		return this.f32;
	}

	public void setF32(String f32) {
		this.f32 = f32;
	}

	public String getF33() {
		return this.f33;
	}

	public void setF33(String f33) {
		this.f33 = f33;
	}

	public String getF34() {
		return this.f34;
	}

	public void setF34(String f34) {
		this.f34 = f34;
	}

	public String getF35() {
		return this.f35;
	}

	public void setF35(String f35) {
		this.f35 = f35;
	}

	public String getF36() {
		return this.f36;
	}

	public void setF36(String f36) {
		this.f36 = f36;
	}

	public String getF37() {
		return this.f37;
	}

	public void setF37(String f37) {
		this.f37 = f37;
	}

	public String getF38() {
		return this.f38;
	}

	public void setF38(String f38) {
		this.f38 = f38;
	}

	public String getF39() {
		return this.f39;
	}

	public void setF39(String f39) {
		this.f39 = f39;
	}

	public String getF40() {
		return this.f40;
	}

	public void setF40(String f40) {
		this.f40 = f40;
	}

	public String getF41() {
		return this.f41;
	}

	public void setF41(String f41) {
		this.f41 = f41;
	}

	public String getF42() {
		return this.f42;
	}

	public void setF42(String f42) {
		this.f42 = f42;
	}

	public String getF43() {
		return this.f43;
	}

	public void setF43(String f43) {
		this.f43 = f43;
	}

	public String getF44() {
		return this.f44;
	}

	public void setF44(String f44) {
		this.f44 = f44;
	}

	public String getF45() {
		return this.f45;
	}

	public void setF45(String f45) {
		this.f45 = f45;
	}

	public String getF46() {
		return this.f46;
	}

	public void setF46(String f46) {
		this.f46 = f46;
	}

	public String getF47() {
		return this.f47;
	}

	public void setF47(String f47) {
		this.f47 = f47;
	}

	public String getF48() {
		return this.f48;
	}

	public void setF48(String f48) {
		this.f48 = f48;
	}

	public String getF49() {
		return this.f49;
	}

	public void setF49(String f49) {
		this.f49 = f49;
	}

}