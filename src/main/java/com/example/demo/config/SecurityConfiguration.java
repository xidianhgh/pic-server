package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MI on 2019/6/22.
 */
// extends WebSecurityConfigurerAdapter
@Configuration
public class SecurityConfiguration  {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//        http.authorizeRequests().antMatchers("/**").permitAll()
//                .antMatchers("").permitAll()
//                .antMatchers("").rememberMe()
//                .antMatchers(HttpMethod.DELETE,"").hasRole("user")
//                .antMatchers("").authenticated();
//    }

    @Bean
    public com.example.demo.entity.HttpSecurity httpSecurity(){
        com.example.demo.entity.HttpSecurity httpSecurity=new com.example.demo.entity.HttpSecurity();
      httpSecurity.antMatchers("/vv")
                  .antMatchers("..");
      return httpSecurity;

    }

//    List<RequestMatcher> list=new ArrayList<>();
//private void test(){
//    list.add(new AntPathRequestMatcher(""));
//}
}
