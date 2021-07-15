package pl.coderslab.app.url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailDao {

    public Set<String> searchEmails(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        Matcher matcher = p.matcher(document.text());

        Set<String> emails = new LinkedHashSet<>(); //HashSet
        while (matcher.find()) {
            emails.add(matcher.group());
        }
//        System.out.println(emails);
        return emails;
    }
}
