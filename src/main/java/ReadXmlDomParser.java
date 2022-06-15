import org.telegram.telegrambots.meta.api.objects.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadXmlDomParser {
    private final String EBAY_API = "";

    //Parse XML and create Objects via Model
    public List<Model> getListing(Message message) {
        List<Model> modelList = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        if (message.getText().split(":").length!=4) return modelList;
        try {
            URL url = new URL("http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.13.0&SECURITY-APPNAME=" + EBAY_API +
                    "&GLOBAL-ID=EBAY-US&keywords=" + message.getText().split(":")[0].replaceAll(" ", "%20") +
                    "&paginationInput.entriesPerPage=" + message.getText().split(":")[3] +
                    "&itemFilter(0).name=MaxPrice&itemFilter(0).value=" + message.getText().split(":")[2] +
                    "&itemFilter(1).name=MinPrice&itemFilter(1).value=" + message.getText().split(":")[1] +
                    "&itemFilter(2).name=ListingType&itemFilter(2).value=FixedPrice" +
                    "&sortOrder=StartTimeNewest&buyerPostalCode=11432");

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse((InputStream) url.getContent());
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("item");
            Model model;
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    model = new Model();
                    model.setItemId(Long.parseLong(element.getElementsByTagName("itemId").item(0).getTextContent()));
                    model.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                    model.setGalleryURL(element.getElementsByTagName("galleryURL").item(0).getTextContent());
                    model.setViewItemURL(element.getElementsByTagName("viewItemURL").item(0).getTextContent());
                    model.setCurrentPrice(Double.parseDouble(element.getElementsByTagName("currentPrice").item(0).getTextContent()));
                    model.setListingType(element.getElementsByTagName("listingType").item(0).getTextContent());
                    model.setStartTime(element.getElementsByTagName("startTime").item(0).getTextContent());
                    model.setConditionDisplayName(element.getElementsByTagName("conditionDisplayName").item(0).getTextContent());
                    model.setShippingServiceCost(Double.parseDouble(element.getElementsByTagName("shippingServiceCost").item(0).getTextContent()));
                    model.setInsertTime(element.getElementsByTagName("startTime").item(0).getTextContent());
                    modelList.add(model);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return modelList;
    }
}