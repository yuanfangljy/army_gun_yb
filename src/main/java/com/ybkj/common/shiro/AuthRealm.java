/*
package com.ybkj.common.shiro;

import com.ybkj.gun.mapper.WebUserMapper;
import com.ybkj.gun.model.WebUser;
import com.ybkj.gun.service.WebUserSerivce;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.pkcs11.Secmod;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private WebUserSerivce webUserSerivce;
    @Autowired
    private WebUserMapper webUserMapper;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        WebUser user = webUserMapper.selectWebUserByUsername(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        WebUser user=(WebUser) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        Set<String> roles=new HashSet<String>();
        roles.add("user");

        //用户的电话号码
        Object userMobile=null;
        //用户的权限
        int userRights=0;
        //用户
        WebUser users =null;
        try {
            //查询用户的信息
            users = shiroService.getUserByPhoneshiro(principal.toString());
            userRights=users.getUserRights();
            userMobile=users.getUserMobile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //给登录的当前用户，设置权限，打上这个注解@RequiresRoles({"admin"})，满足了上面的条件的才可以进行访问
        if(userMobile.equals(principal) && userRights==1){
            System.out.println("权限：++++1111111111111111");
            roles.add("admin");
        }

        //3. 创建 SimpleAuthorizationInfo ，并设置 reles 属性
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        //4. 返回 SimpleAuthorizationInfo 对象
        return info;
    }

}*/
