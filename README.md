# ravindasenarath-webflux-non-edge-service

To build native image
 - Set JDK to [GraalVM](https://www.graalvm.org/)
 ```console
 ./mvn -Pnative clean package -DskipTests
 ```
 
Run native image
```console
./target/vehicle-service
```
