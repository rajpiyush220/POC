package com.touchblankspot.secret.springvault;

import com.touchblankspot.secret.springvault.config.VaultConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {

    @NonNull
    private final VaultConfig vaultConfig;

    @GetMapping(value = "/test")
    public String getVaultData() {
        return String.format("Data from vault : %s", vaultConfig);
    }
}
