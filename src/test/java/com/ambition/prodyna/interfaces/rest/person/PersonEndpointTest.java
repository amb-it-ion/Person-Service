package com.ambition.prodyna.interfaces.rest.person;

import java.util.UUID;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Tags;

@RunWith(ArquillianCucumber.class)
@Features("features/person.feature")
@Tags("@whitebox")
public class PersonEndpointTest {

    public static String SERVICE_URL = "http://localhost:8080/contest";

    @Deployment()
    public static WebArchive createDeployment() {
	return ShrinkWrap.create(WebArchive.class, "person.war")
		.addPackages(true, "com.ambition.prodyna")
		.addAsManifestResource(
			new StringAsset("<alternatives><class>com.ambition.prodyna.infrastructure.persistence.person.PersonRepositoryStub</class></alternatives>"), "beans.xml");
    }

    private PersonRest person;
    private final RequestSpecification spec = RestAssured.with();

    @Given("^I create a new person with name '(.*)' and random UUID$")
    public void create(final String name) {
	this.person = new PersonRest();
	this.person.setName(name);
	this.person.setUuid(UUID.randomUUID().toString());
	this.spec.given().body(this.person);
    }

    @When("^I post to '(.*)'$")
    public void get(final String uri) {
	this.spec.when().post(uri);
    }

    @Then("^I should get code '(.*)'$")
    public void res(final String expected) {
	this.spec.expect().statusCode(Integer.parseInt(expected)).log().all();
    }

}
