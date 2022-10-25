package br.com.ideltech.cloudparking.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ideltech.cloudparking.model.ParkingModel;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class ParkingController {
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String hello() {
    return "Hello World! Java DIO";
  }

  @RequestMapping(value="/parking", method=RequestMethod.GET)
  public List<ParkingModel> findAll() {
    ParkingModel parking1 = new ParkingModel("1", "MG-1234", "MG", "Palio", "Prata" );
    ParkingModel parking2 = new ParkingModel("2", "SP-1234", "SP", "Renegade", "Azul" );
    ParkingModel parking3 = new ParkingModel("3", "BA-1234", "BA", "EcoSport", "Preto" );
    return Arrays.asList(parking1, parking2, parking3);
  }


}
