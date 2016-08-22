# Contacts_Narvar
Solution to the programming challange: Contacts.

Please clone the code and run the ContactsApplication Spring Boot Application class.
The default persistence is provided by inbuilt H2 database. The application is enabled to run against Postgres database.

The Contacts REST server provides following REST operations:
1. GET /contacts
2. POST /contacts
3. PUT /contacts/{id}
4. GET /search?

The content negotiation format is: application/hal+json

1. GET http://localhost:8484/directory/contacts:
    This endpoint is not protected by authentication. 
    Since Contacts application is populating 2 contacts on startup, the GET call will return 2 entries.
  
  Sample Response:  
  {
  "_embedded": {
    "content": [
      {
        "id": 1,
        "name": "John",
        "email": "john@example.com",
        "profession": "Engineer",
        "_links": {
          "self": {
            "href": "http://localhost:8484/directory/contacts/1"
          }
        }
      },
      {
        "id": 2,
        "name": "Jane",
        "email": "jane@example.com",
        "profession": "Legal Advisor",
        "_links": {
          "self": {
            "href": "http://localhost:8484/directory/contacts/2"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8484/directory/contacts"
    }
  }
}

2. POST http://localhost:8484/directory/contacts:
    This is a protected endpoint. So in a REST client, update Authorization header in request (enter username: "user" and password: "password"). Set Content-Type: application/json.

    Sample payload:
    {
    	"name": "Alex",
    	"email": "alex@example.com",
    	"profession": "Doctor"
    }
    
3. PUT http://localhost:8484/directory/contacts/2:
    This is another protected endpoint. Similar to POST request, update Authorization header in request (enter username: "user" and password: "password"). In case, you have entered these credential already, brower/REST client will cache it for you. Set Content-Type: application/json.

    Sample payload:
    {
    	"name": "Jane",
    	"email": "jane@example.com",
    	"profession": "Librarian"
    }
    
4. GET http://localhost:8484/directory/search?<<attribute1>>=<<value>>[&<<attribute2>>=<<value>>]
    The search end point provide search on combinations of name, email and profession.


In case you want to run against Postgres, enable following properties in build.gradle file:

bootRun {
    def map = [:]
    map["spring.datasource.url"] = "jdbc:postgresql://localhost:5432/contacts"
    map["spring.datasource.username"] = "contacts_admin"
    map["spring.datasource.password"] = "password"
    map["spring.datasource.driver-class-name"] = "org.postgresql.Driver"
    systemProperties = map;
}

Create user "contacts_admin" with password "password" and create a "contacts" database with "contacts_admin" user as owner.
