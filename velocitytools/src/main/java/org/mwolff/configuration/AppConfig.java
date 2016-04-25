package org.mwolff.configuration;

import org.mwolff.velocitytools.VelocityMerger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "org.mwolff")
public class AppConfig {

    @Bean(name = { "velocityMerger" })
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public VelocityMerger velocityMerger() {
        return new VelocityMerger();
    }
}
