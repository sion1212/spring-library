package spring.library.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.controller.request.UserRequest;
import spring.library.domain.User;
import spring.library.dto.UserDto;
import spring.library.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto save(UserRequest userRequest){
        User user = User.from(userRequest);
        userRepository.save(user);
        UserDto userDto = UserDto.from(user);
        return userDto;
    }

    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(UserDto::from).toList();
        return userDtos;
    }

    @Transactional
    public UserDto update(Long memberId, UserRequest userRequest){
        User userOfIdNumber = userRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        User userForUpdate = User.from(userRequest);
        userOfIdNumber.update(userForUpdate);
        return UserDto.from(userOfIdNumber);
    }

    public void delete(Long memberId){
        userRepository.deleteById(memberId);
    }
}
