package org.mwolff.classgenerator.artifacts;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.DisplayTool;
import org.junit.Assert;
import org.junit.Test;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.InstanceVariable;
import org.mwolff.velocitytools.VelocityMerger;

public class artifactsClassesTest {

    private static final Logger LOG = Logger.getLogger(artifactsClassesTest.class);

    //@formatter:off
    String templatePackage = "package $classStructure.packageString;";
    
    String packagename= "package org.mwolff.configuration;";
    
    String templateClassComment = "/**\n" +
                                  "  * $classStructure.classComment\n" +
                                  "  * @ $classStructure.author\n" +
                                  "  * @ $classStructure.version\n" +
                                  "  /*";
    String classComment = "/**\n" +
                          "  * Beschreibung der Klasse in einem Satz.\n" +
                          "  * @ Manfred Wolff\n" +
                          "  * @ 1.0.0\n" +
                          "  /*";
    
    String templateClassHeader = "public class $classStructure.identifier#if($classStructure.extendsString) extends $classStructure.extendsString#end#if($classStructure.implementsString) implements $classStructure.implementsString#end {";
    String templateClassHeaderHibernate=
            "#if ($classStructure.hibernate == \"true\")\n" +
            "@Entity\n" +
            "#end\n" +        
            "public class $classStructure.identifier#if($classStructure.extendsString) extends $classStructure.extendsString#end#if($classStructure.implementsString) implements $classStructure.implementsString#end {";
    
    String classHeader = "public class MyClass {";
    String classHeaderHibernate=
            "@Entity\n" +
            "public class MyClass {";
    
    String classWithExtends = "public class MyClass extends MyOtherClass {";
    String classWithImplements = "public class MyClass implements MyInterface {"; 
    String classWithExtendsImplements = "public class MyClass extends MyOtherClass implements MyInterface {";
    
    String templateInstanceVariable = "#foreach ($instanceVariable in $classStructure.instanceVariableList)\n" +
                                      "#if ($instanceVariable.cardinality == \"none\")\n" +  
                                      "#if (($instanceVariable.identifier == \"id\") && ($classStructure.hibernate == \"true\"))\n" +
                                      "    @Id\n" +
                                      "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n" +
                                      "#end\n" +
                                      "    $instanceVariable.modifier $instanceVariable.type $instanceVariable.identifier;\n" +
                                      "#end\n" +
                                      "#if ($instanceVariable.cardinality == \"one-many\")\n" +  
                                      "    $instanceVariable.modifier List<$instanceVariable.type> ${instanceVariable.identifier}List = new ArrayList<$instanceVariable.type>();\n" +
                                      "#end\n" +
                                      "#end";
    
    String instanceVariable = 
            "    private String var;\n" +
            "    private String var2;\n";
    
    String instanceVariableOM = "    private List<String> varList = new ArrayList<String>();\n";

    String instanceVariableHibernate = 
            "    private String var;\n" +
            "    @Id\n" +
            "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n" +
            "    private Long id;\n";
    
    String templateSetterGetter = 
                   "#foreach ($instanceVariable in $classStructure.instanceVariableList)\n" +
                    "#if ($instanceVariable.cardinality == \"none\")\n" +  
                    "    public $instanceVariable.type get${display.capitalize($instanceVariable.identifier)}() {\n" +
                    "        return this.$instanceVariable.identifier;\n" +
                    "    }\n" + 
                    "    public void set${display.capitalize($instanceVariable.identifier)}(final $instanceVariable.type $instanceVariable.identifier) {\n" +
                    "        this.$instanceVariable.identifier = $instanceVariable.identifier;\n" +
                    "    }\n" + 
                    "#end\n" +
                    "#if ($instanceVariable.cardinality == \"one-many\")\n" +  
                    "    public List<$instanceVariable.type> get${display.capitalize($instanceVariable.identifier)}List() {\n" +
                    "        return new ArrayList<$instanceVariable.type>(this.${instanceVariable.identifier}List);\n" +
                    "    }\n" + 
                    "    public void add${display.capitalize($instanceVariable.identifier)}(final $instanceVariable.type $instanceVariable.identifier) {\n" +
                    "        this.${instanceVariable.identifier}List.add(${instanceVariable.identifier});\n" +
                    "    }\n" + 
                    "#end\n" +
                    "#end";
     
