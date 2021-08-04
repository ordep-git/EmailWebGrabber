package pl.coderslab.app.email;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.coderslab.app.entity.Email;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EmailExtractor {

    private final String REGEX_EMAIL = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

    public Set<Email> searchEmails(String url) {
        Document document = new Document(null);
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = p.matcher(document.text());

        Set<String> emails = new HashSet<>(); //LinkedHashSet
//        while (matcher.find()) {
//            emails.add(matcher.group());
//        }
//        matcher.find().stream().forEach({
//                emails.add(matcher.group())
//        });
        Set<Email> setmails = emails.stream().map(mail -> new Email(mail)).collect(Collectors.toSet());
        return setmails;
    }


    public boolean isValidRelativeURL(String url) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            return true;
        } else {
            return false;
        }
    }
}
