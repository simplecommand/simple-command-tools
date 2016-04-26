/**
    Generator-Maven-Plugin.

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
package org.mwolff.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import runner.SpringRunner;

/**
 * Says "Hi" to the user.
 *
 * @configurator include-project-dependencies
 * @requiresDependencyResolution compile+runtime
 */
@Mojo( name = "generate")
public class GeneratorMojo extends AbstractMojo
{
    public void execute() throws MojoExecutionException
    {
        getLog().info( "----------------------------------------------" );
        getLog().info( "----------------Generating stuff--------------" );
        getLog().info( "----------------------------------------------" );
        String[] arguments = new String[]{""};
        SpringRunner.main(arguments);
    }
}