    String setterGetter = 
            "    public String getVar() {\n" +
            "        return this.var;\n" +
            "    }\n" +
            "    public void setVar(final String var) {\n" +
            "        this.var = var;\n" +
            "    }\n";

    String setterGetterOM = 
            "    public List<String> getVarList() {\n" +
            "        return new ArrayList<String>(this.varList);\n" +
            "    }\n" +
            "    public void addVar(final String var) {\n" +
            "        this.varList.add(var);\n" +
            "    }\n";
    
    String templateImportStatement = "#foreach ($import in $classStructure.importsList)\n" +
                                     "import $import;\n" +
                                     "#end";
    String importStatement = "import de.mwolff.entity.Adresse;\n" +
                             "import de.mwolff.entity Person;\n";
    
    String templateImportStatementHibernate = 
            "#if ($classStructure.hibernate == \"true\")\n" + 
            "import javax.persistence.Entity;\n" +
            "import javax.persistence.GeneratedValue;\n" +
            "import javax.persistence.GenerationType;\n" +
            "import javax.persistence.Id;\n" +
            "#end\n" +            
            "#foreach ($import in $classStructure.importsList)\n" +
            "import $import;\n" +
            "#end";

    String importStatementHibernate =
            "import javax.persistence.Entity;\n" +
            "import javax.persistence.GeneratedValue;\n" +
            "import javax.persistence.GenerationType;\n" +
            "import javax.persistence.Id;\n" +
            "import de.mwolff.entity.Adresse;\n" +
            "import de.mwolff.entity Person;\n";
    
    //@formatter:on

    @Test
    public void packagename() throws Exception {
        String s = templatePackage;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setPackageString("org.mwolff.configuration");
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(packagename);
        LOG.info(w.toString());
        Assert.assertEquals(packagename, w.toString());

    }

    @Test
    public void setterGetter() throws Exception {
        String s = templateSetterGetter;
        LOG.info(s);
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        InstanceVariable localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("String");
        localInstance.setIdentifier("var");
        localInstance.setCardinality("none");
        structure.addInstanceVariables(localInstance);
        context.put("display", new DisplayTool());
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info("\n\n" + setterGetter);
        LOG.info(w.toString());
        Assert.assertEquals(setterGetter, w.toString());
    }

    @Test
    public void setterGetterOM() throws Exception {
        String s = templateSetterGetter;
        LOG.info(s);
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        InstanceVariable localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("String");
        localInstance.setIdentifier("var");
        localInstance.setCardinality("one-many");
        structure.addInstanceVariables(localInstance);
        context.put("display", new DisplayTool());
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info("\n\n" + setterGetterOM);
        LOG.info("\n" + w.toString());
        Assert.assertEquals(setterGetterOM, w.toString());

    }

    @Test
    public void instanceVarialeNone() throws Exception {
        String s = templateInstanceVariable;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        InstanceVariable localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("String");
        localInstance.setIdentifier("var");
        localInstance.setCardinality("none");
        structure.addInstanceVariables(localInstance);

        localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("String");
        localInstance.setIdentifier("var2");
        localInstance.setCardinality("none");
        structure.addInstanceVariables(localInstance);

        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(instanceVariable);
        LOG.info(w.toString());
        Assert.assertEquals(instanceVariable, w.toString());
    }

    @Test
    public void instanceVarialeOneMany() throws Exception {
        String s = templateInstanceVariable;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        List<InstanceVariable> instanceVariableList = new ArrayList<InstanceVariable>();

        InstanceVariable localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("String");
        localInstance.setIdentifier("var");
        localInstance.setCardinality("one-many");
        instanceVariableList.add(localInstance);

        structure.setInstanceVariableList(instanceVariableList);
        context.put("instanceVariableList", instanceVariableList);
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(instanceVariableOM);
        LOG.info(w.toString());
        Assert.assertEquals(instanceVariableOM, w.toString());
    }

    @Test
    public void instanceVarialeHibernate() throws Exception {
        String s = templateInstanceVariable;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        List<InstanceVariable> instanceVariableList = new ArrayList<InstanceVariable>();

        InstanceVariable localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("String");
        localInstance.setIdentifier("var");
        localInstance.setCardinality("none");
        instanceVariableList.add(localInstance);

        localInstance = new InstanceVariable();
        localInstance.setModifier("private");
        localInstance.setType("Long");
        localInstance.setIdentifier("id");
        localInstance.setCardinality("none");
        instanceVariableList.add(localInstance);
        
        structure.setInstanceVariableList(instanceVariableList);
        structure.setHibernate("true");
        
        
        
        context.put("instanceVariableList", instanceVariableList);
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(instanceVariableHibernate);
        LOG.info(w.toString());
        Assert.assertEquals(instanceVariableHibernate, w.toString());
    }
    
