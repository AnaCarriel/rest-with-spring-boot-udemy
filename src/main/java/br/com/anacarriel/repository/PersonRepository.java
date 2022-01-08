package br.com.anacarriel.repository;

import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("UPDATE Person p SET p.enabled =:enabled WHERE p.id =:id")
    void disablePersons(@Param("id") Long id, @Param("enabled") Boolean enabled);

}
