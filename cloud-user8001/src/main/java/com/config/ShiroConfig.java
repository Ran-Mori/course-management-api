package com.config;

import com.Controller.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //过滤器，绑定安全管理器
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
//        /**
//         * 添加shiro内置过滤器
//         * anno:无需认证
//         * authc：必须认证
//         * user:拥有 记住我 能访问
//         * perms：拥有对某个资源的权限才能访问
//         * role：拥有某个角色权限才能访问
//         */
//        //创建新的过滤器
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //绑定安全管理器
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        //创建链式hashMap
//        Map<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
//        //往hash链中填键值对设置过滤。其中键的值是url中的访问路径，值是固定的几个
//        filterChainDefinitionMap.put("/user/*","authc");//指user路径下的所有访问
//        filterChainDefinitionMap.put("/add","authc");
//        filterChainDefinitionMap.put("/update","authc");
//        //授权设置
//        filterChainDefinitionMap.put("/add","perms[/:add:*]");
//        //设置过滤器的过滤列表
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        //设置默认跳转的登录URL地址
//        shiroFilterFactoryBean.setLoginUrl("/toLogin");
//        //设置未授权URL地址
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
//        return shiroFilterFactoryBean;
//    }

    //安全管理器，绑定Realm
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //把UserRealm放入容器，默认名字为方法名，可通过name属性修改
    @Bean(name = "userRealm")
    UserRealm getUserRealm(){
        //创建自定义的Realm
        UserRealm userRealm = new UserRealm();
        //创建hash凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置这个匹配器的参数
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        //更改Realm的默认匹配器为新创建且设置还参数的hash匹配器
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

}
