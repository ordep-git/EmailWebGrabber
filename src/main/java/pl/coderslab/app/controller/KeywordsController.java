package pl.coderslab.app.controller;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.Email;
import pl.coderslab.app.entity.Keywords;
import pl.coderslab.app.entity.Url;
import pl.coderslab.app.email.LinkExtractor;
import pl.coderslab.app.email.EmailExtractor;
import pl.coderslab.repository.EmailRepository;
import pl.coderslab.repository.KeywordsRepository;
import pl.coderslab.repository.UrlRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class KeywordsController {
    private static final String GOOGLE_PRE = "https://www.google.com/search?q=email+";
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;
    private final KeywordsRepository keywordsRepository;
    Logger logger = null;
    Set<String> emails = null;

    public KeywordsController(EmailRepository emailRepository, UrlRepository urlRepository, KeywordsRepository keywordsRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
        this.keywordsRepository = keywordsRepository;
    }

    //    search links by keywords and save
    @RequestMapping("/searchlinks")
    @ResponseBody
    public String searchLinksByKeywords(Model model, @RequestParam String keywords) {
        List<String> keywordslist2 = Stream.of(keywords.split(" ")).map(elem -> new String(elem)).collect(Collectors.toList());
        String keywordslist = String.join("+", keywordslist2);
        String urlgoogle = GOOGLE_PRE + keywordslist;
        LinkExtractor linkExtractor = new LinkExtractor();
        List<String> links = linkExtractor.searchLinks(urlgoogle);

        //pobranie linku i wrzucenie do metody wyszukiwania
        EmailExtractor emailExtractor = new EmailExtractor();
        int i = 14;
        do {
//            if (emailExtractor.searchEmails(links.get(i)) == null) {
            i++;
//            }
            emails = emailExtractor.searchEmails(links.get(i));
        } while (links.get(i).isEmpty());

        model.addAttribute("emails", emails);
        model.addAttribute("url", urlgoogle);
        model.addAttribute("links", links);
        List<Email> emailList = emails.stream().map(Email::new).collect(Collectors.toList());
        this.emailRepository.saveAll(emailList);

        Keywords keywords1 = new Keywords(keywordslist, emailList);
        Url url1 = new Url(urlgoogle, emailList);
        for (Email em : emailList) {
            em.addKeyword(keywords1);
            this.keywordsRepository.save(keywords1);
            em.addUrl(url1);
            this.urlRepository.save(url1);
        }

//        links.forEach(System.out::println);
//        return emails;
//        return "linki: " + links;
        return "linki: "+ links.get(15);
//        return "search";
    }

}
