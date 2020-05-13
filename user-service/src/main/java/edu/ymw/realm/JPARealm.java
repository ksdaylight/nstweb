/**
* 模仿天猫整站 springboot 教程 为 how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package edu.ymw.realm;


import edu.ymw.pojo.User;
import edu.ymw.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class JPARealm extends AuthorizingRealm {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JPARealm.class);

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = token.getPrincipal().toString();
		log.info("取出的userName:"+userName );
		User user = userService.getByName(userName);
		if(user == null){
			log.info("傻逼" );
			log.info("居然没查到???" );
			log.info("呵呵呵" );
		}
		else {
			log.info("取出的user:"+user.toString() );
		}

		String passwordInDB = user.getPassword();
		String salt = user.getSalt();
		log.info("取出密码:"+passwordInDB );
		log.info("取出的盐:"+salt);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
				getName());
		log.info("验证成功");
		return authenticationInfo;
	}

}

