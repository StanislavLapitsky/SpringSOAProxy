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

###Parts and components
Here you can find the modules:
* core - all the main logic classes are there.
* common - contract declaration, interfaces and DTO to define simplest SOA application.
* user-service - service which implements UserController and 
uses independent ProjectController to express how interaction works.
* project-service - service which implements ProjectController (to be used from the 
user-service).
* soa-integration - represents how multiple module can work together and has some tests
for the common logic.
