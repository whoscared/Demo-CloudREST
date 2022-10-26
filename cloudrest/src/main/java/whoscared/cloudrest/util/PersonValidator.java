package whoscared.cloudrest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import whoscared.cloudrest.models.Person;
import whoscared.cloudrest.services.PersonService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;
        String username = person.getUsername();
        Optional<Person> findPerson = personService.getPersonByUsername(username);
        if (findPerson.isPresent()){
            errors.rejectValue("username","", "User with this username already have");
        }
    }
}
