package pl.coderslab.app.controller;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.entity.Email;
import pl.coderslab.app.email.EmailExtractor;
import pl.coderslab.app.entity.Url;
import pl.coderslab.repository.EmailRepository;
import pl.coderslab.repository.UrlRepository;

import java.util.*;
import java.util.stream.Collectors;

// logger
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
//@RequestMapping("/email")
public class UrlController {
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;
//    private final Validator validator;

    public UrlController(EmailRepository emailRepository, UrlRepository urlRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
//        this.validator = validator;
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
//    @ResponseBody
    public String listEmail(Model model) {
        List<Email> emails = emailRepository.findAllByEmails();
//        List<Email> emails = emailRepository.findAll();
        model.addAttribute("emails", emails);
//        emails.forEach(System.out::println);
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
        model.asMap().forEach((k, v) -> logger.debug(k + " : " + v));
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
//    public String searchListEmail(Model model, @Valid @RequestParam String url, BindingResult result) {
    public String searchListEmail(Model model, @Valid @RequestParam String url) {
//       walidacja
//        if (result.hasErrors()) {
//            return "/form";
//        }
        EmailExtractor emailExtractor = new EmailExtractor();
        Set<String> emails = emailExtractor.searchEmails(url);
        model.addAttribute("url", url);
        model.addAttribute("emails", emails);
        List<Email> emailList = emails.stream().map(Email::new).collect(Collectors.toList());
        this.emailRepository.saveAll(emailList);

        Url url1 = new Url(url, emailList);
        for (Email em : emailList) {
            em.addUrl(url1);
            this.urlRepository.save(url1);
        }
        return "search";
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmail(@PathVariable Long id) {
        emailRepository.deleteById(id);
        return "redirect:/list";
    }
}
