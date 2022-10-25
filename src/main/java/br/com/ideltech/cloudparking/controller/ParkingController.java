package br.com.ideltech.cloudparking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class ParkingController {
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String hello() {
    return "Hello World! Java DIO";
  }


}
