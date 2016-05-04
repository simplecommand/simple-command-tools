package org.mwolff.xml;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XMLHelperTest {

    @Test
    public void instanceExists() throws Exception {
        XMLHelper xmlHelper = XMLHelper.getInstance();
        assertThat(xmlHelper, notNullValue());
    }
    
    @Test
    public void attributeHelper() throws Exception {
        final String resource = "/testxml.xml";
        final SAXReader reader = new SAXReader();
        final InputStream xmlStream = this.getClass().getResourceAsStream(resource);
        Document document = reader.read(xmlStream);
        final List<Element> list = document.selectNodes("//rootelement/child");
        assertThat(list.size(), is(1));
        Map<String, String> map = XMLHelper.getAttributeOfElement(list.get(0));
        String value = map.get("a");
        assertThat(value, is("1"));
        value = map.get("b");
        assertThat(value, is("2"));
    }
}
