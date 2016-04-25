package org.mwolff.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import runner.SpringRunner;

/**
 * Says "Hi" to the user.
 *
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