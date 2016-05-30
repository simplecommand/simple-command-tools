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
package org.mwolff.generator.structures;

import java.util.ArrayList;
import java.util.List;

public class ClassStructure {
    
    private String identifier;
    private String extendsString;
    private String implementsString;
    private String classComment;
    private String packageString;
    private String author;
    private String version;
    private String template;
    private String hibernate;
    
    public void addImports(String importString) {
        importsList.add(importString);
    }
    
    public void addInstanceVariables(InstanceVariable instanceVariable) {
        instanceVariableList.add(instanceVariable);
    }
    
    public List<InstanceVariable> getInstanceVariableList() {
        return instanceVariableList;
    }

    public void setInstanceVariableList(List<InstanceVariable> instanceVariablesList) {
        this.instanceVariableList = instanceVariablesList;
    }

    public List<String> getImportsList() {
        return importsList;
    }

    private List<InstanceVariable> instanceVariableList = new ArrayList<InstanceVariable>();
    private List<String> importsList = new ArrayList<String>();
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getExtendsString() {
        return extendsString;
    }

    public void setExtendsString(String extendsString) {
        this.extendsString = extendsString;
    }

    public String getImplementsString() {
        return implementsString;
    }

    public void setImplementsString(String implementsString) {
        this.implementsString = implementsString;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public String getPackageString() {
        return packageString;
    }

    public void setPackageString(String packageString) {
        this.packageString = packageString;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    
    public boolean importerExists(String importerString) {
        boolean result = false;
        
        for (String string : getImportsList()) {
            if (string.equals(importerString)) {
                result = true;
            }
        }
        
        return result;
    }

    public String getHibernate() {
        return hibernate;
    }

    public void setHibernate(String hibernate) {
        this.hibernate = hibernate;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
