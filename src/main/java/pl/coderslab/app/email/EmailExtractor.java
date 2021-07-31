package pl.coderslab.app.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class EmailExtractor {

    private final String REGEX_EMAIL = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

    public Set<String> searchEmails(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile(REGEX_EMAIL);
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
