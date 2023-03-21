package edu.ntnu.idatt2105.g6.backend.service;

import edu.ntnu.idatt2105.g6.backend.dto.users.UserDTO;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationRequest;
import edu.ntnu.idatt2105.g6.backend.security.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(UserDTO userDTO);
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
