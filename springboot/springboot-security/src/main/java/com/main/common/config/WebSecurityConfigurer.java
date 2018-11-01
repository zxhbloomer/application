package com.main.common.config;



import com.main.service.impl.security.SecurityUserDetailService;
import com.main.service.impl.security.SimpleLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailService securityUserDetailService;

    @Autowired
    private SimpleLoginSuccessHandler simpleLoginSuccessHandler;

    @Autowired
    RedisSessionRegistryImpl redisSessionRegistry;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(securityUserDetailService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 跨域攻击防护
            .sessionManagement().maximumSessions(1).sessionRegistry(redisSessionRegistry) //不允许重复登录
            .expiredUrl("/login") //当Session过期之后,跳转的URL
//                .expiredSessionStrategy(new SessionStrategy())   //Session过期时间策略
            .and().and()
            .authorizeRequests()
            .antMatchers( "/error").permitAll() //(登录后) 所有 "用户" 可访问
            .antMatchers("/home").anonymous() //(无须登录) 所有人 可访问
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")    //当没有授权的人访问的时候默认跳转到login
            .failureUrl("/login?error")// 当你账号密码不正确的时候跳转的页面
//                .defaultSuccessUrl("/welcome") // 当你成功登陆之后的界面(此配置URL会被successHandler覆盖)
            .successHandler(simpleLoginSuccessHandler) //登陆成功处理,覆盖defaultSuccessUrl
            .permitAll()
            .and()
            .logout();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
    }


}
