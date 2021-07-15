package pl.coderslab.app.url;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.repository.EmailRepository;

import java.io.IOException;

@Controller
//@RequestMapping("/email")
public class EmailController {
private final EmailRepository emailRepository;

    public EmailController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @RequestMapping("/")
    public String mainForm(){
        return "mainform";
    }

    @RequestMapping("/list") //z db
    public String listEmail(Model model) {
        model.addAttribute("emails", emailRepository.findAll());
        return "list";
    }

    @GetMapping("/search") // z url
    public String searchListEmail(Model model) throws IOException {
        EmailDao emailDao = new EmailDao();
        model.addAttribute("emails", emailDao.searchEmails("https://bringmore.pl"));
        return "search";
    }



}
