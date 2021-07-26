package pl.coderslab.app.url;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.EmailRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
//@RequestMapping("/email")
public class EmailController {
    private final EmailRepository emailRepository;
    //    private final EmailService emailService;
    //    private final EmailExtractor emailExtractor;

//    public EmailController(EmailRepository emailRepository, EmailService emailService) {
//        this.emailRepository = emailRepository;
//        this.emailService = emailService;
//    }

//
//    public EmailController(EmailRepository emailRepository, EmailExtractor emailExtractor) {
//        this.emailRepository = emailRepository;
//        this.emailExtractor = emailExtractor;
//    }

    public EmailController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @GetMapping("/")
    public String mainForm() {
        return "mainform";
    }

    @RequestMapping("/list") //z db
    public String listEmail(Model model) {
        model.addAttribute("emails", emailRepository.findAll());
        return "list";
    }

    @RequestMapping("/search")
    public String searchListEmail(Model model) throws IOException {
        EmailExtractor emailExtractor = new EmailExtractor();
        Set<String> emails = emailExtractor.searchEmails("https://masab.pl/");
        model.addAttribute("emails", emails);
        List<Email> emailList = emails.stream().map(Email::new).collect(Collectors.toList());
        this.emailRepository.saveAll(emailList);
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
