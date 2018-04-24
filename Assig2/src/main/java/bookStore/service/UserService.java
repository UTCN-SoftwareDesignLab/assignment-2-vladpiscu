package bookStore.service;

import bookStore.dto.UserDto;
import bookStore.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
    User save(UserDto user);
    User update(UserDto user);
    void remove(UserDto user);
    void removeAll();
}
