package br.com.anacarriel.services;

import br.com.anacarriel.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service //serve para que o spring cuide da injeção de dependencia da classe, não necessita que eu instancie a classe
public class PersonServices {

    private final AtomicLong counter = new AtomicLong(); //vai servir para simular um id do BD, gera sempre um id novo

    public Person create(Person person){
        return person;
    }

    public Person update(Person person){
        return person;
    }

    public void delete(String id){

    }

    public Person findeById(String id){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Ana");
        person.setLastName("Maria");
        person.setAddress("Piraju-SP");
        person.setGender("Female");

        return person;
    }

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<Person>();
        for (int i=0; i<8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name" + i);
        person.setLastName("Last name"  + i);
        person.setAddress("Piraju-SP"  + i);
        person.setGender("Female"  + i);

        return person;
    }

}
