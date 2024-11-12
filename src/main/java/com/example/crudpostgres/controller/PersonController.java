package com.example.crudpostgres.controller;

import com.example.crudpostgres.model.Person;
import com.example.crudpostgres.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllUsers() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Person createUser(@RequestBody Person user) {
        return personService.createPerson(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateUser(@PathVariable Long id, @RequestBody Person userDetails) {
        return ResponseEntity.ok(personService.updatePerson(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}