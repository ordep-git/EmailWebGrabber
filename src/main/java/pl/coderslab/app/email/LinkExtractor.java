package pl.coderslab.app.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkExtractor {

    public List<String> searchLinks(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> links = new ArrayList<>();
        Elements elements = document.select("a[href]");
        for (Element e : elements) {
            links.add(e.attr("href"));
        }
//        System.out.println(links);
//        System.out.println("Rozmiar: " + links.size());
        return links;
    }

}
