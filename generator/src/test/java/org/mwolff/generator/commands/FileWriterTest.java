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
package org.mwolff.generator.commands;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mwolff.generator.structures.Configuration;
import org.mwolff.velocitytools.PropertyLoader;
import org.mwolff.velocitytools.PropertyLoader.Methods;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.mwolff.commons.command.iface.CommandException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class FileWriterTest {

    @Resource
    FileWriter<Configuration> fileWriter;

    @Resource
    Configuration configuration;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void resourcesExist() throws Exception {
        assertThat(fileWriter, notNullValue());
    }
    
    
    @Test
    public void writeFile() throws Exception {
        
        PropertyLoader loader = getLoader();
        String basepath = loader.getProperties().getProperty("basepath");

        configuration.setOutputPath(basepath + "/neu");
        configuration.setActualOutputPath(basepath + "/neu");
        
        File file = new File(configuration.getActualOutputPath() + "/myfile.txt");
        if (file.exists()) {
            file.delete();
        }

        configuration.setMergeString("Hallo Welt");
        configuration.setOutputFile("myfile.txt");
        fileWriter.execute(configuration);

        file = new File(configuration.getActualOutputPath() + "/myfile.txt");
        assertThat(file.exists(), is(true));

        FileReader fr = new FileReader(configuration.getActualOutputPath() + "/myfile.txt");
        BufferedReader br = new BufferedReader(fr);

        String zeile1 = br.readLine();
        System.out.println(zeile1);
        assertThat(zeile1, is("/*"));
        br.close();
        
        
        File dir = new File(configuration.getActualOutputPath());
        String[] entries = dir.list();
        for (String s : entries) {
            File currentFile = new File(dir.getPath(), s);
            currentFile.delete();
        }
        dir.delete();
    }

    @Test
    public void dirCouldnotCreated() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage("Directory could not created. Maybe you've no rights to create this directory.");
        
        PropertyLoader loader = getLoader();

        String basepath = loader.getProperties().getProperty("basepath");

        configuration.setActualOutputPath(basepath + "/xxl/\000");
        configuration.setMergeString("Hallo Welt");
        configuration.setOutputFile("myfile.txt");
        fileWriter.execute(configuration);
    }
    
    @Test
    public void fileCouldnotCreated() throws Exception {
        thrown.expect(CommandException.class);
        
        PropertyLoader loader = getLoader();
        String basepath = loader.getProperties().getProperty("basepath");

        configuration.setOutputPath(basepath);
        configuration.setActualOutputPath(basepath);
        configuration.setMergeString("Hallo Welt");
        configuration.setOutputFile("myfile.txt/,");
        fileWriter.execute(configuration);
    }

    public PropertyLoader getLoader() throws CommandException {
        PropertyLoader loader = new PropertyLoader();
        try {
            loader.initialize("/configuration.test.properties", Methods.CLASSPATH);
        } catch (Exception ex) {
            throw new CommandException(ex);
        }
        return loader;
    }
}
