# CRUD Service implementation

Requires
* Maven 2
* Free port 8080
* Some disk space
* Patience during first install

Start JBoss with 
```mvn -Pwildfly package cargo:run

Setup user "user" for application realm (default) with
```<installation>/target/cargo/installs/wildfly-8.2.0.Final/wildfly-8.2.0.Final/bin/add-user

Access API at
```http://localhost:8080/person-service/

Login at 
```http://localhost:8080/person-service/login.html

Access monitoring at
```http://localhost:8080/person-service/moskito-inspect/

Build documentation at 
`target/site/jqassistant-html with
with
```mvn site

Run jQAssistant with
```mvn jqassistant:server

Review at
```http://localhost:7474/

Query for 
```MATCH (t:Type)-[:DECLARES]->(m:Method) RETURN t.fqn AS Type, count(t) AS DeclaredMethods ORDER BY DeclaredMethods DESC LIMIT 20
