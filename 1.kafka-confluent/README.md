# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

#### Tech Stack
    * Spring boot
    * Spring batch
    * h2 db (Spring req any db to store job description)
    * Spring -kafka
    * [Confluent cloud](https://confluent.cloud/) to host kakfa related infra hosting

#### Project Info
    * It's a sample project which create a topic with specified parition and replicationFactor.
    * Remaining properties kept default 
    * This project has single message producer which produces message periodically after 1 min and 
        at the same time we have receiver wich receives message.
### How to run it
    * In order to run first we need to create free account at confluent cloud and created a cluster
        once cluster is created then generate api key and secret key.
    * Open application.yml and update below properties with the generated one
        1. kakfa.baseUrl(This will be bootstrap server url of the cluster and each cluster wil have unique url)
        2. kafka.securityConfig.apiKey && kafka.securityConfig.secretKey
    * Use any IDE to run it or ./gradlew bootRun(linux), gradlew bootRun (Windows).
