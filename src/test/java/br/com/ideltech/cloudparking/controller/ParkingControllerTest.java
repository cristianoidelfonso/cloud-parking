package br.com.ideltech.cloudparking.controller;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.com.ideltech.cloudparking.controller.dto.ParkingCreateDTO;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {

  @LocalServerPort
  private static int randomPort;

  @BeforeEach
  public void setup() {
    baseURI = "http://localhost";
    port = 8082;
    basePath = "/";
  }

  @Test
  public void testHello() { 
    // Configurar o path base da API Rest
    // baseURI = "http://localhost";
    port = 8082;
    // basePath = "/";
    
    // Executar um consulta na API Rest
    String mensagem = given()
                        .body("")
                        .contentType(ContentType.JSON)
                      .when()
                        .get("/")
                      .then()
                        .log().all()
                        .extract()
                          .path("mensagem");
    System.out.println("mensagem: " + mensagem);

    // Assertions
    given()
      .body("")
      .contentType(ContentType.JSON)
    .when()
      .get("/")
    .then()
      .assertThat()
        .contentType(ContentType.JSON)
        .body("mensagem", equalTo("Hello World! Java DIO"))
          .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void whenFindAllThenCkechResult() {
    port = 8082;
    given()
    .when()
      .get("/parking")
    .then()
      .contentType(ContentType.JSON).statusCode(HttpStatus.OK.value())
      .body("license[0]", Matchers.equalTo("MG-1234"))
      .extract().response().body().prettyPrint();  
  }

  @Test
  public void whenCreateThenCkeckIsCreated() {
    port = 8082;
    var createDTO = new ParkingCreateDTO("MG-1234", "MG", "Palio", "Prata");
    given()
      .when()
        .contentType(ContentType.JSON)
        .body(createDTO)
        .post("/parking")
      .then()
        .contentType(ContentType.JSON).statusCode(HttpStatus.CREATED.value())
        .body("license", Matchers.equalTo("MG-1234"))
        .body("state", Matchers.equalTo("MG"))
        .body("model", Matchers.equalTo("Palio"))
        .body("color", Matchers.equalTo("Prata"));

  }

}
