package dao.interfaces;

import dao.DAO;
import entity.User;

import java.util.Optional;

public interface UserDAO extends DAO<User> {

    Optional<User> findByEmail(String email) throws Exception;
}
