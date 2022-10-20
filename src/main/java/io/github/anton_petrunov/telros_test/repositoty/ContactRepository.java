package io.github.anton_petrunov.telros_test.repositoty;

import io.github.anton_petrunov.telros_test.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact getContactByUserIdAndId(Integer userId, Integer id);

    List<Contact> getContactsByUserId(Integer userId);

    @Transactional
    @Modifying
    void deleteByUserIdAndId(Integer userId, Integer id);

}
