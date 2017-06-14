package de.neusta.bootstrap.scanner;

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
     * Gets all classes in a package.
     * 
     * @param packagePath
     *            Full qualified package path e.g. de.neusta.bootstrap.test
     * @return All classes in the selected path
     */
    public Set<Class<?>> getSubTypesOf(String packagePath) {

        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(
                        false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packagePath))));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        return classes;
    }

}
