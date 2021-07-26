package pl.coderslab.app.url;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.repository.EmailRepository;

public class EmailConverter implements Converter<String, Email> {

        private EmailRepository emailRepository;
        @Override
        public Email convert(String source) {
            return (Email) emailRepository.findAll();
        }
    }

