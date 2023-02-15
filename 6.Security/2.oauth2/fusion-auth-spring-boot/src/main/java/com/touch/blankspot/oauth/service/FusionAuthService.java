package com.touch.blankspot.oauth.service;

import com.touch.blankspot.oauth.integration.fiegn.client.FusionAuthFeignClient;
import com.touch.blankspot.oauth.integration.fusion.FusionAuthIntegration;
import com.touch.blankspot.oauth.web.service.type.AppLoginRequest;
import com.touch.blankspot.oauth.web.service.type.AppLoginResponse;
import com.touch.blankspot.oauth.web.service.type.AppRegistrationRequest;
import com.touch.blankspot.oauth.web.service.type.AppRoleRequest;
import com.touch.blankspot.oauth.web.service.type.AppUpdatePasswordRequest;
import io.fusionauth.domain.ApplicationRole;
import io.fusionauth.domain.User;
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
public class FusionAuthService {

  @Value("${app.fusionauth.applicationId}")
  private String clientId;

  @NonNull private final FusionAuthIntegration fusionAuthIntegration;

  @NonNull private final FusionAuthFeignClient fusionAuthFeignClient;

  public User getUserByEmail(String email) {
    return fusionAuthIntegration.getUserByEmail(email);
  }

  public User register(AppRegistrationRequest request) {
    return fusionAuthIntegration.registerUser(request);
  }

  public AppLoginResponse performLogin(AppLoginRequest loginRequest) {
    return fusionAuthIntegration.performLogin(loginRequest);
  }

  public String performLogout() {
    try {
      fusionAuthFeignClient.logout(clientId);
      return "Success";
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      return "failure";
    }
  }

  public Boolean updatePassword(AppUpdatePasswordRequest passwordRequest) {
    return fusionAuthIntegration.updatePassword(passwordRequest);
  }

  public User getUserById(String id) {
    return fusionAuthIntegration.getUserById(id);
  }

  public boolean deactivateUser(UUID userId) {
    return fusionAuthIntegration.deactivateUser(userId);
  }

  public User reactivateUser(UUID userId) {
    return fusionAuthIntegration.reactivateUser(userId);
  }

  public ApplicationRole createRole(AppRoleRequest roleRequest) {
    return fusionAuthIntegration.createRole(roleRequest);
  }
}
