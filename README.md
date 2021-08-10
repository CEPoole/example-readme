# example-service-name

Brief description of this microservice and how it fits into its wider service, why it's needed and how it's intended to be used. Try to keep it to one or two lines, if you can't then maybe the microservice is too big?

1. [How to run the service](#how-to-run-the-service)
2. [Testing the service](#testing-the-service)
3. [API Documentation](#api-documentation)
4. [Related services](#related-services)
5. [License](#license)

# How to run the service

To run the service, simple enter `sbt run` from the command line.
This should start the service on **Port 9123**.
If you want to start the service with test endpoints enabled, use the following:
```
sbt "run 9123 -Dapplication.router=testOnlyDoNotUseInAppConf.Routes"
```

In order to start the dependent services, download and set up [Service Manager](#https://github.com/hmrc/service-manager) and use the `EXAMPLE` profile.

```
sm --start EXAMPLE -r
```
You will also need to be running `Mongo 4.0` locally.

To see the user flow and data required to navigate journeys, see the [UI tests](#https://github.com/hmrc/example-ui-tests).

## Testing the service
This service supports two types of testing, unit and integration. In order to run the tests, use the following commands
```
sbt test             <- Unit tests
sbt it:test          <- Integration tests
```
This service also allows you to run a coverage report for the tests. Running the command below will produce a JSON, XML and HTML report; we recommend opening the HTML report in a web browser.
>This is configured to fail if statement coverage falls below **95%**
```
sbt clean coverage test it:test coverageReport
```

#  API Documentation

| Name | Method | Path | Description |
|:---- |:------ |:---- |:----------- |
|[Get user details](#get-user-details) | GET | [/example-service/user/:identifier](#get-user-details) | Get all info for a user with a given identifier
|[Update user details](#update-user-details) |POST | [/example-service/user/:identifier](#update-user-details) | Provide new details for a user with a given identifier. User must already exist within the system.
|[Delete user details](#delete-user-details) |DELETE | [/example-service/user/:identifier](#delete-user-details) | Remove a specified user from the database

All responses from this service may return standard HTTP codes from this list.
Descriptions of successful request bodies are listed below.
|Code    |Description |
|:-------|:-----------|
|200     |[Ok](https://httpstatuses.com/200)|
|400     |[Bad Request](https://httpstatuses.com/400)|
|401     |[Unauthorized](https://httpstatuses.com/401)|
|404     |[Not Found](https://httpstatuses.com/404)|
|409     |[Conflict](https://httpstatuses.com/409)|
|500     |[Internal Server Error](https://httpstatuses.com/500)|

  ---
## Get user details
Get all data associated with a given user identifier.

#### Response model
If the provided identifier matches an existing record:
```$xslt
200 (OK)
{
  "name": "bob",
  "age": "42",
  "location": "UNKNOWN"
}
```
See [UserInfo](test/models/UserInfoSpec.scala) for implementation.


## Update user details

Update all data associated with a given user identifier. A user's *name* **cannot** be updated via this endpoint because the business uses this data item as a primary key.

#### Request model
Some or all of this data must be provided to update a record:
```$xslt
{
  "age": "42",
  "location": "UNKNOWN"
}
```
See [UserInfo](test/models/UserInfoSpec.scala) for implementation.

#### Response model
If the provided data successfully updates a record:
```$xslt
200 (OK)
{
  "status": "Successfully updated record: {identifier}"
}
```

## Delete user details

Delete all data associated with a given user identifier.
#### Response model
If the provided data successfully deletes a record:
```$xslt
200 (OK)
{
  "status": "Successfully deleted record: {identifier}"
}
```

# Related services

This is a list of services that support this microservice's functionality.
> **Note:** Some of these services may be in private repositories. This is because they may contain sensitive data or behaviour that must not be shared outside of the HMRC organisation.
 
|Service Name        |Link                                              |
|:-------------------|:-------------------------------------------------|
|Backend             |https://github.com/hmrc/example-backend           |
|Stub                |https://github.com/hmrc/example-stub              |
|Prototype           |https://github.com/hmrc/example-prototype         | 
|Journey tests       |https://github.com/hmrc/example-ui-tests          |
|Performance tests   |https://github.com/hmrc/example-performance-tests |
|Prototype           |https://github.com/hmrc/example-prototype         |                        

## License

This code is open source software licensed under the **Apache 2.0 License**
