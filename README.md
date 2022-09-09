# ravindasenarath-webflux-non-edge-service

To build native image
 - Set JDK to GraalVM
 - 
 ```console
 ./mvn -Pnative clean package -DskipTests
 ```
 
Run native image
```console
./target/vehicle-service
```
