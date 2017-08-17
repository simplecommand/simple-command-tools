package org.mwolff.toolbox.xml;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

/**
 * Little Helper for DOM4J methods.
 * 
 * @author Manfred Wolff
 *
 */
public class XMLHelper {

    private static XMLHelper instance = new XMLHelper();

    private XMLHelper() {
        super();
    }

    public static XMLHelper getInstance() {
        return XMLHelper.instance;
    }

    public static Map<String, String> getAttributeOfElement(final Element element) {
        final Map<String, String> attributeMap = new HashMap<>();
        for (int i = 0; i < element.attributeCount(); i++) {
            attributeMap.put(element.attribute(i).getName(), element.attribute(i).getValue());
        }
        return attributeMap;
    }

}
