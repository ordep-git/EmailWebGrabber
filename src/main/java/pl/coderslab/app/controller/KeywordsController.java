package pl.coderslab.app.controller;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.app.entity.Email;
import pl.coderslab.app.entity.Keywords;
import pl.coderslab.app.entity.Url;
import pl.coderslab.app.email.LinkExtractor;
import pl.coderslab.app.email.EmailExtractor;
import pl.coderslab.repository.EmailRepository;
import pl.coderslab.repository.KeywordsRepository;
import pl.coderslab.repository.UrlRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class KeywordsController {
    private static final String GOOGLE_PRE = "https://www.google.com/search?q=email+";
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;
    private final KeywordsRepository keywordsRepository;
    Logger logger = null;
    Set<String> emails = new HashSet<>();

    public KeywordsController(EmailRepository emailRepository, UrlRepository urlRepository, KeywordsRepository keywordsRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
        this.keywordsRepository = keywordsRepository;
    }

    //logger test-model
//    @RequestMapping(value = "/test-email")
//    @ResponseBody
//    public void getAllFromMap(Model model) {
//        List<Email> emails = emailRepository.findAllByEmails();
//        model.addAttribute("emails", emails);
//        model.asMap().forEach((k, v) -> logger.debug(k + " : " + v));
//    }

    //    search links by keywords and save
    @RequestMapping("/searchlinks")
    public String searchLinksByKeywords(Model model, @RequestParam String keywords) {
        List<String> keywordslist2 = Stream.of(keywords.split(" ")).map(elem -> new String(elem)).collect(Collectors.toList());
        String keywordslist = String.join("+", keywordslist2);
        String urlgoogle = GOOGLE_PRE + keywordslist;
        LinkExtractor linkExtractor = new LinkExtractor();
        List<String> links = linkExtractor.searchLinks(urlgoogle);
        EmailExtractor emailExtractor = new EmailExtractor();
//        List<String> linksFiltr = new ArrayList<>();
        //walidacja
        List<String> validlinks = links.stream().filter(link -> emailExtractor.isValidRelativeURL(link)).collect(Collectors.toList());
        //pobranie linku i wrzucenie do metody wyszukiwania
        List<Email> emailList = validlinks.stream().map(link -> emailExtractor.searchEmails(link))
                .flatMap(Collection::stream).map(mail -> new Email(mail)).collect(Collectors.toList());

        model.addAttribute("emails", emailList);
        this.emailRepository.saveAll(emailList);
        Keywords keywords1 = new Keywords(keywordslist, emailList);

        int linkIter = 0;
        for (Email em : emailList) {
            Url url1 = new Url(validlinks.get(linkIter), emailList);
            linkIter++;
            em.addUrl(url1);
            this.urlRepository.save(url1);

            em.addKeyword(keywords1);
            this.keywordsRepository.save(keywords1);
        }
        return "searchkeywords";
    }
}
