package fr.knife.estockapi.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.springframework.stereotype.Service;

/**
 * The service to manage HTML operations
 */
@Service
public class HTMLService {
    /**
     * Convert the specified string to HTML content
     *
     * @param html The HTML string value to convert
     *
     * @return The HTML wrapper
     */
    private static Document toHTML(String html) {
        return Jsoup.parse(html);
    }

    /**
     * Get the stock value HTML element holder
     *
     * @param html The HTML to check
     *
     * @return The stock value HTML element holder found
     */
    private static Element getStockValueHtmlElementHolder(Document html) {
        return html.select(".c-faceplate__price > .c-instrument--last").get(0);
    }

    /**
     * Get the string value in the specified stock value HTML element holder
     *
     * @param stockValueHtmlElementHolder The stock value HTML element holder to check
     *
     * @return The stock value in string format found
     */
    private static String getStockValueInnerHtml(Element stockValueHtmlElementHolder) {
        return ((TextNode) stockValueHtmlElementHolder.childNodes().get(0)).text();
    }

    /**
     * Get the stock value in the specified HTML
     *
     * @param html The HTML to check
     *
     * @return The found stock value
     */
    public Double getStockValue(String html) {
        return Double.valueOf(getStockValueInnerHtml(getStockValueHtmlElementHolder(toHTML(html))));
    }
}
