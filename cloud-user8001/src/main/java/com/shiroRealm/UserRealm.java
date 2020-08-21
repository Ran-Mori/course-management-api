package com.shiroRealm;

import com.entity.TblUser;
import com.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String principal = authenticationToken.getPrincipal().toString();

        //查询得到此用户
        TblUser user = userService.selectUserByAccount(principal);
        if (user==null)
            return null;
        return new SimpleAuthenticationInfo(
                principal,
                user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()),
                this.getName()
        );
    }
}
