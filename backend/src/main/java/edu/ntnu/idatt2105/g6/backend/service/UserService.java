package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserUpdateDTO;
import edu.ntnu.idatt2105.g6.backend.exception.UserExistsException;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import edu.ntnu.idatt2105.g6.backend.repo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void updateUser(UserUpdateDTO userUpdateDTO){

            User user = userRepository.findByUsername(userUpdateDTO.username()).orElseThrow();

            Optional<User> newUser = userRepository.findByUsername(userUpdateDTO.newUsername());
            if (newUser.isPresent()) throw new UserExistsException(userUpdateDTO.newUsername());

            user = User
                    .builder()
                    .userId(user.getUserId())
                    .username(userUpdateDTO.newUsername() != null ? userUpdateDTO.username() : user.getUsername())
                    .fullName(userUpdateDTO.fullName() != null ? userUpdateDTO.fullName() : user.getFullName())
                    .email(userUpdateDTO.email() != null ? userUpdateDTO.email() : user.getEmail())
                    .birthDate(userUpdateDTO.birthDate() != null ? userUpdateDTO.birthDate() : user.getBirthDate())
                    .phone(userUpdateDTO.phone() != null ? userUpdateDTO.phone() : user.getPhone())
                    .picture(userUpdateDTO.picture() != null ? userUpdateDTO.picture() : user.getPicture())
                    .role(userUpdateDTO.role() != null ? userUpdateDTO.role() : user.getRole())
                    .build();

            userRepository.save(user);


    }
}
