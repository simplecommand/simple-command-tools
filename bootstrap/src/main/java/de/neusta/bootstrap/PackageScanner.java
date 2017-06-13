package de.neusta.bootstrap;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 * Scans a certain package and returns all classes that belongs in this package
 * (test classes as well).
 * 
 * Uses org.reflections library to scan.
 * 
 * @author mwolff
 *
 */
public class PackageScanner {

    /**
     * Gets all classes in a package that match <code>classToFind</code>.
     * Object.class finds all the classes.
     * 
     * @param packagePath Full qualified packagepath e.g. de.neusta.bootstrap
     * @param classToFind Type of class e.g. Object.class.
     * @return
     */
    public Set<Class<?>> getSubTypesOf(String packagePath, Class<Object> classToFind) {
        
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(
                        false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packagePath))));

        Set<Class<?>> classes = reflections.getSubTypesOf(classToFind);
        return classes;
    }

}
