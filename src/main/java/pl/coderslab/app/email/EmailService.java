package pl.coderslab.app.email;

import org.springframework.stereotype.Service;
import pl.coderslab.app.entity.Email;
import pl.coderslab.app.entity.Keywords;
import pl.coderslab.app.entity.Url;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmailService {
    //    void delete(Long id);
//    private static final String GOOGLE_PRE = "https://www.google.com/search?q=email+";

//    public List<String> searchAndValid(String keywords){
//        List<String> keywordslist2 = Stream.of(keywords.split(" ")).map(elem -> new String(elem)).collect(Collectors.toList());
//        String keywordslist = String.join("+", keywordslist2);
//        String urlgoogle = GOOGLE_PRE + keywordslist;
//        LinkExtractor linkExtractor = new LinkExtractor();
//        List<String> links = linkExtractor.searchLinks(urlgoogle);
//        EmailExtractor emailExtractor = new EmailExtractor();
//        List<String>validlinks = links.stream().filter(link -> emailExtractor.isValidRelativeURL(link)).collect(Collectors.toList());
//
//        List<Email> emailList = validlinks.stream().map(link -> emailExtractor.searchEmails(link)).flatMap(Collection::stream)
//                .map(mail -> new Email(mail)).collect(Collectors.toList());
//        List<Keywords> keywordList = keywordslist2.stream().map(key -> new Keywords(key, emailList)).collect(Collectors.toList());
//        List<Url> urlList = validlinks.stream().map(link -> new Url(link, emailList)).collect(Collectors.toList());
//        return validlinks;
//    }

}
