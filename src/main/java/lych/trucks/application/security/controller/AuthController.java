package lych.trucks.application.security.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.dto.TokenResponse;
import lych.trucks.application.security.dto.UserDTO;
import lych.trucks.application.security.model.User;
import lych.trucks.application.security.service.jwt.TokenService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lych.trucks.application.security.dto.TokenResponse.aTokenResponse;

/**
 * Auth controller.
 */
@RequestMapping(value = "/cargo/v1/auth")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final TokenService tokenService;

    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Method for auth.
     *
     * @param userDTO a user dto.
     * @return message about success.
     */
    @PostMapping
    public ResponseEntity authenticate(@RequestBody final UserDTO userDTO) {

        final User user = dozerBeanMapper.map(userDTO, User.class);

        final String token = tokenService.getToken(user);

        final TokenResponse response = aTokenResponse(token);

        return ResponseEntity.ok().header("access-token", response.getToken()).body("Successfully");
    }
}
