package whoscared.cloudrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whoscared.cloudrest.models.Person;
import whoscared.cloudrest.repositories.PersonRepository;
import whoscared.cloudrest.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    final private PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.getPersonByUsername(username);
        if (person.isEmpty()){
            throw new UsernameNotFoundException("User with this username not found");
        }
        return new PersonDetails(person.get());
    }
}
