# Spring DeFrame-API [![Build Status](https://travis-ci.org/spring-projects/spring-DeFrame.png?branch=master)](https://travis-ci.org/spring-projects/spring-DeFrame/)

## Understanding the Spring DeFrame application with a few diagrams
<a href="https://speakerdeck.com/michaelisvy/spring-DeFrame-sample-application">See the presentation here</a>

## Running DeFrame locally
```
git clone https://github.com/spring-projects/spring-DeFrame.git
cd spring-DeFrame
./mvnw spring-boot:run
```

You can then access DeFrame here: http://localhost:8080/

<img width="1042" alt="DeFrame-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

## In case you find a bug/suggested improvement for Spring DeFrame
Our issue tracker is available here: https://github.com/spring-projects/spring-DeFrame/issues


## Database configuration

In its default configuration, DeFrame uses an in-memory database (HSQLDB) which
gets populated at startup with data. A similar setup is provided for MySql in case a persistent database configuration is needed.
Note that whenever the database type is changed, the data-access.properties file needs to be updated and the mysql-connector-java artifact from the pom.xml needs to be uncommented.

You could start a MySql database with docker:

```
docker run -e MYSQL_ROOT_PASSWORD=DeFrame -e MYSQL_DATABASE=DeFrame -p 3306:3306 mysql:5.7.8
```

## Working with DeFrame in Eclipse/STS

### prerequisites
The following items should be installed in your system:
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git command line tool (https://help.github.com/articles/set-up-git)
* Eclipse with the m2e plugin (m2e is installed by default when using the STS (http://www.springsource.org/sts) distribution of Eclipse)

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/


### Steps:

1) In the command line
```
git clone https://github.com/spring-projects/spring-DeFrame.git
```
2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven project
```


## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [DeFrameApplication](https://github.com/spring-projects/spring-DeFrame/blob/master/src/main/java/org/springframework/samples/DeFrame/DeFrameApplication.java) |
|Properties Files | [application.properties](https://github.com/spring-projects/spring-DeFrame/blob/master/src/main/resources) |
|Caching | [CacheConfig](https://github.com/spring-projects/spring-DeFrame/blob/master/src/main/java/org/springframework/samples/DeFrame/system/CacheConfig.java) |

## Interesting Spring DeFrame branches and forks

The Spring DeFrame master branch in the main
[spring-projects](https://github.com/spring-projects/spring-DeFrame)
GitHub org is the "canonical" implementation, currently based on
Spring Boot and Thymeleaf. There are quite a few forks in a special
GitHub org [spring-DeFrame](https://github.com/spring-DeFrame). If
you have a special interest in a different technology stack that could
be used to implement the Pet Clinic then please join the community
there.

| Link                               | Main technologies |
|------------------------------------|-------------------|
| [spring-framework-DeFrame][]     | Spring Framework XML configuration, JSP pages, 3 persistence layers: JDBC, JPA and Spring Data JPA |
| [javaconfig branch][]              | Same frameworks as the [spring-framework-DeFrame][] but with Java Configuration instead of XML |
| [spring-DeFrame-angularjs][]     | AngularJS 1.x, Spring Boot and Spring Data JPA |
| [spring-DeFrame-angular][]       | Angular 4 front-end of the DeFrame REST API [spring-DeFrame-rest][] |
| [spring-DeFrame-microservices][] | Distributed version of Spring DeFrame built with Spring Cloud |
| [spring-DeFrame-reactjs][]       | ReactJS (with TypeScript) and Spring Boot |
| [spring-DeFrame-graphql][]       | GraphQL version based on React Appolo, TypeScript and GraphQL Spring boot starter |
| [spring-DeFrame-kotlin][]        | Kotlin version of [spring-DeFrame][] |
| [spring-DeFrame-rest][]          | Backend REST API |


## Interaction with other open source projects

One of the best parts about working on the Spring DeFrame application is that we have the opportunity to work in direct contact with many Open Source projects. We found some bugs/suggested improvements on various topics such as Spring, Spring Data, Bean Validation and even Eclipse! In many cases, they've been fixed/implemented in just a few days.
Here is a list of them:

| Name | Issue |
|------|-------|
| Spring JDBC: simplify usage of NamedParameterJdbcTemplate | [SPR-10256](https://jira.springsource.org/browse/SPR-10256) and [SPR-10257](https://jira.springsource.org/browse/SPR-10257) |
| Bean Validation / Hibernate Validator: simplify Maven dependencies and backward compatibility |[HV-790](https://hibernate.atlassian.net/browse/HV-790) and [HV-792](https://hibernate.atlassian.net/browse/HV-792) |
| Spring Data: provide more flexibility when working with JPQL queries | [DATAJPA-292](https://jira.springsource.org/browse/DATAJPA-292) |


# Contributing

The [issue tracker](https://github.com/spring-projects/spring-DeFrame/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <http://editorconfig.org>. If you have not previously done so, please fill out and submit the https://cla.pivotal.io/sign/spring[Contributor License Agreement].

# License

The Spring DeFrame sample application is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).

[spring-DeFrame]: https://github.com/spring-projects/spring-DeFrame
[spring-framework-DeFrame]: https://github.com/spring-DeFrame/spring-framework-DeFrame
[spring-DeFrame-angularjs]: https://github.com/spring-DeFrame/spring-DeFrame-angularjs
[javaconfig branch]: https://github.com/spring-DeFrame/spring-framework-DeFrame/tree/javaconfig
[spring-DeFrame-angular]: https://github.com/spring-DeFrame/spring-DeFrame-angular
[spring-DeFrame-microservices]: https://github.com/spring-DeFrame/spring-DeFrame-microservices
[spring-DeFrame-reactjs]: https://github.com/spring-DeFrame/spring-DeFrame-reactjs
[spring-DeFrame-graphql]: https://github.com/spring-DeFrame/spring-DeFrame-graphql
[spring-DeFrame-kotlin]: https://github.com/spring-DeFrame/spring-DeFrame-kotlin
