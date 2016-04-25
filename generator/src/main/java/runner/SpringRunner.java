package runner;

import org.apache.log4j.Logger;
import org.mwolff.generator.structures.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.mwolff.command.chainbuilder.InjectionChainBuilder;
import de.mwolff.commons.command.iface.CommandException;

public class SpringRunner {
    
    private static InjectionChainBuilder<Configuration> injectionChainBuilder;
    private static ApplicationContext ctx;
    private static Configuration configuration;

    private static final Logger LOG = Logger.getLogger(SpringRunner.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        ctx = new ClassPathXmlApplicationContext("/application.xml");
        injectionChainBuilder = (InjectionChainBuilder<Configuration>) ctx.getBean("injectionChainBuilder");
        configuration = (Configuration) ctx.getBean("configuration");

        configuration.setConfiguration("/generator.properties");

        try {
            injectionChainBuilder.execute(configuration);
        } catch (CommandException e) {
            LOG.error(e);
        }
    }

    public static InjectionChainBuilder<Configuration> getBuilder() {
        return injectionChainBuilder;
    }
}
