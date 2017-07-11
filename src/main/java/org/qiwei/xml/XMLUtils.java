package org.qiwei.xml;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class XMLUtils {
    private static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

    public static String ObjectToXML(Object obj) {
        String result = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            marshaller.marshal(obj, baos);
            result = StringEscapeUtils.unescapeXml(baos.toString("utf-8"));
        } catch (Exception e) {
            logger.error("ObjectToXML", e);
        }
        return result;
    }

    public static <T> T XMLToObject(Class<T> c, String xmlStr) {
        return XMLToObject(c, new ByteArrayInputStream(xmlStr.getBytes()));
    }

    @SuppressWarnings("unchecked")
    public static <T> T XMLToObject(Class<T> c, InputStream is) {
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(is);
        } catch (Exception e) {
            logger.error("XMLToObject", e);
        }
        return null;
    }

    public static String mapToXML(Map<String, Object> map, String rootElementName) {
        CharArrayWriter caw = new CharArrayWriter();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement(rootElementName);
            doc.appendChild(root);

            Set<Entry<String, Object>> entrySet = map.entrySet();
            for (Entry<String, Object> entry : entrySet) {
                Element ele = doc.createElement(entry.getKey());
                ele.appendChild(doc.createTextNode(String.valueOf(entry.getValue())));
                root.appendChild(ele);
            }

            TransformerFactory ft = TransformerFactory.newInstance();
            Transformer tf = ft.newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");

            StreamResult sResult = new StreamResult(caw);
            tf.transform(new DOMSource(doc), sResult);
        } catch (Exception e) {
            logger.error("mapToXML", e);
        }

        return caw.toString();
    }

    public static Map<String, String> XMLToMap(InputStream is)
            throws ParserConfigurationException, SAXException, IOException {
        Map<String, String> map = new HashMap<>();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(is);
        Element root = document.getDocumentElement();
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            map.put(node.getNodeName(), node.getTextContent());
        }
        return map;
    }

    public static Map<String, String> XMLToMap(String xmlStr, String encode) {
        Map<String, String> map = new HashMap<>();
        try {
            byte bytes[] = xmlStr.getBytes(encode);
            try (ByteArrayInputStream in = new ByteArrayInputStream(bytes);) {
                map = XMLToMap(in);
            } catch (Exception e) {
                logger.error("XMLToMap", e);
            }
        } catch (Exception e) {
            logger.error("XMLToMap", e);
        }

        return map;
    }

    public static Map<String, String> XMLToMap(String xmlStr) {
        return XMLToMap(xmlStr, "utf-8");
    }


}