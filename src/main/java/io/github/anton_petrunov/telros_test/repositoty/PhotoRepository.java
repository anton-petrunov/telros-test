package io.github.anton_petrunov.telros_test.repositoty;

import io.github.anton_petrunov.telros_test.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Photo getPhotoByUserIdAndId(Integer userId, Integer id);

    Set<Photo> getPhotosByUserId(Integer userId);

    @Transactional
    @Modifying
    void deleteByUserIdAndId(Integer userId, Integer id);
}
