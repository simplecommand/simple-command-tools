package de.mwolff.examples.passwordvalidator;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean(name = { "lengthValidator" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public LengthValidator<PasswordParameter> lengthValidator() {
        return new LengthValidator<PasswordParameter>();
    }

    @Bean(name = { "passwordService" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PasswordService passwordService() {
        return new PasswordService();
    }

    @Bean(name = { "passwordParameter" })
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PasswordParameter passwordParameter() {
        return new PasswordParameter();
    }

}
