/** Simple Command Framework.
 * 
 * Framework for easy building software that fits the SOLID principles.
 * 
 * @author Manfred Wolff <m.wolff@neusta.de>
 * 
 *         Download:
 *         https://mwolff.info/bitbucket/scm/scf/simplecommandframework.git
 * 
 *         Copyright (C) 2018 Manfred Wolff and the simple command community
 * 
 *         This library is free software; you can redistribute it and/or
 *         modify it under the terms of the GNU Lesser General Public
 *         License as published by the Free Software Foundation; either
 *         version 2.1 of the License, or (at your option) any later version.
 * 
 *         This library is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         Lesser General Public License for more details.
 * 
 *         You should have received a copy of the GNU Lesser General Public
 *         License along with this library; if not, write to the Free Software
 *         Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 *         02110-1301
 *         USA */
package org.mwolff.toolbox.commands;

import static org.mwolff.command.CommandTransition.*;

import java.io.IOException;

import org.mwolff.command.AbstractDefaultCommand;
import org.mwolff.command.CommandTransition;
import org.mwolff.command.parameterobject.GenericParameterObject;
import org.mwolff.command.sax.ActionContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/** Standard command to deal with sax parser.
 * <p>
 * In-parameter
 * <ul>
 * <li>INPUT_SOURCE org.xml.sax.InputSource</li>
 * <li>CONTENT_HANDLER org.xml.sax.helpers.DefaultHandler</li>
 * </ul>
 * </p>
 *
 * <p>
 * OUt-parameter
 * <ul>
 * <li>ERROR_STRING An error or empty string if everything was OK.</li>
 * </ul>
 * </p>
 * 
 * @author mwolff */
public class SaxParserCommand extends AbstractDefaultCommand<GenericParameterObject> {


    @Override
    public CommandTransition executeCommand(GenericParameterObject parameterObject) {

        try {
            final InputSource inputSource = (InputSource) parameterObject.get("INPUT_SOURCE");
            final ActionContentHandler handler = (ActionContentHandler) parameterObject.get("CONTENT_HANDLER");
            final XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(inputSource);

        } catch (IOException | SAXException e) {
            parameterObject.put("ERROR_STRING", e.getMessage());
            return FAILURE;
        }
        return SUCCESS;
    }

}
