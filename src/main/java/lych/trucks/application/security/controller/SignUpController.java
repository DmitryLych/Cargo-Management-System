package lych.trucks.application.security.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.dto.UserDTO;
import lych.trucks.application.security.model.User;
import lych.trucks.application.security.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Sing up controller.
 */
@RequestMapping(value = "/cargo/v1/signUp")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignUpController {

    private final DozerBeanMapper dozerBeanMapper;

    private final UserService userService;

    /**
     * Method for sign up.
     * @param userDTO a user dto.
     * @return created user.
     */
    @PostMapping
    public ResponseEntity createUser(@RequestBody final UserDTO userDTO) {

        final User userToSave = dozerBeanMapper.map(userDTO, User.class);

        final User userToResponse = userService.createUser(userToSave);

        final UserDTO response = dozerBeanMapper.map(userToResponse, UserDTO.class);

        return ResponseEntity.status(CREATED).body(response);
    }
}
