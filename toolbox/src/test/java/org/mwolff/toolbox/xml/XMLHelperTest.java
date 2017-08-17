package org.mwolff.toolbox.xml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class XMLHelperTest {

    @Test
    public void instanceExists() throws Exception {
        final XMLHelper xmlHelper = XMLHelper.getInstance();
        Assert.assertThat(xmlHelper, CoreMatchers.notNullValue());
    }

    @Test
    public void attributeHelper() throws Exception {
        final String resource = "/testxml.xml";
        final SAXReader reader = new SAXReader();
        final InputStream xmlStream = this.getClass().getResourceAsStream(resource);
        final Document document = reader.read(xmlStream);
        @SuppressWarnings("unchecked")
        final List<Element> list = document.selectNodes("//rootelement/child");
        Assert.assertThat(list.size(), CoreMatchers.is(1));
        final Map<String, String> map = XMLHelper.getAttributeOfElement(list.get(0));
        String value = map.get("a");
        Assert.assertThat(value, CoreMatchers.is("1"));
        value = map.get("b");
        Assert.assertThat(value, CoreMatchers.is("2"));
    }
}
