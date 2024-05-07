package utils;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;

public class FileReader {
    public ArrayList<String> readXmlWithBrands() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("src/test/resources/brands.xml");

            Element filtersElement = document.getDocumentElement();

            NodeList manufacturerNodes = filtersElement.getElementsByTagName("brand");

            ArrayList<String> brands = new ArrayList<>();

            for (int i = 0; i < manufacturerNodes.getLength(); i++) {
                Element manufacturerElement = (Element) manufacturerNodes.item(i);
                String brand = manufacturerElement.getTextContent();
                brands.add(brand);
            }
            return brands;
        } catch (Exception e) {
            return (new ArrayList<>(Collections.singleton("")));
        }
    }
}