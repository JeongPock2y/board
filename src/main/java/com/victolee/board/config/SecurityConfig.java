package com.victolee.board.config;


import com.victolee.board.service.BoardService;
import com.victolee.board.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*BoardService와 연결*/
    private BoardService boardService;
    private MemberService memberService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /*BCryptPasswordEncoder은 Spring Security  에서 제공하는
                비밀번호 암호화 객체 pw encoder Object
                Service에서 사용할수있도록  @Bean처리*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*page setting*/
                /*특정 경로를 지정하며  permitALl or hasRole 등의 메서드르로 역할에따른 접근설정잡아줌*/
                /*ADMIN 만 */
                .antMatchers("/admin/**").hasRole("ADMIN")
                /*일반 사용자*/
                .antMatchers("/user/myinfo").hasRole("MEMBER")
                .antMatchers("/**").permitAll()

            .and() // 로그인 설정 들여쓰기 shift+tab
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/login/result")
                .permitAll()
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")
                .invalidateHttpSession(true)
                //HttpSession 초기화
            .and()
                // 403 예외처리
                .exceptionHandling().accessDeniedPage("/user/denied");
    }
            @Override
            public  void configure(AuthenticationManagerBuilder auth) throws Exception
            {
                auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
            }

}
