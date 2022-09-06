# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [Vault Client Quick Start](https://docs.spring.io/spring-cloud-vault/docs/current/reference/html/#client-side-usage)

### Guides

### Vault Setup (I did this on windows because it is being bit tricky to set it up in ubuntu)
#### Steps to setup vault (Document needs to refactor and updated )
* [Vault Download Link](https://www.vaultproject.io/downloads)
* Copy file at [Vault Config](/spring-vault/vaultconfig.hcl) at the same location as vault.exe
* Start vault server using below command 
  ```vault server -config ./vaultconfig.hcl```
* Left it running and open another command prompt to run below command
  1. ```set VAULT_ADDR=http://localhost:8200 [Keep the same port defined in config file]```
  2. ```set VAULT_SKIP_VERIFY=true # Not recomonded for prod```
  3. ```vault operator init```
* Above command will generate some key and token like below, please keep it safe
  ```
    Unseal Key 1: W6pi/vcJarZirLPfogfuaDxJbhTntMFCv+c81TmI0sKm
    Unseal Key 2: SdfKQ7Z1zuFvv8V5Gv1SonDJOgyNzsGor/f92Naq2dlK
    Unseal Key 3: mWcbmIXdpzcDh1ehchYaqedS3eFFofOIZ63628Db3/tm
    Unseal Key 4: M26bzvgfOmoqKm/bUbBozUkQEvHIG0BuYpMHwDwpwzVH
    Unseal Key 5: B+jKNb5DHfzoEp0l66MLkewIHdt9GYPJKXYa/uObtitg

    Initial Root Token: hvs.rhFwCWNomlCek24Qe57N5uUD
  ```
* Run set of below command to proceed further
  1. ```set VAULT_TOKEN=<above generated token>```
  2. ```vault status```
  3. Its time to unseal vault because above command will show vault is sealed
  4. ```vault operator unseal <any 3 key generate by init command>```
  5. Run ```vault status``` to check whether vault is unsealed or not
  6. Run ```vault secrets enable -path=secret/ kv   ``` to enable key value store with named secret
  7. Run ```vault kv put secret/<app_name> key=value key1=value``` to add key
     * <app_name> --> This will be used to access secrets and same app name will be used as app name in client
     * key -> key name could be prefix.keyname and same prefix can be used as prefix for ConfigProperties annotation
  8. This ```vault kv get secret/<app_name>``` command can be used to get values from vault

### Client setup
* Create a spring boot project with below dependecies
   1. ```spring-boot-starter-web```
   2. ```spring-cloud-starter-vault-config```
* Update application.properties/yaml with following keys
    ```yaml
    spring:
      application:
        name: spring-vault-sample
        # name should be same as name set in vault kv put secret/<app_name>
      config:
       import: vault://
      cloud:
        vault:
          host: localhost
          # host should be same as host set as VAULT_ADDR key
          port: 8200
          # host should be same as host set as VAULT_ADDR port
          scheme: http
        kv:
         enabled: true
      token: hvs.rhFwCWNomlCek24Qe57N5uUD
      # token would be same as token generate during init process
    ```

* Take a look at project source code to understand remaining 