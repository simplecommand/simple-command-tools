package de.mwolff.examples.loginvalidator;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean(name = { "lengthValidator" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public LengthValidator lengthValidator() {
        return new LengthValidator();
    }

    @Bean(name = { "loginService" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public LoginService loginService() {
        return new LoginService();
    }
}
