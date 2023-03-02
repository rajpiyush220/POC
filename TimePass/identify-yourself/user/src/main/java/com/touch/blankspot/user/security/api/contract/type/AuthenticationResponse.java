package com.touch.blankspot.user.security.api.contract.type;

import java.io.Serializable;

public record AuthenticationResponse(String token) implements Serializable {}
