package pl.coderslab.app.url;

import ch.qos.logback.classic.Logger;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.EmailRepository;
import pl.coderslab.repository.UrlRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// logger
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/email")
public class EmailController {
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;


    public EmailController(EmailRepository emailRepository, UrlRepository urlRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
    }

    @GetMapping("/")
    public String mainForm() {
        return "mainform";
    }

    @RequestMapping("/listall") //z db
    public String listAllEmail(Model model) {
        List<Email> emails = emailRepository.findAll();
        model.addAttribute("emails", emails);
        return "listall";
    }

    @RequestMapping("/list") //z db
    public String listEmail(Model model) {
        List<Email> emails = emailRepository.findAllByEmails();
//        List<Email> emails = emailRepository.findAll();
        model.addAttribute("emails", emails);
//        model.addAttribute("urls", url);
//        model.addAttribute("emails", emailRepository.findDistinctByEmail(emails));
        return "list";
    }

    //logger test-model
    Logger logger = null;
    @RequestMapping(value = "/test-model")
    @ResponseBody
    public void getAllFromMap(Model model) {
        List<Email> emails = emailRepository.findAllByEmails();
        model.addAttribute("emails", emails);
        model.asMap().forEach((k, v) -> logger.debug(k + "<klucz : wartosc>" + v));
    }

    @GetMapping("/form")
    public String showMainFormSearch() {
        return "form";
    }

//    @PostMapping("/form")
//    public String formSearch(@RequestParam String url, Model model) {
//
//        return "search";
//    }

//    search and save
    @RequestMapping("/search")
    public String searchListEmail(@RequestParam String url, Model model) throws IOException {
        EmailExtractor emailExtractor = new EmailExtractor();
        Set<String> emails = emailExtractor.searchEmails(url);
        model.addAttribute("url", url);
        model.addAttribute("emails", emails);
        List<Email> emailList = emails.stream().map(Email::new).collect(Collectors.toList());
        this.emailRepository.saveAll(emailList);

//        this.urlRepository.save(url);
        return "search";
    }

    // dorobić formularz i chceckboxy
//    @PostMapping("/search") // z url
//    @ResponseBody
//    public String saveEmail(@ModelAttribute("emails") @RequestParam Collection<Email> email) {
//        model.addAttribute("email", email);
//        for (Email em : emailCollection) {
//            this.emailRepository.save(em);
//        }
//            this.emailRepository.saveAll(email);
//        return "zapisano do bazy";
//        return "redirect:/list";
//    }

    @RequestMapping("/delete/{id}")
    public String deleteEmail(@PathVariable Long id) {
        emailRepository.deleteById(id);
        return "redirect:/list";
    }
}
