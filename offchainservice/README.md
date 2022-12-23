# OffChainService

## Requirements
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

### Configuration
You need to configure your contract attributes in application.properties:
- contract address: `voting_factory.contract_address`
- blockchain url: `voting_factory.service_url`
- private key: `voting_factory.private_key`

### Run

```shell
mvn spring-boot:run
```
