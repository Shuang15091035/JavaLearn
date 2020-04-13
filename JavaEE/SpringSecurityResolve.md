org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration
 ->@EnableWebSecurity
 ->org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 	->org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
	->org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#getHttp
		-> org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#init
		->org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder#init
		->org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder#doBuild
		->org.springframework.security.config.annotation.AbstractSecurityBuilder#build
		-> org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration#springSecurityFilterChain

	AuthenticationManager
		->org.springframework.security.authentication.ProviderManager

	AuthenticationProvider
		->org.springframework.security.authentication.dao.DaoAuthenticationProvider
	UserDetailsService
		->org.springframework.security.provisioning.JdbcUserDetailsManager
		->org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter.UserDetailsServiceDelegator
		->org.springframework.security.authentication.CachingUserDetailsService
		org.springframework.security.provisioning.UserDetailsManager
			->org.springframework.security.provisioning.JdbcUserDetailsManager
			-> org.springframework.security.provisioning.InMemoryUserDetailsManager