package bookStore.Mapper;

import bookStore.dto.UserDto;
import bookStore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserDto user) {
        return new User(user.getUsername(), user.getPassword(), user.getRole());
    }

    public UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
