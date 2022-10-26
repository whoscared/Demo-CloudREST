package whoscared.cloudrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whoscared.cloudrest.models.Person;
import whoscared.cloudrest.repositories.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {

    private final  PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonByUsername(String username){
        return personRepository.getPersonByUsername(username);
    }
}
