package Hieu_iceTea.FoodMate_Spring.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //region - Authentication | JDBC-
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
        //.withDefaultSchema()
                /*.withUser(
                        User.withUsername("user")
                                .password("123456")
                                .roles("USER")
                )
                .withUser(
                        User.withUsername("admin")
                                .password("123456")
                                .roles("ADMIN")
                )
                .withUser(
                        User.withUsername("Hieu_iceTea")
                                .password("123456")
                                .roles("USER", "ADMIN")
                )
                .withUser(
                        User.withUsername("Hieu_IT")
                                .password("123456")
                                .roles("USER", "ADMIN")
                )*/
        ;

        //inMemoryAuthentication
        /*auth.inMemoryAuthentication()
                .withUser("customer").password("{noop}1996").roles("CUSTOMER")
                .and()
                .withUser("admin").password("{noop}1996").roles("ADMIN");*/
    }

    /*@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //Tắt mã hóa mật khẩu, mật khẩu sẽ được lưu trực tiếp vào DB
    }*/
    //endregion

    //region - Authorization | Configure -
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                // ADMIN:
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("HOST", "ADMIN", "ADMIN_ReadOnly", "STAFF")
                .antMatchers(HttpMethod.POST, "/admin/**").hasAnyRole("HOST", "ADMIN", "STAFF")
                .antMatchers(HttpMethod.PUT, "/admin/**").hasAnyRole("HOST", "ADMIN", "STAFF")
                .antMatchers(HttpMethod.DELETE, "/admin/**").hasAnyRole("HOST", "ADMIN", "STAFF")

                // CLIENT:
                .antMatchers("/account/profile/**").hasRole("CUSTOMER")
                .antMatchers("/account/my-order/**").hasRole("CUSTOMER")

                // API:
                .antMatchers(HttpMethod.GET, "/api/**", "/restApi/**").hasAnyRole("HOST", "ADMIN", "ADMIN_ReadOnly")
                .antMatchers(HttpMethod.POST, "/api/**", "/restApi/**").hasAnyRole("HOST", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/**", "/restApi/**").hasAnyRole("HOST", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/**", "/restApi/**").hasAnyRole("HOST", "ADMIN")


                //.antMatchers("/").permitAll()
                //.anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/account/login") //Bỏ dòng này sẽ dùng trang login mặc định
                //.successHandler(new MyAuthenticationSuccessHandler())
                .defaultSuccessUrl("/account/login-success-redirect")
                .permitAll()

                .and()
                .logout()
                .permitAll()

                //Khi dùng API chú ý bỏ phần này
                /*.and()
                .exceptionHandling()
                .accessDeniedPage("/account/access-denied")*/

                //Thêm 2 dòng này nếu dùng API (Đăng nhập kiểu Basic-Auth trong Postman)
                .and()
                .httpBasic()

                //Thêm phần này để hủy csrf, lúc đó test trong Postman cho dễ, đỡ phải thêm thêm trường @csrf. AHIHI
                .and()
                .csrf()
                .ignoringAntMatchers("/api/**", "/restApi/**")
                //.disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }
    //endregion

    //region - In-Memory Authentication -
    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("Hieu_iceTea")
                        .password("123456")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
    //endregion
}