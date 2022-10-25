package br.com.ideltech.cloudparking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.ideltech.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.ideltech.cloudparking.controller.dto.ParkingDTO;
import br.com.ideltech.cloudparking.model.ParkingModel;

@Component
public class ParkingMapper {

  private static final ModelMapper MODEL_MAPPER = new ModelMapper();

  public ParkingDTO toParkingDTO(ParkingModel parkingModel){

    return MODEL_MAPPER.map(parkingModel, ParkingDTO.class);
  }

  public List<ParkingDTO> toParkingDTOList(List<ParkingModel> parkingList) {
    return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
  }

  public ParkingModel toParking(ParkingDTO dto) {
    return MODEL_MAPPER.map(dto, ParkingModel.class);
  }

  public ParkingModel toParkingCreate(ParkingCreateDTO dto) {
    return MODEL_MAPPER.map(dto, ParkingModel.class);
  }

}
