package com.tianye.shiro;

import com.tianye.entity.Admin;
import com.tianye.entity.Role;
import com.tianye.mapper.AdminMapper;
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

public class MyRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        Admin admin = adminMapper.queryAdminByUsername(primaryPrincipal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(admin.getRoleName());
        System.out.println("================");
        for (Role role:admin.getRoles()) {
            simpleAuthorizationInfo.addStringPermissions(role.getAuthoritieName());
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Admin admin =adminMapper.queryAdminByUsername(authenticationToken.getPrincipal().toString());
        System.out.println(admin);
        AuthenticationInfo authenticationInfo = null;
        if (admin.getUsername().equals(authenticationToken.getPrincipal().toString())) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), this.getName());

        }
        return authenticationInfo;
    }

}
