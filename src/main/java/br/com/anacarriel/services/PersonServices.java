package br.com.anacarriel.services;

import br.com.anacarriel.converter.DozerConverter;
import br.com.anacarriel.data.vo.v1.PersonVO;
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

    public Person update(PersonVO personVO){
        var entity = repository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

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