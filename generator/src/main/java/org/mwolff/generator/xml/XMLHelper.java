package org.mwolff.generator.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.InstanceVariable;

public class XMLHelper {
    
    private static final Logger LOG = Logger.getLogger(XMLHelper.class);

    private String xmlFileName;

    public void setXmlFileName(String xmlFileName) {
        LOG.info("XML-File: " + xmlFileName);
        this.xmlFileName = xmlFileName;
    }
    
    public List<ClassStructure>  readXMLFile() throws XMLException {
        final String resource = xmlFileName;
        final SAXReader reader = new SAXReader();
        final Document document = createXMLStream(reader, resource);
        LOG.info("XML-File read.");
        return createClasses(document);
    }
    
    @SuppressWarnings("unchecked")
    private static List<ClassStructure>  createClasses(Document document) throws XMLException {
        
        LOG.info("Create classes.");
        List<ClassStructure> classList = new ArrayList<ClassStructure>();
        final List<Element> list = document.selectNodes("//classes/class");
        if (!list.isEmpty()) {
            for (final Element element : list) {
                extractClassElement(element, classList);
            }
        }
        return classList;
    }

    private static void extractClassElement(final Element element, List<ClassStructure> classList) throws XMLException {
        ClassStructure classStructure = new ClassStructure();
        extractClassAttributes(element, classStructure);
        extractInnerClassElements(element, classStructure);
        classList.add(classStructure);
        LOG.info("Added class " + classStructure.getIdentifier());
    }

    private static void extractClassAttributes(final Element element, ClassStructure classStructure) {
        
        for (@SuppressWarnings("unchecked")
        final Iterator<Attribute> attributeIterator = element.attributeIterator(); attributeIterator.hasNext();) {
            
            final Attribute attribute = attributeIterator.next();
            
            if ("identifier".equals(attribute.getName())) {
                classStructure.setIdentifier(attribute.getValue());
            }
            
            if ("extends".equals(attribute.getName())) {
                classStructure.setExtendsString(attribute.getValue());
            }
            
            if ("implements".equals(attribute.getName())) {
                classStructure.setImplementsString(attribute.getValue());
            }
            
        }
    }
    
    private static void extractInnerClassElements(final Element element, ClassStructure classStructure) {

        @SuppressWarnings("unchecked")
        final List<Element> innerElementList = element.elements();
        
        LOG.info("Setting inner structures for class " + classStructure.getIdentifier());
        
        for (Element innerElement : innerElementList) {
            
            if ("package".equals(innerElement.getName())) {
                classStructure.setPackageString(innerElement.getText());
            }

            if ("classcomment".equals(innerElement.getName())) {
                classStructure.setClassComment(innerElement.getText());
            }

            if ("author".equals(innerElement.getName())) {
                classStructure.setAuthor(innerElement.getText());
            }

            if ("version".equals(innerElement.getName())) {
                classStructure.setVersion(innerElement.getText());
            }
            
            if ("instancevariables".equals(innerElement.getName())) {
                createInstanceVariables(classStructure, innerElement);
            }
        }
    }

    private static void createInstanceVariables(ClassStructure classStructure, Element innerElement) {

        final List<InstanceVariable> variableList = new ArrayList<InstanceVariable>();
        @SuppressWarnings("unchecked")
        final List<Element> instanceVarialbleList = innerElement.elements();
        
        for (Element instanceVariables : instanceVarialbleList) {
            
            final InstanceVariable variable = new InstanceVariable();
            variable.setCardinality("none");
            
            for (@SuppressWarnings("unchecked")
            final Iterator<Attribute> variableIterator = instanceVariables.attributeIterator(); variableIterator.hasNext();) {
                
                final Attribute attribute = variableIterator.next();

                LOG.debug(attribute.getName());    
                
                if ("id".equals(attribute.getName())) {
                    variable.setIdentifier(attribute.getValue());
                }

                if ("type".equals(attribute.getName())) {
                    variable.setType(attribute.getValue());
                }
                
                if ("scope".equals(attribute.getName())) {
                    variable.setModifier(attribute.getValue());
                }

                if ("cardinality".equals(attribute.getName())) {
                    variable.setCardinality(attribute.getValue());
                }
            }
            
            variableList.add(variable);
            LOG.info("Create new instance variable named " + variable.getIdentifier());
        }
        classStructure.setInstanceVariableList(variableList);
    }

    private Document createXMLStream(SAXReader reader, String resource) throws XMLException {
        Document document;
        final InputStream xmlStream = this.getClass().getResourceAsStream(resource);
        if (xmlStream != null) {
            document = createXMLDocument(reader, xmlStream);
        } else {
            LOG.error("Could not read xml file");
            throw new XMLException("Could not read xml file");
        }
        return document;
    }
    
    private static Document createXMLDocument(SAXReader reader, InputStream xmlStream) throws XMLException {
        Document document = null;
        try {
            document = reader.read(xmlStream);
        } catch (final DocumentException e) {
            LOG.error("XML Document could not created");
            throw new XMLException("XML Document could not created", e);
        }
        return document;
    }
}
