package br.com.ideltech.cloudparking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Parking Controller")
public class ParkingController {

  private final ParkingMapper parkingMapper;
  private final ParkingService parkingService;

  @Autowired
  public ParkingController (ParkingService parkingService, ParkingMapper parkingMapper) {
    this.parkingService = parkingService;
    this.parkingMapper = parkingMapper;
  }

  @ApiOperation("Next class")
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Map<String, Object> hello() {
    Map<String, Object> map = new HashMap<>();
    map.put("mensagem", "Hello World! Java DIO");
    map.put("Próxima aula:", "Realizando os testes da APi e configurando as portas da aplicação.");
    return map;
  }

  @ApiOperation("Find all parkings")
  @RequestMapping(value = "/parking", method = RequestMethod.GET)
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

  @ApiOperation("Find parking by id")
  @RequestMapping(value = "/parking/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<ParkingDTO> findById(@PathVariable(name = "id") String id) {
    
    ParkingModel parking = parkingService.findById(id);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);
    return new ResponseEntity<ParkingDTO>(result, HttpStatus.OK);
  }

  @ApiOperation("Create parking")
  @RequestMapping(value = "/parking", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
    
    var parkingCreate = parkingMapper.toParkingCreate(dto);
    ParkingModel parkingModel = parkingService.create(parkingCreate);
    ParkingDTO result = parkingMapper.toParkingDTO(parkingModel);
    return new ResponseEntity<ParkingDTO>(result, HttpStatus.CREATED);
    // return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @ApiOperation("Update parking")
  @RequestMapping(value = "/parking/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<ParkingDTO> update(@PathVariable(name = "id") String id, @RequestBody ParkingCreateDTO dto) {

    var parkingCreate = parkingMapper.toParkingCreate(dto);
    ParkingModel parkingModel = parkingService.update(id, parkingCreate);
    ParkingDTO result = parkingMapper.toParkingDTO(parkingModel);
    return new ResponseEntity<ParkingDTO>(result, HttpStatus.OK);
    // return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @ApiOperation("Delete parking by id")
  @RequestMapping(value = "/parking/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<String> deleteById(@PathVariable String id) {

    parkingService.deleteById(id);
    
    return ResponseEntity.noContent().build();
    // return new ResponseEntity<String>("Successfully Deleted", HttpStatus.NO_CONTENT);
  }

  @ApiOperation("Exit of parking")
  @RequestMapping(value = "/paking/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<ParkingDTO> exit(@PathVariable(name = "id") String id) {
    ParkingModel parking = parkingService.exit(id); 
    return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
  }


}