    @Test
    public void mergeClassHeader() throws Exception {
        String s = templateClassHeader;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setIdentifier("MyClass");
        structure.setExtendsString(null);
        structure.setImplementsString(null);
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(classHeader);
        LOG.info(w.toString());
        Assert.assertEquals(classHeader, w.toString());
    }

    @Test
    public void mergeClassHeaderHibernate() throws Exception {
        String s = templateClassHeaderHibernate;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setIdentifier("MyClass");
        structure.setExtendsString(null);
        structure.setImplementsString(null);
        structure.setHibernate("true");
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(classHeader);
        LOG.info(w.toString());
        Assert.assertEquals(classHeaderHibernate, w.toString());
    }

    @Test
    public void mergeClassHeaderWithExtends() throws Exception {
        String s = templateClassHeader;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setIdentifier("MyClass");
        structure.setExtendsString("MyOtherClass");
        structure.setImplementsString(null);
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(classWithExtends);
        LOG.info(w.toString());
        Assert.assertEquals(classWithExtends, w.toString());
    }

    @Test
    public void mergeClassHeaderWithImplements() throws Exception {
        String s = templateClassHeader;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setIdentifier("MyClass");
        structure.setExtendsString(null);
        structure.setImplementsString("MyInterface");
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(classWithImplements);
        LOG.info(w.toString());
        Assert.assertEquals(classWithImplements, w.toString());
    }

    @Test
    public void mergeClassHeaderWithExtendsImplements() throws Exception {
        String s = templateClassHeader;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setIdentifier("MyClass");
        structure.setExtendsString("MyOtherClass");
        structure.setImplementsString("MyInterface");
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(classWithExtendsImplements);
        LOG.info(w.toString());
        Assert.assertEquals(classWithExtendsImplements, w.toString());
    }

    @Test
    public void mergeClassBody() throws Exception {
        String s = templateClassComment;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structure = new ClassStructure();
        structure.setClassComment("Beschreibung der Klasse in einem Satz.");
        structure.setAuthor("Manfred Wolff");
        structure.setVersion("1.0.0");
        context.put("classStructure", structure);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(classComment);
        LOG.info(w.toString());
        Assert.assertEquals(classComment, w.toString());
    }

    @Test
    public void mergeImport() throws Exception {
        String s = templateImportStatement;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structureAddress = new ClassStructure();
        structureAddress.setIdentifier("Addresse");
        structureAddress.setPackageString("de.mwolff.entity");
        structureAddress.addImports("de.mwolff.entity.Adresse");
        structureAddress.addImports("de.mwolff.entity Person");
        context.put("classStructure", structureAddress);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(importStatement);
        LOG.info(w.toString());
        Assert.assertEquals(importStatement, w.toString());
    }

    @Test
    public void mergeImportHibernate() throws Exception {
        String s = templateImportStatementHibernate;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structureAddress = new ClassStructure();
        structureAddress.setIdentifier("Addresse");
        structureAddress.setPackageString("de.mwolff.entity");
        structureAddress.addImports("de.mwolff.entity.Adresse");
        structureAddress.addImports("de.mwolff.entity Person");
        structureAddress.setHibernate("true");
        context.put("classStructure", structureAddress);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(importStatementHibernate);
        LOG.info(w.toString());
        Assert.assertEquals(importStatementHibernate, w.toString());
    }

    @Test
    public void mergeImportNoImport() throws Exception {

        String s = templateImportStatement;
        final VelocityContext context = new VelocityContext();
        final ClassStructure structureAddress = new ClassStructure();
        structureAddress.setIdentifier("Addresse");
        structureAddress.setPackageString("de.mwolff.entity");
        context.put("classStructure", structureAddress);
        StringWriter w = new StringWriter();
        VelocityMerger.evaluate(context, w, s);
        LOG.info(importStatement);
        LOG.info(w.toString());
        Assert.assertEquals("", w.toString());
    }
}
