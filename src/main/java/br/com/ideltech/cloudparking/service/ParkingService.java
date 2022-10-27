package br.com.ideltech.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ideltech.cloudparking.exception.ParkingNotFoundException;
import br.com.ideltech.cloudparking.model.ParkingModel;

@Service
public class ParkingService {
  
  private static Map<String, ParkingModel> parkingMap = new HashMap<>();

  // static {
  //   var id1 = getUUID();
  //   var id2 = getUUID();
  //   var id3 = getUUID();

  //   ParkingModel parking1 = new ParkingModel(id1, "MG-1234", "MG", "Palio", "Prata");
  //   ParkingModel parking2 = new ParkingModel(id2, "SP-1234", "SP", "Renegade", "Azul" );
  //   ParkingModel parking3 = new ParkingModel(id3, "BA-1234", "BA", "EcoSport", "Preto");
    
  //   parkingMap.put(id1, parking1);
  //   parkingMap.put(id2, parking2);
  //   parkingMap.put(id3, parking3);
  // }

  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public List<ParkingModel> findAll() {
    return parkingMap.values().stream().collect(Collectors.toList());
  }

  public ParkingModel findById(String id) {
    ParkingModel parking = parkingMap.get(id);

    if(parking == null) {
      throw new ParkingNotFoundException(id);
    }
    return parking; 
  }

  public ParkingModel create(ParkingModel parkingCreate) {
    String uuid = getUUID();
    parkingCreate.setId(uuid);
    parkingCreate.setEntryDate(LocalDateTime.now());
    parkingMap.put(uuid, parkingCreate);
    // parkingRepository.save(parkingCreate);
    return parkingCreate;
  }

  public ParkingModel update(String id, ParkingModel parkingCreate) {
    ParkingModel parking = findById(id);
    parking.setColor(parkingCreate.getColor());
    parking.setModel(parkingCreate.getModel());
    parkingMap.replace(id, parking);
    // parkingMap.replace(id, parkingCreate, parking);
    return parking;
  }

  public void deleteById(String id) {
    findById(id);
    parkingMap.remove(id);
  }

  public ParkingModel exit(String id) {
    return null;
  }

  public ParkingModel checkOut(String id) {
    ParkingModel parking = findById(id);
    parking.setExitDate(LocalDateTime.now());
    // parking.setBill(ParkingCheckOut.getBill(parking));
    // parkingRepository.save(parking);
    return parking;
  }

  

}
