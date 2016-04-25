package org.mwolff.generator.structures;

import java.util.List;

import de.mwolff.commons.command.iface.ParameterObject;

public class Configuration implements ParameterObject {

    // configuration.properties or how you will name it.
    private String config;                     // Main configuration (.properties) where basepath and outputpath has to be set

    // Part of configuration.properties.
    private String basepath;                   // Base path of the user of this framework
    private String outputPath;                 // Path where classes should be generated usually $basepath/src/main/java
    private String classTemplate;              // The template for generating the class
    private String xmlfile;                    // XML file with the class definitions to be generated
    private String idDefault;                  // should id generated by default

    private String outputFile;                 // Will be set dynamically typically from PrepareJava command
    private String mergeString;                // The result of the velocity merging. Typically a java file.
    private ClassStructure actualStructure;    // The acutal class in merge mechanism
    private String imports;                    // Will be set dynamically during parse process
    private String actualOutputPath;           // Will be set dynamically für each generated file
    private String hibernateSupport;           // Hibernate Annotations should be set on entities
    
    
    public static final String version = "0.0.1";
    public static final String VERSION = 
            "/*\n" +
            " * DON'T TOUCH THIS FILE. IT'S GENERATED BY generate version $version\n" +
            " */\n";
    
    
    private List<ClassStructure> configurationList;
    
    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getConfiguration() {
        return config;
    }

    public void setConfiguration(String configuration) {
        this.config = configuration;
    }

    public String getMergeString() {
        return mergeString;
    }

    public void setMergeString(String mergeString) {
        this.mergeString = mergeString;
    }

    public String getXmlfile() {
        return xmlfile;
    }

    public void setXmlfile(String xmlfile) {
        this.xmlfile = xmlfile;
    }

    public List<ClassStructure> getConfigurationList() {
        return configurationList;
    }

    public void setConfigurationList(List<ClassStructure> configurationList) {
        this.configurationList = configurationList;
    }

    public String getClassTemplate() {
        return classTemplate;
    }

    public void setClassTemplate(String classTemplate) {
        this.classTemplate = classTemplate;
    }


    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public ClassStructure getActualStructure() {
        return actualStructure;
    }

    public void setActualStructure(ClassStructure actualStructure) {
        this.actualStructure = actualStructure;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getActualOutputPath() {
        return actualOutputPath;
    }

    public void setActualOutputPath(String actualOutputPath) {
        this.actualOutputPath = actualOutputPath;
    }

    public String getIdDefault() {
        return idDefault;
    }

    public void setIdDefault(String idDefault) {
        this.idDefault = idDefault;
    }

    public String getHibernateSupport() {
        return hibernateSupport;
    }

    public void setHibernateSupport(String hibernateSupport) {
        this.hibernateSupport = hibernateSupport;
    }
}