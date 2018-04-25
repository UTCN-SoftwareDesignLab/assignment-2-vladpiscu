package bookStore.service;

import bookStore.dto.UserDto;
import bookStore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookStore.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password );
    }

    @Override
    public User save(UserDto user) {
        User u = getUser(user);
        return userRepository.save(u);
    }

    @Override
    public User update(UserDto user) {
        User u = getUser(user);
        u.setId(user.getId());
        return userRepository.save(u);
    }

    @Override
    public void remove(UserDto user) {
        User u = getUser(user);
        userRepository.delete(u);
    }

    @Override
    public void remove(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        userRepository.deleteAll();
    }

    private User getUser(UserDto user) {
        return new User(user.getUsername(), user.getPassword(), user.getRole());
    }
}
