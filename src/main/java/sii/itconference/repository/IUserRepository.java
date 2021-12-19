package sii.itconference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sii.itconference.model.User;

import java.util.List;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAll();

    boolean existsUserByUsername(String username);
}