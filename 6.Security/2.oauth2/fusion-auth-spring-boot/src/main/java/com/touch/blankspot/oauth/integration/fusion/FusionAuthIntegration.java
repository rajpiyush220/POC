package com.touch.blankspot.oauth.integration.fusion;

import com.inversoft.error.Errors;
import com.inversoft.rest.ClientResponse;
import com.touch.blankspot.oauth.web.service.type.AppLoginRequest;
import com.touch.blankspot.oauth.web.service.type.AppLoginResponse;
import com.touch.blankspot.oauth.web.service.type.AppRegistrationRequest;
import com.touch.blankspot.oauth.web.service.type.AppRoleRequest;
import com.touch.blankspot.oauth.web.service.type.AppUpdatePasswordRequest;
import io.fusionauth.client.FusionAuthClient;
import io.fusionauth.domain.ApplicationRole;
import io.fusionauth.domain.User;
import io.fusionauth.domain.UserRegistration;
import io.fusionauth.domain.api.ApplicationRequest;
import io.fusionauth.domain.api.ApplicationResponse;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.LoginResponse;
import io.fusionauth.domain.api.UserResponse;
import io.fusionauth.domain.api.user.ChangePasswordRequest;
import io.fusionauth.domain.api.user.RegistrationRequest;
import io.fusionauth.domain.api.user.RegistrationResponse;
import java.util.Locale;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class FusionAuthIntegration {

  @NonNull private final FusionAuthClient fusionAuthClient;

  @Value("${app.fusionauth.applicationId}")
  private UUID appId;

  public User registerUser(AppRegistrationRequest appRegistrationRequest) {
    User user =
        new User()
            .with(u -> u.email = appRegistrationRequest.getEmail())
            .with(u -> u.password = appRegistrationRequest.getPassword());
    UserRegistration registration = new UserRegistration();
    registration.applicationId = appId;
    registration.username = appRegistrationRequest.getEmail();
    registration.preferredLanguages.add(Locale.ENGLISH);
    registration.roles.add("APP_USER");
    registration.data.put("createdBy", "Sample App");
    RegistrationRequest request = new RegistrationRequest(user, registration);
    ClientResponse<RegistrationResponse, Errors> response =
        fusionAuthClient.register(null, request);
    if (response.wasSuccessful()) {
      return response.successResponse.user;
    } else {
      log.error("Unable to register User {}", response.errorResponse);
    }
    return null;
  }

  public User getUserByEmail(String email) {
    ClientResponse<UserResponse, Errors> response = fusionAuthClient.retrieveUserByEmail(email);
    if (response.wasSuccessful()) {
      return response.successResponse.user;
    } else {
      handleError(response);
    }
    return null;
  }

  public User getUserById(String id) {
    ClientResponse<UserResponse, Errors> response =
        fusionAuthClient.retrieveUser(UUID.fromString(id));
    if (response.wasSuccessful()) {
      return response.successResponse.user;
    } else {
      handleError(response);
    }
    return null;
  }

  public Boolean updatePassword(AppUpdatePasswordRequest passwordRequest) {
    ClientResponse<Void, Errors> response =
        fusionAuthClient.changePasswordByIdentity(
            new ChangePasswordRequest(
                passwordRequest.getEmail(),
                passwordRequest.getOldPassword(),
                passwordRequest.getNewPassword()));
    if (response.wasSuccessful()) {
      return true;
    } else {
      handleError(response);
    }
    return false;
  }

  public String performLogout(String refreshToken) {
    return fusionAuthClient.logout(false, refreshToken).wasSuccessful()
        ? "Logout successfully."
        : "";
  }

  public AppLoginResponse performLogin(AppLoginRequest loginRequest) {
    ClientResponse<LoginResponse, Errors> response =
        fusionAuthClient.login(
            new LoginRequest(appId, loginRequest.getId(), loginRequest.getCredential()));
    if (response.wasSuccessful()) {
      LoginResponse loginResponse = response.successResponse;
      return AppLoginResponse.builder()
          .token(loginResponse.token)
          .refreshToken(loginResponse.refreshToken)
          .user(loginResponse.user)
          .build();
    } else {
      handleError(response);
    }
    return null;
  }

  public boolean deactivateUser(UUID userId) {
    ClientResponse<Void, Errors> response = fusionAuthClient.deactivateUser(userId);
    if (response.wasSuccessful()) {
      return true;
    } else {
      handleError(response);
    }
    return false;
  }

  public User reactivateUser(UUID userId) {
    ClientResponse<UserResponse, Errors> response = fusionAuthClient.reactivateUser(userId);
    if (response.wasSuccessful()) {
      return response.successResponse.user;
    } else {
      handleError(response);
    }
    return null;
  }

  public ApplicationRole createRole(AppRoleRequest roleRequest) {
    ApplicationRole role =
        new ApplicationRole()
            .with(appRole -> appRole.name = roleRequest.getName())
            .with(appRole -> appRole.description = roleRequest.getDescription())
            .with(appRole -> appRole.isSuperRole = roleRequest.isSuperRole())
            .with(appRole -> appRole.isDefault = roleRequest.isDefault());
    ClientResponse<ApplicationResponse, Errors> response =
        fusionAuthClient.createApplicationRole(appId, null, new ApplicationRequest(role));
    if (response.wasSuccessful()) {
      return response.successResponse.role;
    } else {
      handleError(response);
    }
    return null;
  }

  private void handleError(ClientResponse<?, Errors> response) {
    if (response.errorResponse != null) {
      Errors errors = response.errorResponse;
      log.error("Error occurred {}", errors);
      throw new RuntimeException("Error occurred " + errors);
    } else if (response.exception != null) {
      Exception exception = response.exception;
      log.error(exception.getMessage(), exception);
      throw new RuntimeException(exception.getCause());
    }
  }
}
