import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Store {
    private ArrayList<Product> productList = new ArrayList<>();


    public Store() {
    }

    public void addProduct(Product product) {
        
        this.productList.add(product);
    }

    public void removeProduct(int index) {
        productList.remove(index);        
    }

    public void modifyProduct(int index, Product product) {
        productList.set(index, product);
    }

    public void printProducts() {
        if(productList.size() == 0) {
            System.out.println("list empty");
        } else {
            for(Product product : productList) {
                product.printProduct();
            }
        }
    }

    public int getProductIndexById(int id) {
        for(Product product : productList) {
            if(product.getId() == id) {
                return productList.indexOf(product);
            }
        }
        return -1;
    }

    public Product getProductByIndex(int productIndex) {
        return productList.get(productIndex);
    }

    public void saveProductListToXml(String filePath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("products");
            document.appendChild(rootElement);

            for(Product product : productList) {
                Element productElement = document.createElement("product");
                rootElement.appendChild(productElement);

                createChildElement(document, productElement, "name", product.getName());
                createChildElement(document, productElement, "id", String.valueOf(product.getId()));
                createChildElement(document, productElement, "price", String.valueOf(product.getPrice()));                
                createChildElement(document, productElement, "amount", String.valueOf(product.getAmount()));                
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filePath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readProductListFromXml(String filePath) {
        ArrayList<Product> tmpList = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filePath);

            Element rootElement = document.getDocumentElement();

            NodeList childNodeList = rootElement.getChildNodes();
            // int numberOfElements = 0;

            Node node;
            for(int i = 0; i < childNodeList.getLength(); i++) {
                node = childNodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    // numberOfElements++;
                    NodeList childNodesOfProductTag = node.getChildNodes();
                    String name = "", id = "", price = "", amount = "";
                    for(int j = 0; j < childNodesOfProductTag.getLength(); j++) {
                        if(childNodesOfProductTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfProductTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfProductTag.item(j).getTextContent();
                                case "id" -> id = childNodesOfProductTag.item(j).getTextContent();
                                case "price" -> price = childNodesOfProductTag.item(j).getTextContent();
                                case "amount" -> amount = childNodesOfProductTag.item(j).getTextContent();
                            }
                        }
                    }
                    tmpList.add(new Product(name, Integer.parseInt(id), Float.parseFloat(price), Integer.parseInt(amount)));
                }
            }
            productList = tmpList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortList(int sortType) {
        switch (sortType) {
            case 0 -> Collections.sort(productList, Product.productNameComparator);
            case 1 -> Collections.sort(productList, Product.productIdComparator);
            case 2 -> Collections.sort(productList, Product.productPriceComparator);
            case 3 -> Collections.sort(productList, Product.productAmountComparator);
            default -> System.out.println("Not a valid sorting option.");
        }
    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
