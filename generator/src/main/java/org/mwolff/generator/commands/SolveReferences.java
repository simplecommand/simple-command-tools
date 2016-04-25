package org.mwolff.generator.commands;

import java.util.List;

import org.apache.log4j.Logger;
import org.mwolff.generator.structures.ClassStructure;
import org.mwolff.generator.structures.Configuration;
import org.mwolff.generator.structures.InstanceVariable;

import de.mwolff.commons.command.AbstractDefaultChainCommand;
import de.mwolff.commons.command.iface.CommandException;

/**
 * Add foreign referencen which are described in class.xml to the import
 * statement.
 * 
 * e.g. Person has Address. Address should be an import statement in
 * Person.java.
 * 
 * @author mwolff
 */
public class SolveReferences<T extends Configuration> extends AbstractDefaultChainCommand<T> {

    private static final Logger LOG = Logger.getLogger(SolveReferences.class);

    @Override
    public void execute(T context) throws CommandException {

        List<ClassStructure> allStructures = context.getConfigurationList();
        List<ClassStructure> lookUpStructure = context.getConfigurationList();

        for (ClassStructure classStructure : allStructures) {

            List<InstanceVariable> variableList = classStructure.getInstanceVariableList();

            for (InstanceVariable instanceVariable : variableList) {
                String type = instanceVariable.getType();

                String cardinality = instanceVariable.getCardinality();

                if (("one-many".equals(cardinality)) && (!classStructure.importerExists("java.util.List"))) {
                    classStructure.addImports("java.util.List");
                    classStructure.addImports("java.util.ArrayList");
                }

                for (ClassStructure lookup : lookUpStructure) {

                    if (lookup.getIdentifier().equals(type)) {
                        if (!classStructure.importerExists(lookup.getPackageString() + "." + type)) {
                            LOG.info("Add packagestring: " + lookup.getPackageString());
                            classStructure.addImports(lookup.getPackageString() + "." + type);
                        }
                    }
                }
            }
            
            if ("true".equals(context.getIdDefault())) {
                InstanceVariable variable = new InstanceVariable();
                variable.setCardinality("none");
                variable.setIdentifier("id");
                variable.setModifier("private");
                variable.setType("Long");
                classStructure.addInstanceVariables(variable);
            }
        }
    }
}
