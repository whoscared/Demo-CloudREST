package whoscared.cloudrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import whoscared.cloudrest.models.Person;
import whoscared.cloudrest.services.RegistrationService;
import whoscared.cloudrest.util.PersonValidator;
import whoscared.cloudrest.util.exceptions.PersonErrorResponse;
import whoscared.cloudrest.util.exceptions.PersonNotRegisteredException;

import javax.validation.Valid;


@RestController
@RequestMapping("/register")
public class RegisterController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    @Autowired
    public RegisterController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }


    @GetMapping()
    public Person registration() {
        return new Person();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> checkData(@RequestBody @Valid Person person,
                                                BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error: bindingResult.getFieldErrors()){
                errorMessage.append(error.getField())
                        .append(" : ")
                        .append(error.getDefaultMessage())
                        .append("\n");
            }
            throw new PersonNotRegisteredException(errorMessage.toString());
        }
        registrationService.registration(person);
        //httpServletResponse.sendRedirect("/register");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException (PersonNotRegisteredException e){
        PersonErrorResponse errorResponse = new PersonErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
