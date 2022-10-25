package br.com.ideltech.cloudparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ideltech.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.ideltech.cloudparking.controller.dto.ParkingDTO;
import br.com.ideltech.cloudparking.controller.mapper.ParkingMapper;
import br.com.ideltech.cloudparking.model.ParkingModel;
import br.com.ideltech.cloudparking.service.ParkingService;


@RestController
public class ParkingController {

  private final ParkingMapper parkingMapper;
  private final ParkingService parkingService;

  @Autowired
  public ParkingController (ParkingService parkingService, ParkingMapper parkingMapper) {
    this.parkingService = parkingService;
    this.parkingMapper = parkingMapper;
  }
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String hello() {
    
    return "Hello World! Java DIO";
  }

  @RequestMapping(value="/parking", method=RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<ParkingDTO>> findAll() {

    // ParkingModel parking1 = new ParkingModel("1", "MG-1234", "MG", "Palio", "Prata" );
    // ParkingModel parking2 = new ParkingModel("2", "SP-1234", "SP", "Renegade", "Azul" );
    // ParkingModel parking3 = new ParkingModel("3", "BA-1234", "BA", "EcoSport", "Preto" );
    // return Arrays.asList(parking1, parking2, parking3);

    List<ParkingModel> parkingList = parkingService.findAll();
    List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
    return new ResponseEntity<List<ParkingDTO>>(result, HttpStatus.OK);
  }

  @RequestMapping(value="/parking/{id}", method=RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
    
    ParkingModel parking = parkingService.findById(id);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);
    return new ResponseEntity<ParkingDTO>(result, HttpStatus.OK);
  }

  @RequestMapping(value="/parking", method=RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
    
    var parkingCreate = parkingMapper.toParkingCreate(dto);
    ParkingModel parkingModel = parkingService.create(parkingCreate);
    ParkingDTO result = parkingMapper.toParkingDTO(parkingModel);
    return new ResponseEntity<ParkingDTO>(result, HttpStatus.CREATED);
    // return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }


}
