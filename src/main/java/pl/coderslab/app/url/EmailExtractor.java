package pl.coderslab.app.url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class EmailExtractor {

    public Set<String> searchEmails(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = p.matcher(document.text());

        Set<String> emails = new LinkedHashSet<>(); //HashSet
        while (matcher.find()) {
            emails.add(matcher.group());
        }

//        System.out.println(emails);
//        System.out.println("Rozmiar: " + emails.size());
        return emails;
    }
}
