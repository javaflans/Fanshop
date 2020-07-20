package com.paralucent.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paralucent.model.KolData;
import com.paralucent.model.KolDatasChecked;
import com.paralucent.model.KolShare;
import com.paralucent.model.Kolprofitexpdata;
import com.paralucent.model.Level;
import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.Role;
import com.paralucent.model.RoleMember;
import com.paralucent.model.RoleMemberPK;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;
import com.paralucent.model.WwwOrder;
import com.paralucent.model.WwwShop;
import com.paralucent.services.DataService;
import com.paralucent.services.VerifyService;
import com.paralucent.utils.DateUtils;
import com.paralucent.utils.EncrypSHA256;
import com.paralucent.viewmodel.kolDatasCheckedBO;

@Controller
public class StandardController
	extends
	BaseController {

    private final static String DOMAIN_URL = "http://www.fanshopping.com.tw:8080/fanshop";
    // private final static String DOMAIN_URL = "http://localhost:8080/fanshop";
    // private final static String DOMAIN_URL = "http://www.paralucent.com.tw:8081/fanshop";
    @Autowired
    private DataService dataService;
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private HttpSession session;

    public StandardController() {
	super();
	genLogger();
    }

    /**
     * 共用功能
     * 
     * @param member
     * @return
     */
    /**
     * 登入 view: login
     */
    @RequestMapping("login")
    public ModelAndView getLogin(@ModelAttribute Member member) {
	clearSession(req,session);
	ModelAndView view = new ModelAndView("login");
	view.addObject("member", new Member());
	session.setMaxInactiveInterval(300);
	return view;
    }

    /**
     * 重新導向登入(登出後回到登入頁面用)
     */
    @RequestMapping("redirectLogin")
    public ModelAndView getRedirectLogin(@ModelAttribute Member member) {
	ModelAndView view = new ModelAndView("login");
	return view;
    }

    /**
     * 登入審核
     */
    @RequestMapping(value = "loginAction", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("member") Member member) {
	VerifyAccount result = validateAccount(req,member);
	logAccount(result, LOGIN_TYPE);
	log.info("message: " + result.getVerifyed());
	if (!result.getVerifyed()) {
	    session.setAttribute("loginMessage", "帳號密碼錯誤, 請重新登入");
	    session.setAttribute("loginAccount", null);
	    log.info("Exit loginAction");
	} else {
	    if (StringUtils.equals(result.getMember().getMemberStatus().getStuType(), "reject")
		    && StringUtils.equals(result.getMember().getMemberStatus().getUsageTable(), "member")) {
		session.setAttribute("loginMessage", "此帳號已被系統封鎖, 禁止登入");
		session.setAttribute("loginAccount", null);
		log.info("Exit loginAction");
	    } else {
		Member sessionMember = result.getMember();
		Role sessionRole = dataService.searchRoleByMember(sessionMember.getId());
		List<Menus> menuList = prepareMenu(sessionRole, sessionMember);
		result.getMember().setUserLocalName(firstUplocate(sessionMember.getUserLocalName()));
		session.setAttribute("userUuid", result.getMember().getUserUuid());
		session.setAttribute("userMaxProdCount", result.getMember().getUserProductCount());
		session.setAttribute("loginAccount", result.getMember());
		session.setAttribute("loginData", sessionRole.getRoleName().equals("KOL")?dataService.searchKOLCheckedByUUID(result.getMember().getUserUuid()):result.getMember());
		session.setAttribute("loginRole", sessionRole);
		session.setAttribute("header_type", "kendo");
		session.setAttribute("menus", menuList);
		log.info("Exit loginAction");
	    }
	}
	return returnView(req,session,"redirect:/overview");
    }

    /**
     * 註冊KOL ID Email 驗證信發送
     */
    @RequestMapping(value = "registFormSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> registFormSubmit(@RequestBody ArrayList<Member> members) {
	Map<String, Object> result = new HashMap<String, Object>();
	if (members != null && members.size() > 0) {
	    Member member = members.get(0);
	    boolean checkDuplicate = verifyService.checkDuplicateMember(member);
	    log.info("check Result: " + checkDuplicate);
	    if (!checkDuplicate) {
		result.put("code", 204);
		result.put("message", "此Email已經正在使用,請重新登入或使用其他Email註冊!");
		result.put("status", "warning");
	    } else {
		Status unvalidatedStatus = verifyService.searchStatus("unvalidated", "member");
		Status uncheckedStatus = verifyService.searchStatus("unchecked", "member");
		Status roleEnabledStatus = verifyService.searchStatus("enabled", "role");
		Role kolRole = dataService.searchRoleByRoleName("KOL");
		Level defaultLevel = verifyService.searchLevel("Lv1");
		if (unvalidatedStatus != null) {
		    member.setUserUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		    member.setUserProductCount(20);
		    member.setLevel(defaultLevel);
		    member.setMemberStatus(uncheckedStatus);
		    member.setMailStatus(unvalidatedStatus);
		    member.setUserCreated("Regist System");
		    member.setUserDateCreated(new Date());
		    // SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
		    // log.info("Member: " + member.getUserMail());
		    // log.info("Member: " + member.getUserName());
		    // log.info("Member: " + member.getUserLocalName());
		    // log.info("Member: " + member.getUserPassword());
		    // log.info("Member: " + member.getUserPhone());
		    // log.info("Member: " + member.getUserCity());
		    // log.info("Member: " + member.getUserArea());
		    // log.info("Member: " + member.getUserAddress());
		    // log.info("Member: " + member.getZipCode());
		    // log.info("Member: " +
		    // sdf.format(member.getUserBirthday()));
		    // log.info("Member: " + member.getStatus().getStuType() + " / " + member.getStatus().getStuDesc());
		}
		verifyService.insertUpdateMember(member);
		member = dataService.searchMember(member);
		RoleMember roleMember = new RoleMember();
		RoleMemberPK pk = new RoleMemberPK();
		pk.setMemberID(member.getId());
		roleMember.setId(pk);
		roleMember.setMember(member);
		roleMember.setRole(kolRole);
		roleMember.setStatus(roleEnabledStatus);
		roleMember.setUserCreated("system");
		roleMember.setUserDateCreated(new Date());
		verifyService.insertUpdateRoleMemeber(roleMember);
		try {
		    if (sendVerifyMail(member, "分享達人 ID 帳戶驗證信", prepareRegistMailContent(member))) {
			result.put("code", 200);
			result.put("title", "帳號註冊確認");
			result.put("message", "恭喜你, 註冊成功, 請到您的電子郵件收取驗證郵件, 並依照指示完成驗證");
			result.put("status", "Successfuly");
		    }
		} catch (UnsupportedEncodingException | MessagingException e) {
		    result.put("code", 204);
		    result.put("message", "因特殊原因造成例外, 請與服務人員聯繫");
		    result.put("status", "error");
		}
	    }
	} else {
	    result.put("code", 204);
	    result.put("message", "輸入資料錯誤,請重新確認");
	    result.put("status", "error");
	}
	return result;
    }

    /**
     * KOL ID 忘記密碼 Email 發送
     */
    @RequestMapping(value = "forgetFormSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> forgetFormSubmit(@RequestBody ArrayList<Member> members) {
	Map<String, Object> result = new HashMap<String, Object>();
	if (members != null && members.size() > 0) {
	    Member member = members.get(0);
	    List<Member> dataResults = verifyService.queryDuplicateMember(member);
	    if (dataResults != null && dataResults.size() > 0) {
		Member data = dataResults.get(0);
		if (!data.getMailStatus().getStuType().equals("unvalidated")) {
		    try {
			if (sendVerifyMail(data, "分享達人 ID 密碼重設要求", prepareForgetMailContent(data))) {
			    result.put("code", 200);
			    result.put("title", "密碼重設確認");
			    result.put("message", "感謝您，我們已將包含如何重設密碼相關指示的電子郵件傳送至 " + member.getUserMail() + "。");
			    result.put("status", "Successfuly");
			}
		    } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		} else {
		    result.put("code", 204);
		    result.put("message", "該帳號Email連結尚未驗證! 請重新回到Email驗證");
		    result.put("status", "error");
		}
	    } else {
		result.put("code", 204);
		result.put("message", "該帳號不存在, 請重新確認");
		result.put("status", "error");
	    }
	} else {
	    result.put("code", 204);
	    result.put("message", "輸入資料錯誤,請重新確認");
	    result.put("status", "error");
	}
	return result;
    }

    /**
     * KOL 密碼重設
     */
    @RequestMapping(value = "resetPasswordSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> resetPasswordSubmit(@RequestBody ArrayList<Member> members) {
	Map<String, Object> result = new HashMap<String, Object>();
	if (members != null && members.size() > 0) {
	    Member member = members.get(0);
	    log.info(member.getUserPassword());
	    log.info(member.getUserMail());
	    Member data = verifyService.checkMemberVerifyed(member.getUserMail());
	    if (data != null && StringUtils.isNotBlank(data.getUserName())) {
		data.setUserPassword(member.getUserPassword());
		data.setUserLastDateModify(new Date());
		data.setUserLastModify("Forget PWD System");
		verifyService.insertUpdateMember(data);
		result.put("code", 200);
		result.put("title", "密碼重設");
		result.put("message", "密碼重設成功! 請使用新的密碼重新登入!");
		result.put("status", "error");
	    }
	}
	return result;
    }

    /**
     * 發送 Email 驗證信
     */
    private boolean sendVerifyMail(Member member, String subject, StringBuffer htmlContent) throws MessagingException, UnsupportedEncodingException {
	Message message = new MimeMessage(getMailSession());
	message.setFrom(new InternetAddress("paralucent.service@gmail.com", "Fanshopping Support"));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(member.getUserMail()));
	message.setSubject(subject);
	message.setContent(htmlContent.toString(), "text/html; charset=utf-8");
	Transport.send((MimeMessage) message);
	return Boolean.TRUE;
    }

    /**
     * 本地共用功能清單
     */
    /**
     * 準備 Menu 目錄
     */
    private List<Menus> prepareMenu(Role role, Member member) {
	List<Menus> menus = new ArrayList<Menus>(0);
	menus = dataService.searchMenus(role, member);
	return menus;
    }

    /**
     * 產生註冊驗證信內容
     * 
     * @param member
     * @return
     */
    private StringBuffer prepareRegistMailContent(Member member) {
	StringBuffer htmlContent = new StringBuffer("");
	htmlContent.append("<html style='height: 100%;' >");
	htmlContent.append("	<head>");
	htmlContent.append("		<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
	htmlContent.append("	</head>");
	htmlContent.append("	<body style='height:100%; padding:0; margin: 0; '>");
	htmlContent.append(
		"		<div style='width:100%; height: 100%; margin: 0 auto; -webkit-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); -moz-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75);'>");
	htmlContent.append("			<div style='width:100%; height:30px; background:#ffbe23; '>");
	htmlContent.append(
		"				<img style='height: 50px;' src='http://www.fanshopping.com.tw//uploads/logo/home/logo.png' alt='logo' />");
	htmlContent.append("			</div>");
	htmlContent.append("			<br/><br/>");
	htmlContent.append("			<div style='font-family: Microsoft JhengHei; width: 290px; font-size: 25px; margin: 0 auto;'>");
	htmlContent.append("				<span>確認您的</span>  <span style='color: #ffbe23;'>分享達人 ID</span>");
	htmlContent.append("			</div><br/><br/><br/>");
	htmlContent.append("			<div style='margin: 0;'>");
	htmlContent.append("				<div style='word-break:break-all; font-size: 13px; width: 95%; margin: 0 auto;'>");
	htmlContent.append("					" + member.getUserLocalName() + "  先生/女士 您好，<br/><br/><br/>");
	htmlContent.append("					感謝您註冊 分享達人 ID。您還差一步就可以完成設定。為了安全起見並確定您是真人，請 <a href='" + DOMAIN_URL
		+ "/verifyApi/EmailVerify?koluuid=" + member.getUserUuid() + "&kolname=" + member.getUserName() + "&kolmail=" + member.getUserMail()
		+ "'>按一下這裡</a> 以確認您的帳戶。<br/><br/>");
	htmlContent.append("					您的 分享達人 ID 為：" + member.getUserMail() + "<br/><br/>");
	htmlContent.append("					如果上述連結無法運作，請將下方的 URL 複製並貼到新的瀏覽器視窗中，以完成設定。<br/><br/><br/><br/><br/>");
	htmlContent.append("					<a href='" + DOMAIN_URL + "/verifyApi/EmailVerify?koluuid=" + member.getUserUuid()
		+ "&kolname=" + member.getUserName() + "&kolmail=" + member.getUserMail() + "'>");
	htmlContent.append("						" + DOMAIN_URL + "/verifyApi/EmailVerify?koluuid=" + member.getUserUuid()
		+ "&kolname=" + member.getUserName() + "&kolmail=" + member.getUserMail());
	htmlContent.append("					</a><br/><br/><br/><br/><br/><br/><br/><br/>");
	htmlContent.append("					感謝您註冊使用 分享達人 服務。");
	htmlContent.append("					<br/><br/>");
	htmlContent.append("					開團樂 KOL ID 團隊 敬上");
	htmlContent.append("				</div>");
	htmlContent.append("			</div>");
	htmlContent.append(
		"			<div style='position:fixed; bottom: 0px; width: 100%; padding:0; height:23px; background:#ffbe23; margin: 0 auto;'>");
	htmlContent.append("				<footer role='contentinfo'>");
	htmlContent.append("			        <div style='color: #fff'>");
	htmlContent.append("			            <ul style='padding-top: 3px; padding-left: 15px;'>");
	htmlContent.append("			                <li style='list-style-type:none; font-weight: bolder; letter-spacing: 2px;'>");
	htmlContent.append("			                	丞易國際有限公司&copy; 2017");
	htmlContent.append("			                </li>");
	htmlContent.append("			            </ul>");
	htmlContent.append("			        </div>");
	htmlContent.append("			    </footer>");
	htmlContent.append("			</div>");
	htmlContent.append("		</div>");
	htmlContent.append("	</body>");
	htmlContent.append("</html>");
	return htmlContent;
    }

    /**
     * 產生忘記密碼驗證信內容
     * 
     * @param member
     * @return
     */
    private StringBuffer prepareForgetMailContent(Member member) {
	EncrypSHA256 sha256 = new EncrypSHA256();
	SimpleDateFormat sdf = new SimpleDateFormat("SSSssmm'T'HHddMMyyyy");
	String dd = sdf.format(new Date());
	String shaCode = sha256.genEncrypSHA256(member.getUserUuid() + dd);
	StringBuffer htmlContent = new StringBuffer("");
	htmlContent.append("<html style='height: 100%;' >");
	htmlContent.append("	<body style='height:100%; padding:0; margin: 0; '>");
	htmlContent.append(
		"		<div style='width:100%; height: 100%; margin: 0 auto; -webkit-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); -moz-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75);'>");
	htmlContent.append("			<div style='width:100%; height:30px; background:#ffbe23; '>");
	htmlContent.append(
		"				<img style='height: 50px;' src='http://www.fanshopping.com.tw//uploads/logo/home/logo.png' alt='logo' />");
	htmlContent.append("			</div>");
	htmlContent.append("			<br/><br/>");
	htmlContent.append("			<div style='font-family: Microsoft JhengHei; width: 290px; font-size: 25px; margin: 0 auto;'>");
	htmlContent.append("				<span>忘記您的</span>  <span style='color: #ffbe23;'>分享達人 密碼?</span>");
	htmlContent.append("			</div><br/><br/><br/>");
	htmlContent.append("			<div style='margin: 0;'>");
	htmlContent.append("				<div style='word-break:break-all; font-size: 13px; width: 95%; margin: 0 auto;'>");
	htmlContent.append("					我們最近收到您的 分享達人 ID帳戶密碼重設要求：" + member.getUserMail() + "<br/><br/>");
	htmlContent.append("					請點擊下面的連結完成此需求。<br/><br/><br/><br/><br/><br/><br/>");
	htmlContent.append("					<a href='" + DOMAIN_URL + "/verifyApi/ForgetPassword?kolsha=" + shaCode + "&koldd="
		+ dd + "&kolmail=" + member.getUserMail() + "'>");
	htmlContent.append("						" + DOMAIN_URL + "/verifyApi/ForgetPassword?kolsha=" + shaCode + "&koldd="
		+ dd + "&kolmail=" + member.getUserMail());
	htmlContent.append("					</a><br/><br/><br/><br/><br/><br/>");
	htmlContent.append("					<div style='background:#eee;width: 98%; padding: 1%;'>");
	htmlContent.append("						<p>");
	htmlContent.append("							如果您在重設密碼過程中遇到任何問題，可以複製連結文字，並直接貼到網絡瀏覽器的 URL 欄中。");
	htmlContent.append("						</p>");
	htmlContent.append("						<p>");
	htmlContent.append("							如果您並未提交 密碼重設 需求，請忽略此電子郵件。");
	htmlContent.append("						</p>");
	htmlContent.append("					</div>");
	htmlContent.append("					<br/><br/><br/><br/><br/><br/>");
	htmlContent.append("					謝謝。<br/>");
	htmlContent.append("					開團樂 KOL ID 團隊 敬上<br/>");
	htmlContent.append("					請勿直接回覆此電子郵件，生成此電子郵件的郵箱將不會進行回覆。");
	htmlContent.append("				</div>");
	htmlContent.append("			</div>");
	htmlContent.append(
		"			<div style='position:fixed; bottom: 0px; width: 100%; padding:0; height:23px; background:#ffbe23; margin: 0 auto;'>");
	htmlContent.append("				<footer role='contentinfo'>");
	htmlContent.append("			        <div style='color: #fff'>");
	htmlContent.append("			            <ul style='padding-top: 3px; padding-left: 15px;'>");
	htmlContent.append("			                <li style='list-style-type:none; font-weight: bolder; letter-spacing: 2px;'>");
	htmlContent.append("			                	丞易國際有限公司&copy; 2017");
	htmlContent.append("			                </li>");
	htmlContent.append("			            </ul>");
	htmlContent.append("			        </div>");
	htmlContent.append("			    </footer>");
	htmlContent.append("			</div>");
	htmlContent.append("		</div>");
	htmlContent.append("	</body>");
	htmlContent.append("</html>");
	return htmlContent;
    }

    /**
     * API
     */
    /**
     * Email 驗證
     */
    @RequestMapping(value = "verifyApi/EmailVerify", method = RequestMethod.GET)
    public ModelAndView verifyEmail() {
	log.info("Enter verifyEmail");
	String kolUuid = req.getParameter("koluuid");
	String kolName = req.getParameter("kolname");
	String kolmail = req.getParameter("kolmail");
	Member member = verifyService.checkMemberVerifyed(kolUuid, kolName, kolmail);
	if (member != null && member.getMailStatus().getStuType().equals("unvalidated")) {
	    Status validated = verifyService.searchStatus("validated", "member");
	    member.setMailStatus(validated);
	    member.setUserLastModify("Email Verify System");
	    member.setUserLastDateModify(new Date());
	    verifyService.insertUpdateMember(member);
	    session.setAttribute("code", 200);
	    session.setAttribute("title", "認證帳戶");
	    session.setAttribute("message", "Email 認證成功! 請重新登入, 並靜候團主開通 <span style='color: #ffbe23;'>分享達人</span> 任用權限!");
	    session.setAttribute("status", "error");
	} else if (member != null && member.getMailStatus().getStuDesc().equals("validated")) {
	    session.setAttribute("code", 204);
	    session.setAttribute("title", "使用中帳戶");
	    session.setAttribute("message", "Email 認證失敗! 該帳號可能已經啟用過了!");
	    session.setAttribute("status", "error");
	} else {
	    session.setAttribute("code", 204);
	    session.setAttribute("title", "失效帳戶");
	    session.setAttribute("message", "Email 認證失敗! 請與服務人員聯繫!");
	    session.setAttribute("status", "error");
	}
	return new ModelAndView("redirect:/redirectkolverfyresult");
    }

    /**
     * 重設密碼 Email 驗證
     */
    @RequestMapping(value = "verifyApi/ForgetPassword", method = RequestMethod.GET)
    public ModelAndView forgetPassword() {
	log.info("Enter forgetPassword");
	String kolsha = req.getParameter("kolsha");
	String koldd = req.getParameter("koldd");
	String kolmail = req.getParameter("kolmail");
	SimpleDateFormat sdf = new SimpleDateFormat("SSSssmm'T'HHddMMyyyy");
	Date before = new Date();
	try {
	    before = sdf.parse(koldd);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Date after = new Date();
	DateUtils dateUtils = new DateUtils();
	dateUtils.calDateDiff(after, before);
	if (dateUtils.getDay() < 1) {
	    Member member = verifyService.checkMemberVerifyed(kolmail);
	    if (member != null && member.getMailStatus().getStuType().equals("validated")) {
		EncrypSHA256 sha256 = new EncrypSHA256();
		String shaCode = sha256.genEncrypSHA256(member.getUserUuid() + koldd);
		log.info("mail sha: " + kolsha);
		log.info("qury sha: " + shaCode);
		if (kolsha.equals(shaCode)) {
		    session.setAttribute("code", 200);
		    session.setAttribute("title", "重設密碼");
		    session.setAttribute("member", member);
		    session.setAttribute("message", "請使用此表單來重設您的帳戶密碼");
		    session.setAttribute("status", "error");
		} else {
		    session.setAttribute("code", 204);
		    session.setAttribute("title", "例外錯誤");
		    session.setAttribute("message", "可能因帳戶資訊有誤! 無法進行驗證, 請與服務人員聯繫");
		    session.setAttribute("status", "error");
		}
	    } else if (member.getMailStatus().getStuType().equals("unvalidated")) {
		session.setAttribute("code", 204);
		session.setAttribute("title", "帳戶尚未驗證");
		session.setAttribute("message", "該帳號Email連結尚未驗證! 請重新回到Email驗證");
		session.setAttribute("status", "error");
	    } else {
		session.setAttribute("code", 204);
		session.setAttribute("title", "失效帳戶");
		session.setAttribute("message", "Email 連結認證失敗! 請與服務人員聯繫");
		session.setAttribute("status", "error");
	    }
	} else {
	    session.setAttribute("code", 204);
	    session.setAttribute("title", "失效驗證");
	    session.setAttribute("message", "Email 連結認證日期已經失效! 請重新申請密碼重設");
	    session.setAttribute("status", "error");
	}
	return new ModelAndView("redirect:/redirectForgetPassword");
    }

    /**
     * 設定可分享商品內容
     */
    @RequestMapping(value = "api/profitExpSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> profitExpSubmit(@RequestBody ArrayList<Kolprofitexpdata> kolprofitExpDatas) {
	log.info("enter profitExpSubmit");
	Map<String, Object> result = new HashMap<String, Object>();
	if (kolprofitExpDatas != null && kolprofitExpDatas.size() > 0) {
	     
	    if (kolprofitExpDatas != null && kolprofitExpDatas.size() > 0) {
		WwwOrder order = null;
		List<WwwOrder> orderList = new ArrayList<WwwOrder>(0);
		for (Kolprofitexpdata data : kolprofitExpDatas) {
		    order = dataService.searchWwwOrderByID(data.getOid(), data.getOf49());
		    log.info("分潤金額: " + data.getOf46());
		    order.setF46(data.getOf46());
		    order.setF47("2");
		    order.setF48(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		    orderList.add(order);
		}
		log.info("update size: " + orderList.size());
		boolean insertResult = dataService.insertUpdatekolprofitExpList(orderList);
		// boolean insertResult = Boolean.TRUE;
		if (insertResult) {
		    // reset prodShareConfigLista
		    result.put("code", 200);
		    result.put("message", orderList.size() + "筆分潤金額已核發");
		    result.put("status", "Successfuly");
		}
	    } else {
		log.info("Session is null!!!!!!!!!!");
	    }
	} else {
	    result.put("code", 204);
	    result.put("message", "輸入資料錯誤,請重新確認");
	    result.put("status", "error");
	}
	return result;
    }

    /**
     * 設定可分享商品內容
     */
    @RequestMapping(value = "api/prodShareConfigSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> prodShareConfigSubmit(@RequestBody ArrayList<WwwShop> shops) {
	log.info("enter prodShareConfigSubmit");
	Map<String, Object> result = new HashMap<String, Object>();
	if (shops != null && shops.size() > 0) {
	     
	    List<WwwShop> prodShareConfigList = (List<WwwShop>) session.getAttribute("prodShareConfigList");
	    if (prodShareConfigList != null && prodShareConfigList.size() > 0) {
		shops.sort((WwwShop o1, WwwShop o2) -> Integer.compare(o1.getId(), o2.getId()));
		prodShareConfigList.sort((WwwShop o1, WwwShop o2) -> Integer.compare(o1.getId(), o2.getId()));
		for (int i = shops.size() - 1; i >= 0; i--) {
		    WwwShop shop = shops.get(i);
		    WwwShop orgShop = prodShareConfigList.get(i);
		    if (shop.getId() == orgShop.getId() && StringUtils.equals(shop.getF99(), orgShop.getF99())
			    && StringUtils.equals(shop.getF98(), orgShop.getF98()) && shop.getF06() == orgShop.getF06()
			    && shop.getF95() == orgShop.getF95() && shop.getF96() == orgShop.getF96() && shop.getF97() == orgShop.getF97()) {
			shops.remove(i);
			prodShareConfigList.remove(i);
		    }
		}
		log.info("update size: " + shops.size());
		boolean insertResult = dataService.insertUpdateShopsList(shops);
		if (insertResult) {
		    // reset prodShareConfigLista
		    session.setAttribute("prodShareConfigList", dataService.searchItemListByAuth("chensi.creative@gmail.com"));
		    result.put("code", 200);
		    result.put("message", "分享商品設定成功");
		    result.put("status", "Successfuly");
		} else {
		    result.put("code", 204);
		    result.put("message", "分享商品設定錯誤,請重新確認");
		    result.put("status", "error");
		}
	    } else {
		log.info("Session is null!!!!!!!!!!");
	    }
	} else {
	    result.put("code", 204);
	    result.put("message", "輸入資料錯誤,請重新確認");
	    result.put("status", "error");
	}
	return result;
    }

    /**
     * 設定分享商品 - KOL
     */
    @RequestMapping(value = "api/prodShareKOLSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> prodKOLShareSubmit(@RequestBody ArrayList<WwwShop> shops) {
	Map<String, Object> result = new HashMap<String, Object>();
	Member user = (Member) session.getAttribute("loginAccount");
	List<KolShare> kolShares = dataService.searchKOLShareList(user.getId());
	List<KolShare> deleteKolShare = new ArrayList<KolShare>(0);
	List<KolShare> insertKolShare = new ArrayList<KolShare>(0);
	if (kolShares != null && kolShares.size() > 0) {
	    if (shops == null || shops.size() <= 0) {
		for (KolShare share : kolShares) {
		    deleteKolShare.add(share);
		}
	    } else {
		boolean delFlag = Boolean.TRUE;
		for (KolShare share : kolShares) {
		    delFlag = Boolean.TRUE;
		    for (WwwShop shop : shops) {
			if (share.getWwwShop().getId() == shop.getId()) {
			    delFlag = Boolean.FALSE;
			    break;
			}
		    }
		    if (delFlag) {
			deleteKolShare.add(share);
		    }
		}
		boolean insertFlag = Boolean.TRUE;
		for (WwwShop shop : shops) {
		    insertFlag = Boolean.TRUE;
		    for (KolShare share : kolShares) {
			if (share.getWwwShop().getId() == shop.getId()) {
			    insertFlag = Boolean.FALSE;
			    break;
			}
		    }
		    if (insertFlag) {
			KolShare kolShare = new KolShare();
			kolShare.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			kolShare.setWwwShop(shop);
			kolShare.setMember(user);
			kolShare.setUserCreated(user.getUserLocalName());
			kolShare.setUserDateCreated(new Date());
			insertKolShare.add(kolShare);
		    }
		}
	    }
	} else {
	    for (WwwShop shop : shops) {
		KolShare kolShare = new KolShare();
		kolShare.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		kolShare.setWwwShop(shop);
		kolShare.setMember(user);
		kolShare.setUserCreated(user.getUserLocalName());
		kolShare.setUserDateCreated(new Date());
		insertKolShare.add(kolShare);
	    }
	}
	boolean insertResult = dataService.insertUpdateSharesList(deleteKolShare, insertKolShare);
	if (insertKolShare != null) {
	    user.setBusinessTarget(user.getBusinessTarget() + insertKolShare.size());
	    user.setUserLastDateModify(new Date());
	    user.setUserLastModify("kol_share");
	    verifyService.insertUpdateMember(user);
	}
	// boolean insertResult = Boolean.TRUE;
	if (insertResult) {
	    result.put("code", 200);
	    result.put("message", "分享商品設定成功");
	    result.put("status", "Successfuly");
	} else {
	    result.put("code", 204);
	    result.put("message", "分享商品設定錯誤,請重新確認");
	    result.put("status", "error");
	}
	return result;
    }

    /**
     * 審核KOL任用權限
     */
    @RequestMapping(value = "api/kolDataSubmit", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> kolDataSubmit(@RequestBody ArrayList<KolData> kolDatas) {
	log.info("enter kolDataSubmit");
	Map<String, Object> result = new HashMap<String, Object>();
	Status checkedStatus = verifyService.searchStatus("checked", "member");
	Status rejectStatus = verifyService.searchStatus("reject", "member");
	if (kolDatas != null && kolDatas.size() > 0 && session.getAttribute("loginAccount") != null) {
	     
	    List<KolData> kolDataList = (List<KolData>) session.getAttribute("kolDataList");
	    if (kolDataList != null && kolDataList.size() > 0) {
		kolDatas.sort((KolData o1, KolData o2) -> Integer.compare(o1.getId(), o2.getId()));
		kolDataList.sort((KolData o1, KolData o2) -> Integer.compare(o1.getId(), o2.getId()));
		List<Member> updateData = new ArrayList<Member>(0);
		Member loginUser = (Member) session.getAttribute("loginAccount");
		for (int i = kolDatas.size() - 1; i >= 0; i--) {
		    KolData kol = kolDatas.get(i);
		    KolData orgKol = kolDataList.get(i);
		    if (kol.getId() == orgKol.getId() && kol.getMailStatus().equals(orgKol.getMailStatus())
			    && kol.getUserStatus().equals(orgKol.getUserAddress())) {
		    } else {
			Member member = dataService.searchMemberByID(kol.getId());
			if (kol.getUserStatus().equals("checked")) {
			    member.setMemberStatus(checkedStatus);
			} else if (kol.getUserStatus().equals("reject")) {
			    member.setMemberStatus(rejectStatus);
			}
			member.setUserLastDateModify(new Date());
			member.setUserLastModify(loginUser.getUserLocalName());
			updateData.add(member);
		    }
		}
		log.info("update size: " + kolDatas.size());
		boolean insertResult = dataService.insertUpdateKolData(updateData);
		// boolean insertResult = Boolean.TRUE;
		if (insertResult) {
		    // reset prodShareConfigLista
		    session.setAttribute("kolDataList", dataService.searchPreVerifyKOL());
		    result.put("code", 200);
		    result.put("message", "分享達人審核資料送交成功");
		    result.put("status", "Successfuly");
		} else {
		    result.put("code", 204);
		    result.put("message", "分享達人審核資料送交錯誤,請重新確認");
		    result.put("status", "error");
		}
	    } else {
		log.info("Session is null!!!!!!!!!!");
	    }
	} else {
	    result.put("code", 204);
	    result.put("message", "輸入資料錯誤,請重新確認");
	    result.put("status", "error");
	}
	return result;
    }

    /**
     * 設定KOL等級
     */
    @RequestMapping(value = "api/setKolDataLevel", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> setKolDataLevel(@RequestBody KolDatasChecked data) {
	Map<String, Object> result = new HashMap<String, Object>();
	if (data != null && session.getAttribute("loginAccount") != null) {
	    Member member = dataService.searchMemberByID(data.getUserUuid());
	    Member loginUser = (Member) session.getAttribute("loginAccount");
	    Level level = dataService.searchLevelByName(data.getLevel());
	    member.setLevel(level);
	    member.setUserLastModify(loginUser.getUserLocalName());
	    member.setUserLastDateModify(new Date());
	    verifyService.insertUpdateMember(member);
	    result.put("code", 200);
	    result.put("message", data.getUserLocalName() + " 等級設置成功");
	}
	return result;
    }

    /**
     * 設定KolData資料提供分潤資料讀取
     */
    @RequestMapping(value = "api/sendKolData", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> kolDataSubmit(@RequestBody KolData kolData) {
	log.info("enter kolDataSubmit");
	Map<String, Object> result = new HashMap<String, Object>();
	session.setAttribute("chooseKolData", kolData);
	return result;
    }

    /**
     * 讀取分潤資料
     */
    @RequestMapping(value = "api/getProfitExpData", method = RequestMethod.POST)
    public @ResponseBody List<Kolprofitexpdata> getProfitExpData() {
	 
	List<Kolprofitexpdata> kolprofitexpList = new ArrayList<Kolprofitexpdata>(0);
	KolData chooseKol = (KolData) session.getAttribute("chooseKolData");
	Member chooseUser = dataService.searchMemberByID(chooseKol.getUserUuid());
	if (chooseKol != null && StringUtils.isNoneBlank(chooseKol.getUserUuid())) {
	    kolprofitexpList = dataService.searchKolProfitExp(chooseKol.getUserUuid());
	    for (Kolprofitexpdata data : kolprofitexpList) {
		log.info("sf00: " + data.getSf00() + " of46: " + data.getOf46());
		if (StringUtils.isBlank(data.getOf46())) {
		    if (chooseUser.getLevel().getLevel().equals("Lv1")) {
			data.setOf46(new BigDecimal(StringUtils.isNotBlank(data.getSf95()) ? data.getSf95() : "0")
				.multiply(new BigDecimal(StringUtils.isNotBlank(data.getOf40()) ? data.getOf40() : "1")).toString());
		    } else if (chooseUser.getLevel().getLevel().equals("Lv2")) {
			data.setOf46(new BigDecimal(StringUtils.isNotBlank(data.getSf96()) ? data.getSf96() : "0")
				.multiply(new BigDecimal(StringUtils.isNotBlank(data.getOf40()) ? data.getOf40() : "1")).toString());
		    } else if (chooseUser.getLevel().getLevel().equals("Lv3")) {
			data.setOf46(new BigDecimal(StringUtils.isNotBlank(data.getSf97()) ? data.getSf97() : "0")
				.multiply(new BigDecimal(StringUtils.isNotBlank(data.getOf40()) ? data.getOf40() : "1")).toString());
		    }
		}
	    }
	    session.setAttribute("kolprofitexpList", kolprofitexpList);
	}
	return kolprofitexpList;
    }

    /**
     * 讀取KOL分享潤資料
     */
    @RequestMapping(value = "api/getProfitExpDataState", method = RequestMethod.POST)
    public @ResponseBody List<Kolprofitexpdata> getProfitExpDataState() {
	 
	List<Kolprofitexpdata> kolprofitexpList = new ArrayList<Kolprofitexpdata>(0);
	Member user = (Member) session.getAttribute("loginAccount");
	user = dataService.searchMemberByID(user.getUserUuid());
	if (user != null && StringUtils.isNoneBlank(user.getUserUuid())) {
	    kolprofitexpList = dataService.searchKolProfitExp(user.getUserUuid());
	    for (Kolprofitexpdata data : kolprofitexpList) {
		if (StringUtils.isBlank(data.getOf46())) {
		    if (user.getLevel().getLevel().equals("Lv1")) {
			data.setOf46(new BigDecimal(StringUtils.isNotBlank(data.getSf95()) ? data.getSf95() : "0")
				.multiply(new BigDecimal(StringUtils.isNotBlank(data.getOf40()) ? data.getOf40() : "1")).toString());
		    } else if (user.getLevel().getLevel().equals("Lv2")) {
			data.setOf46(new BigDecimal(StringUtils.isNotBlank(data.getSf96()) ? data.getSf96() : "0")
				.multiply(new BigDecimal(StringUtils.isNotBlank(data.getOf40()) ? data.getOf40() : "1")).toString());
		    } else if (user.getLevel().getLevel().equals("Lv3")) {
			data.setOf46(new BigDecimal(StringUtils.isNotBlank(data.getSf97()) ? data.getSf97() : "0")
				.multiply(new BigDecimal(StringUtils.isNotBlank(data.getOf40()) ? data.getOf40() : "1")).toString());
		    }
		}
	    }
	    session.setAttribute("kolprofitexpList", kolprofitexpList);
	}
	return kolprofitexpList;
    }

    /**
     * 讀取審核KOL個人資料,mailStatus=validated and userStatus=unchecked
     */
    @RequestMapping(value = "api/getUserDatas", method = RequestMethod.POST)
    public @ResponseBody List<KolData> getUserDatas() {
	 
	List<KolData> members = new ArrayList<KolData>(0);
	if (session != null && session.getAttribute("loginRole") != null) {
	    Role sessionRole = (Role) session.getAttribute("loginRole");
	    if (sessionRole.getRoleName().equals("Admin")) {
		members = dataService.searchPreVerifyKOL();
		session.setAttribute("kolDataList", members);
	    }
	}
	return members;
    }

    /**
     * 讀取 Level 主檔資料
     */
    @RequestMapping(value = "api/getLevelList", method = RequestMethod.POST)
    public @ResponseBody List<Level> getLevelList() {
	 
	List<Level> levels = dataService.searchLevelAll();
	session.setAttribute("LevelList", levels);
	return levels;
    }

    /**
     * 讀取審核KOL個人資料,mailStatus=validated and userStatus=checked
     */
    @RequestMapping(value = "api/getUserDatasChecked", method = RequestMethod.POST)
    public @ResponseBody List<kolDatasCheckedBO> getUserDatasChecked() {
	 
	List<KolDatasChecked> members = new ArrayList<KolDatasChecked>(0);
	List<kolDatasCheckedBO> memberBOs = new ArrayList<kolDatasCheckedBO>(0);
	if (session != null && session.getAttribute("loginRole") != null) {
	    Role sessionRole = (Role) session.getAttribute("loginRole");
	    if (sessionRole.getRoleName().equals("Admin")) {
		members = dataService.searchPreVerifyKOLChecked();
		for (KolDatasChecked kol : members) {
		    kolDatasCheckedBO memberbo = new kolDatasCheckedBO();
		    BeanUtils.copyProperties(kol, memberbo);
		    List<Kolprofitexpdata> kolprofitexpList = dataService.searchKolProfitExp(kol.getUserUuid());
		    BigDecimal total = BigDecimal.ZERO;
		    for (Kolprofitexpdata data : kolprofitexpList) {
			total = total.add(new BigDecimal(data.getOf02()));
		    }
		    Locale locale = new Locale("en", "US");
		    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		    memberbo.setKoltotalProfitExp(currencyFormatter.format(total.intValue()));
		    memberbo.setTotalProfitExp(total.intValue());
		    memberBOs.add(memberbo);
		}
		session.setAttribute("kolDataList", members);
	    }
	}
	return memberBOs;
    }

    /**
     * 讀取KOL分享商品資料
     */
    @RequestMapping(value = "api/getKolProdShareData", method = RequestMethod.POST)
    public @ResponseBody List<WwwShop> getKolProdShareData() {
	 
	List<WwwShop> prodShareList = new ArrayList<WwwShop>(0);
	List<KolShare> kolShares = new ArrayList<KolShare>(0);
	if (session != null && session.getAttribute("loginAccount") != null) {
	    Member user = (Member) session.getAttribute("loginAccount");
	    user = dataService.searchMemberByID(user.getUserUuid());
	    prodShareList = dataService.searchItemListByKOL("chensi.creative@gmail.com", "1");
	    kolShares = dataService.searchKOLShareList(user.getId());
	    for (WwwShop shop : prodShareList) {
		shop.setF94("0");
		shop.setF08(shop.getF08().replace("/uploads/", "http://www.fanshopping.com.tw/uploads/"));
		if (user.getLevel().getLevel().equals("Lv1")) {
		    shop.setF93(String.valueOf(shop.getF95()));
		} else if (user.getLevel().getLevel().equals("Lv2")) {
		    shop.setF93(String.valueOf(shop.getF96()));
		} else if (user.getLevel().getLevel().equals("Lv3")) {
		    shop.setF93(String.valueOf(shop.getF97()));
		}
		for (KolShare share : kolShares) {
		    if (shop.getId() == share.getWwwShop().getId()) {
			shop.setF94("1");
			break;
		    }
		}
	    }
	}
	return prodShareList;
    }

    /**
     * 讀取可供KOL分享商品資料
     */
    @RequestMapping(value = "api/getProdShareConfigData", method = RequestMethod.POST)
    public @ResponseBody List<WwwShop> getProdShareConfigData() {
	 
	List<WwwShop> prodShareConfigList = dataService.searchItemListByAuth("chensi.creative@gmail.com");
	for (WwwShop shop : prodShareConfigList) {
	    if (shop.getF06() <= 0) {
		shop.setF06(shop.getF05());
	    }
	}
	session.setAttribute("prodShareConfigList", prodShareConfigList);
	for (WwwShop shop : prodShareConfigList) {
	    if (shop.getF98().equals("1")) {
		int costValue = new BigDecimal(shop.getF06()).setScale(0).multiply(new BigDecimal("0.10")).intValue();
		shop.setF95(shop.getF95() != costValue ? shop.getF95() : costValue);
		costValue = new BigDecimal(shop.getF06()).setScale(0).multiply(new BigDecimal("0.15")).intValue();
		shop.setF96(shop.getF96() != costValue ? shop.getF96() : costValue);
		costValue = new BigDecimal(shop.getF06()).setScale(0).multiply(new BigDecimal("0.20")).intValue();
		shop.setF97(shop.getF97() != costValue ? shop.getF97() : costValue);
	    }
	}
	return prodShareConfigList;
    }

    /**
     * 頁面轉址
     */
    /**
     * 登出回到首頁
     */
    @RequestMapping("logout")
    public ModelAndView logout() {
	clearSession(req,session);
	return returnView(req,session,"login");
    }

    @RequestMapping("redirectkolverfyresult")
    public ModelAndView redirectkolverfyresult() {
	if (session == null) {
	    return new ModelAndView("redirect:/login");
	} else if (session != null && session.getAttribute("code") == null) {
	    return new ModelAndView("redirect:/login");
	}
	return new ModelAndView("kolverfyresult");
    }

    @RequestMapping("redirectForgetPassword")
    public ModelAndView redirectForgetPassword() {
	log.info("redirectForgetPassword");
	if (session == null) {
	    return new ModelAndView("redirect:/login");
	} else if (session != null && session.getAttribute("code") == null) {
	    return new ModelAndView("redirect:/login");
	}
	return new ModelAndView("forgetpasswordresult");
    }

    /**
     * 未知頁面(404)
     */
    @RequestMapping("undefined")
    public ModelAndView undefined() {
	return returnView(req,session,"undefined");
    }

    /**
     * 總覽
     */
    @RequestMapping(value = "overview")
    public ModelAndView overview() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"overview");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 商品分享管理 - 分享商品 view: prodShare KOL 分享達人設定要分享的商品
     */
    @RequestMapping(value = "/prodShare")
    public ModelAndView prodShare() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"prodShare");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 商品分享管理 - 分享商品設定 view: prodSharConfig
     */
    @RequestMapping(value = "/prodShareConfig")
    public ModelAndView prodSharConfig() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"prodShareConfig");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 商品分享管理 - 分享商品紀錄 view: prodSharRecord
     */
    @RequestMapping(value = "/prodShareRecord")
    public ModelAndView prodShaeRecord() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"prodShareRecord");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 分享達人管理 - 分享達人申請審核 view: checkMember
     */
    @RequestMapping(value = "/checkMember")
    public ModelAndView checkMember() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"checkMember");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 分享達人管理 - 分享達人申請審核 view: checkMember
     */
    @RequestMapping(value = "/configMember")
    public ModelAndView configMember() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"configMember");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 分潤支出 - 分潤支出審核 view: profitExpConfig
     */
    @RequestMapping(value = "/profitExpConfig")
    public ModelAndView profitExpConfig() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"profitExpConfig");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }

    /**
     * 分潤資料 - 分潤收益查詢 view: profitExpState
     */
    @RequestMapping(value = "/profitExpState")
    public ModelAndView profitExpState() {
	if (session != null && session.getAttribute("loginAccount") != null) {
	    return returnView(req,session,"profitExpState");
	} else {
	    clearSession(req,session);
	    return returnView(req,session,"redirect:/redirectLogin");
	}
    }
}
