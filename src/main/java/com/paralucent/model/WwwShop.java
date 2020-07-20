package com.paralucent.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the www_shop database table.
 * 
 */
@Entity
@Table(name="www_shop")
@NamedQuery(name="WwwShop.findAll", query="SELECT w FROM WwwShop w")
public class WwwShop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int boot;

	private String f00;

	private String f01;

	private String f02;

	private String f03;

	private String f04;

	private int f05;

	private int f06;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date f18;

	@Temporal(TemporalType.TIMESTAMP)
	private Date f19;

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

	private String f50;

	private String f51;

	private String f52;

	private String f53;

	private String f54;

	private String f55;

	private String f56;

	private String f57;

	private String f58;

	private String f59;

	private String f60;

	private String f61;

	private String f62;

	private String f63;

	private String f64;

	private String f65;

	private String f66;

	private String f67;

	private String f68;

	private String f69;

	private String f70;

	private String f71;

	private String f72;

	private String f73;

	private String f74;

	private String f75;

	private String f76;

	private String f77;

	private String f78;

	private String f79;

	private String f80;

	private String f81;

	private String f82;

	private String f83;

	private String f84;

	private String f85;

	private String f86;

	private String f87;

	private String f88;

	private String f89;

	private String f90;

	private String f91;

	private String f92;

	private String f93;

	private String f94;

	private int f95;

	private int f96;

	private int f97;

	private String f98;

	private String f99;

	public WwwShop() {
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

	public int getF05() {
		return this.f05;
	}

	public void setF05(int f05) {
		this.f05 = f05;
	}

	public int getF06() {
		return this.f06;
	}

	public void setF06(int f06) {
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

	public Date getF18() {
		return this.f18;
	}

	public void setF18(Date f18) {
		this.f18 = f18;
	}

	public Date getF19() {
		return this.f19;
	}

	public void setF19(Date f19) {
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

	public String getF50() {
		return this.f50;
	}

	public void setF50(String f50) {
		this.f50 = f50;
	}

	public String getF51() {
		return this.f51;
	}

	public void setF51(String f51) {
		this.f51 = f51;
	}

	public String getF52() {
		return this.f52;
	}

	public void setF52(String f52) {
		this.f52 = f52;
	}

	public String getF53() {
		return this.f53;
	}

	public void setF53(String f53) {
		this.f53 = f53;
	}

	public String getF54() {
		return this.f54;
	}

	public void setF54(String f54) {
		this.f54 = f54;
	}

	public String getF55() {
		return this.f55;
	}

	public void setF55(String f55) {
		this.f55 = f55;
	}

	public String getF56() {
		return this.f56;
	}

	public void setF56(String f56) {
		this.f56 = f56;
	}

	public String getF57() {
		return this.f57;
	}

	public void setF57(String f57) {
		this.f57 = f57;
	}

	public String getF58() {
		return this.f58;
	}

	public void setF58(String f58) {
		this.f58 = f58;
	}

	public String getF59() {
		return this.f59;
	}

	public void setF59(String f59) {
		this.f59 = f59;
	}

	public String getF60() {
		return this.f60;
	}

	public void setF60(String f60) {
		this.f60 = f60;
	}

	public String getF61() {
		return this.f61;
	}

	public void setF61(String f61) {
		this.f61 = f61;
	}

	public String getF62() {
		return this.f62;
	}

	public void setF62(String f62) {
		this.f62 = f62;
	}

	public String getF63() {
		return this.f63;
	}

	public void setF63(String f63) {
		this.f63 = f63;
	}

	public String getF64() {
		return this.f64;
	}

	public void setF64(String f64) {
		this.f64 = f64;
	}

	public String getF65() {
		return this.f65;
	}

	public void setF65(String f65) {
		this.f65 = f65;
	}

	public String getF66() {
		return this.f66;
	}

	public void setF66(String f66) {
		this.f66 = f66;
	}

	public String getF67() {
		return this.f67;
	}

	public void setF67(String f67) {
		this.f67 = f67;
	}

	public String getF68() {
		return this.f68;
	}

	public void setF68(String f68) {
		this.f68 = f68;
	}

	public String getF69() {
		return this.f69;
	}

	public void setF69(String f69) {
		this.f69 = f69;
	}

	public String getF70() {
		return this.f70;
	}

	public void setF70(String f70) {
		this.f70 = f70;
	}

	public String getF71() {
		return this.f71;
	}

	public void setF71(String f71) {
		this.f71 = f71;
	}

	public String getF72() {
		return this.f72;
	}

	public void setF72(String f72) {
		this.f72 = f72;
	}

	public String getF73() {
		return this.f73;
	}

	public void setF73(String f73) {
		this.f73 = f73;
	}

	public String getF74() {
		return this.f74;
	}

	public void setF74(String f74) {
		this.f74 = f74;
	}

	public String getF75() {
		return this.f75;
	}

	public void setF75(String f75) {
		this.f75 = f75;
	}

	public String getF76() {
		return this.f76;
	}

	public void setF76(String f76) {
		this.f76 = f76;
	}

	public String getF77() {
		return this.f77;
	}

	public void setF77(String f77) {
		this.f77 = f77;
	}

	public String getF78() {
		return this.f78;
	}

	public void setF78(String f78) {
		this.f78 = f78;
	}

	public String getF79() {
		return this.f79;
	}

	public void setF79(String f79) {
		this.f79 = f79;
	}

	public String getF80() {
		return this.f80;
	}

	public void setF80(String f80) {
		this.f80 = f80;
	}

	public String getF81() {
		return this.f81;
	}

	public void setF81(String f81) {
		this.f81 = f81;
	}

	public String getF82() {
		return this.f82;
	}

	public void setF82(String f82) {
		this.f82 = f82;
	}

	public String getF83() {
		return this.f83;
	}

	public void setF83(String f83) {
		this.f83 = f83;
	}

	public String getF84() {
		return this.f84;
	}

	public void setF84(String f84) {
		this.f84 = f84;
	}

	public String getF85() {
		return this.f85;
	}

	public void setF85(String f85) {
		this.f85 = f85;
	}

	public String getF86() {
		return this.f86;
	}

	public void setF86(String f86) {
		this.f86 = f86;
	}

	public String getF87() {
		return this.f87;
	}

	public void setF87(String f87) {
		this.f87 = f87;
	}

	public String getF88() {
		return this.f88;
	}

	public void setF88(String f88) {
		this.f88 = f88;
	}

	public String getF89() {
		return this.f89;
	}

	public void setF89(String f89) {
		this.f89 = f89;
	}

	public String getF90() {
		return this.f90;
	}

	public void setF90(String f90) {
		this.f90 = f90;
	}

	public String getF91() {
		return this.f91;
	}

	public void setF91(String f91) {
		this.f91 = f91;
	}

	public String getF92() {
		return this.f92;
	}

	public void setF92(String f92) {
		this.f92 = f92;
	}

	public String getF93() {
		return this.f93;
	}

	public void setF93(String f93) {
		this.f93 = f93;
	}

	public String getF94() {
		return this.f94;
	}

	public void setF94(String f94) {
		this.f94 = f94;
	}

	public int getF95() {
		return this.f95;
	}

	public void setF95(int f95) {
		this.f95 = f95;
	}

	public int getF96() {
		return this.f96;
	}

	public void setF96(int f96) {
		this.f96 = f96;
	}

	public int getF97() {
		return this.f97;
	}

	public void setF97(int f97) {
		this.f97 = f97;
	}

	public String getF98() {
		return this.f98;
	}

	public void setF98(String f98) {
		this.f98 = f98;
	}

	public String getF99() {
		return this.f99;
	}

	public void setF99(String f99) {
		this.f99 = f99;
	}

}