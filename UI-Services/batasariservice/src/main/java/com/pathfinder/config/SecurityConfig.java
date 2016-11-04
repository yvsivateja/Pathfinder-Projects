package com.pathfinder.config;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	// extends WebSecurityConfigurerAdapter {

	// @Autowired
	// DataSource dataSource;

	// @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception
	// {
	// return super.authenticationManagerBean();
	// }
	//
	// @Autowired
	// public void registerAuthentication(AuthenticationManagerBuilder auth)
	// throws Exception {
	// String roleQuery =
	// "select username,rolename from user u JOIN userrole ur ON u.userid=ur.userid JOIN role r ON r.roleid=ur.roleid where u.username=?";
	// auth.jdbcAuthentication()
	// .dataSource(dataSource)
	// .usersByUsernameQuery(
	// "select username,password, status from user where username=?")
	// .authoritiesByUsernameQuery(roleQuery);
	
	// }
	//
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	//
	
	// /*
	// * http.authorizeRequests().antMatchers("/")
	// * .access("hasRole('Default_Role')").and().formLogin()
	// * .loginPage("/login").failureUrl("/login?error")
	// * .usernameParameter("username").passwordParameter("password")
	// * .and().logout().logoutSuccessUrl("/login?logout").and()
	// * .exceptionHandling().accessDeniedPage("/403").and().csrf();
	// */
	// http.csrf().disable().formLogin().loginPage("/login")
	// .failureUrl("/login?login_error=1")
	// .defaultSuccessUrl("/group/all").and().logout()
	// .logoutUrl("/logout").logoutSuccessUrl("/index");
	// }
	//
	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web.ignoring().antMatchers("/*");
	// }
}