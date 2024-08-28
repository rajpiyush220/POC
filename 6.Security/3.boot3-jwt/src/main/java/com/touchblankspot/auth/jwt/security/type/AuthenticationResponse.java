package com.touchblankspot.auth.jwt.security.type;

import java.io.Serializable;

public record AuthenticationResponse(String token) implements Serializable {}
