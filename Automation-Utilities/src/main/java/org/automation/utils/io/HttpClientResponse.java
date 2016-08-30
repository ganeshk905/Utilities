

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class HttpClientResponse {

    private int httpStatusCode;
    private Document httpResponseBody;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Document getHttpResponseBody() {
        return httpResponseBody;
    }

    public void setHttpResponseBody(Document httpResponseBody) {
        this.httpResponseBody = httpResponseBody;
    }

    public Node getElementByNameWithNamespace(String namespaceUrl, String elementName) {
        return httpResponseBody.getElementsByTagNameNS(namespaceUrl, elementName).item(0);
    }

    public String getElementValueByName(String elementName) {
        Node node = httpResponseBody.getElementsByTagName(elementName).item(0);
        return node.getNodeValue();
    }

    public String getNodeValueByName(String elementName) {
        Node node = httpResponseBody.getElementsByTagName(elementName).item(0);
        return node.getTextContent();
    }

    public String getFaultstringValue() {
        Node fault = getElementByNameWithNamespace("http://schemas.xmlsoap.org/soap/envelope/", "Fault");
        // The second Node (with List-Nr. 1) is the <faultstring>
        Node faultstring = fault.getChildNodes().item(1);
        return faultstring.getTextContent();
    }


}
