# Spring Resource Server + Keycloak integration demo

## What is Keycloak

[Keycloak](https://www.keycloak.org/) is an open source OpenID Certified identity and access
management (IAM) solution.
Detailed information about Keycloak can be found on product's official page:

* [Keycloak](https://www.keycloak.org/)
* [Guides](https://www.keycloak.org/guides)
* [Documentation](https://www.keycloak.org/documentation)
* [Community](https://www.keycloak.org/community)

## Prerequisites

For setting up a local environment you will need:

* JDK 17+
* Maven 3+
* IDE of your choice (IntelliJ was used in this demo)
* Docker
* Web browser
* Postman or any other tool for issuing HTTP requests (curl, IDE plugin etc.)

## Setting up local environment

### Running local instance of Keycloak

Running local instance of Keycloak is as easy as issuing one simple command for starting a Docker
container:

```shell
 docker run -p 8088:8080 --name keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.0.2 start-dev
```

Docker will start up a container named `keycloak` with a demo version (limited set of options) of
Keycloak server running on port 8088 with preconfigured user `admin` and password `admin`.
To open the application navigate to http://localhost:8088/admin and enter credentials (see above)
when prompted.

### Creating a new realm

To create a new realm click on the dropdown menu with preselected item `master` in the top left
corner of the main page.

![Create a new realm](images%2FKeycloak_00_Create_a_new_realm.jpg)

Type `dashboard-api-realm` in new realm name field.

### Creating a new client

To create a new client (the actual application that is going to access protected resources) navigate
to `Clients` menu on the left menu bar and then click a blue button `Create client`.

![Create a new client](images%2FKeycloak_01_Create_a_new_client.jpg)

Set up the values as shown below:

![AEM client](images%2FKeycloak_02_AEM_Client.jpg)

Client credentials can be found on `Credentials` tab:

![Client credentials](images%2FKeycloak_03_AEM_client_credentials.jpg)

### Enabling refresh tokens functionality

By default, refresh token functionality is disabled as it is an option in OAuth2. To enable refresh
tokens navigate to `Clients` menu, choose `Advanced` tab and switch the toggle to `On`:

![Refresh token enablement](images%2FKeycloak_04_Clients_menu_-_Advanced_tab_-_Refresh_token_option.jpg)

### Getting information about authorization server endpoint configuration

Navigate to `Realm settings` menu, choose `General` tab:

![Endpoints config](images%2FKeycloak_05_realm_settings_link.jpg)

Click
on [OpenID Endpoint Configuration](http://localhost:8088/realms/dashboard-api-realm/.well-known/openid-configuration)

You will need token endpoint address later on.

![Endpoints config](images%2FKeycloak_06_well_known_openid_configuration.jpg)

### Creating a resource server

Let's create simple web app based on Spring Boot. Add dependencies for WEB, Security and OAuth2
Resource Server. Configure application.yml as following:

![application.yml](images%2FKeycloak_07_application.yml.jpg)

Create a configuration class:

![security config](images%2FKeycloak_08_SecurityConfig_class.jpg)

Implement a simple controller:

![security config](images%2FKeycloak_09_SomeController_class.jpg)

### Getting a protected resource

Issue `POST` request with request body `x-www-url-encoded` and keys:

* `grant_type=client_credentials`
* `client_id=aem_client`    (or the one you specified during client creation)
* `client_secret=[paste your client secret here]`
* `URI = http://localhost:8088/realms/dashboard-api-realm/protocol/openid-connect/token`

![security config](images%2FKeycloak_10_Get_new_access_token.jpg)

Issue `GET` request to protected resource http://localhost:8080/some/user with `Authorization`
header equal to `Bearer` + space + the access token value from the previous POST request.

![security config](images%2FKeycloak_11_Call_resource_server.jpg)
