package com.touchblankspot.auth.jwt.springsecurityjwt.security.type;

import java.io.Serializable;

public record AuthenticationResponse(String token) implements Serializable {}
