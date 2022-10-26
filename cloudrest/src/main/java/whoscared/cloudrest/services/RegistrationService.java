package whoscared.cloudrest.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import whoscared.cloudrest.models.Person;
import whoscared.cloudrest.repositories.PersonRepository;

@Service
public class RegistrationService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registration (Person newPerson){
        newPerson.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        personRepository.save(newPerson);
    }
}
