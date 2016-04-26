/**
    Simple Generator Framework.
    Bases on Simple Command Framework

    Framework for easy source code generation via velocity
    @author Manfred Wolff <m.wolff@neusta.de>
    Download: https://github.com/simplecommand/SimpleCommandFramework-Examples


    Copyright (C) 2016 neusta software development

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
    USA
 */
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
