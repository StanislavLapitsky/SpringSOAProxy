# SpringSOAProxy

The project tries to simplify SOA application's services interaction. It could be 
useful for Spring based REST web services. 

SOA application logic is split into multiple independent services. The services must call 
each other but they widely distributed between multiple hosts. Some of them can be hosted 
on the same server/node. Another services are hosted separately. During business logic 
implementation a developer need to call one service from another. It could be done by 
calling e.g. Spring's RestTemplate. The calls implementing could be really time consuming.
The SpringSOAProxy hides the calling logic and allows to work with pure java interfaces 
(fixed contract).

### Parts and components
Here you can find the modules:
* **core** - all the main logic classes are there. The jar to be used as a library.
* **common** - contract declaration, interfaces and DTO to define simplest SOA application.
* **user-service** - service which implements UserController and 
uses independent ProjectController to express how interaction works.
* **project-service** - service which implements ProjectController (to be used from the 
user-service).
* **soa-integration** - represents how multiple module can work together and has some tests
for the common logic.

### How it works
On spring application start *@ProxyableScan* annotation is detected. It scans specified 
base packages for interfaces annotated with *@Proxyable*. For each of the found interfaces
first check whether there is a bean implementing the interface. If there is no such bean a 
Proxy instance is created for the Controller.<br /> 
The Proxy instance keeps an URL for the 
 controller adn RestTemplate based invoker. On the Proxy call invoker marshalls call 
 arguments, calls remote service (RestTemplate is used), unmarshalls the response and 
 returns the results DTO to the caller. 

### How to use the library
Actually you need just **core.jar** module added to a project where you need the SOA Proxy.
1. Define a **contract**.<br />
It means services API - controller interfaces must be defined as well as DTO (Data Transfer 
Objects) classes. The contract is a common module available for all the rest modules which 
really implement the contract (has the interfaces implementation).<br />
The interfaces are annotated with Spring REST annotations - *@RequestMapping*, *@RequestParam* 
etc. to define REST API. The same API is used when the interfaces are called remotely. <br />
The interfaces should be annotated as *@Proxyable* to be detected if you want to *@Autowire* 
them. Their proxies still can be created from ControllerFactory.getController(...) even 
without the annotation.
1. Define two (or more) business logic modules to be deployed on separate nodes 
(servers or just separate JVM instances). Each of the modules depends on 
the **contract** module and implements one or more interfaces declared in the contract.
1. If a Service1 module needs to access another module we just *@Autowire* the required 
controller and call the controller's methods. For example:<br />
```java
    @Service
    public class Service1 {
        @Autowired
        private Service2Controller service2;
        
        void callService1Controller() {
            Service2ResultDTO result = service2.call(<parameters>);
        }
    }
```
To properly initialize the Service2Controller there should be properly defined URL  
in a .properties file. For example:<br />
```
# URLs for the Controller interface's canonical class name.
com.contract.service.Service2Controller=http://localhost:8088/MyContextPath/
```
**IMPORTANT!** To enable the contract interfaces marked with *@Proxyable* annotation it 
is necessary to add @ProxyableScan annotation to your SpringBootApplication
```
@ProxyableScan("com.contract.service")
public class Service1WebApplication {
...
}