package br.com.anacarriel.services;

import br.com.anacarriel.converter.DozerConverter;
import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.data.vo.v1.PersonVO;
import br.com.anacarriel.exception.ResourceNotFoundException;
import br.com.anacarriel.repository.PersonRepository;
import br.com.anacarriel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //serve para que o spring cuide da injeção de dependencia da classe, não necessita que eu instancie a classe
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUserName(username);
        if (user != null){
            return  user;
        }else {
            throw new UsernameNotFoundException("username" + username + "not found");
        }
    }
}