package pl.coderslab.app.keywords;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LinkExtractor {
    public Set<String> searchLinks(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> links = new HashSet<>();
        Elements elements = document.select("a[href]");
        for (Element e : elements) {
            links.add(e.attr("href"));
        }
//        System.out.println(links);
//        System.out.println("Rozmiar: " + links.size());
        return links;
    }
}
