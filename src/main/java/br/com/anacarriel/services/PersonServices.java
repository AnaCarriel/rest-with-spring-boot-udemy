package br.com.anacarriel.services;

import br.com.anacarriel.converter.DozerConverter;
import br.com.anacarriel.data.vo.PersonVO;
import br.com.anacarriel.exception.ResourceNotFoundException;
import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //serve para que o spring cuide da injeção de dependencia da classe, não necessita que eu instancie a classe
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public Person create(Person person){
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), Person.class);
        return vo;
    }

    public Person update(PersonVO person){
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo =  DozerConverter.parseObject(repository.save(entity), Person.class);
        return vo;
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    public PersonVO findeById(Long id){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Norecords found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }


}
