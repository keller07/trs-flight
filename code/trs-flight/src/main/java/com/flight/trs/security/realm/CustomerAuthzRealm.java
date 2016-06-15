package com.flight.trs.security.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flight.trs.model.entity.Customer;
import com.flight.trs.security.token.CustomUsernamePasswordTocken;
import com.flight.trs.security.token.IncorrectCaptchaException;
import com.flight.trs.service.basic.ICustomerService;
import com.flight.trs.util.RegexpValidator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月2日 下午5:51:15
 * @version V1.0
 */
public class CustomerAuthzRealm extends AuthorizingRealm{

	private static final Logger logger = LoggerFactory.getLogger(CustomerAuthzRealm.class);
	
	@Resource
	ICustomerService customerService;
	
	public CustomerAuthzRealm() {
	}


	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		
		if (!(authcToken instanceof CustomUsernamePasswordTocken)) {
			logger.error("authcToken is not instanceof CustomUsernamePasswordTocken...");
			return null;
		}
		CustomUsernamePasswordTocken token = (CustomUsernamePasswordTocken)authcToken;
		
		//验证码的校验
		String captchaFromClient = token.getCaptcha();
		String captchaInServer = (String)SecurityUtils.getSubject().getSession().getAttribute("captcha");
		if( null == captchaFromClient || !captchaFromClient.equalsIgnoreCase(captchaInServer) ){
			logger.info("错误的验证码："+captchaFromClient+" - 正确的验证码："+captchaInServer);
			throw new IncorrectCaptchaException();
		}
		
		//取token中的用户名并尝试查找用户信息
		String username = (String)token.getPrincipal();  
		Customer customer = null;
		if ( RegexpValidator.isPhone(username) ) {
			customer = customerService.findByPhone(username);
		}
		else if ( RegexpValidator.isEmail(username) ) {
			customer = customerService.findByEmail(username);
		}
		else {						//登录名格式错误
			throw new IllegalArgumentException("Illegal  username[" + username+ "]");
		}
		
		if (customer == null) {		//找不到该用户
			throw new UnknownAccountException();
		}
		if (customer.isDeleted()) {	//该账户已被删除
			throw new DisabledAccountException();
		}
		if (customer.isLocked()) {	//该账户已被冻结
			throw new LockedAccountException();
		}
		
		//若找到该用户则，该用户的id、密码和动态盐构建相应的认证信息
		String principal = String.valueOf(customer.getId());
		String credentials = customer.getPassword();
		ByteSource dynamicSalt = ByteSource.Util.bytes(customer.getDynamicSalt());
		
		SimpleAuthenticationInfo authcInfo 
		= new SimpleAuthenticationInfo(principal,credentials,dynamicSalt,getName());
		
		//接下来由凭证匹配器匹配
		return authcInfo;
	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO
		String userID = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authzInfo = new SimpleAuthorizationInfo();
		authzInfo.addRole("cust");
		authzInfo.addStringPermission("cust:*:"+userID);
		return authzInfo;
	}
	
}
