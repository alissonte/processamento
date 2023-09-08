package com.desafio.boleto.domain.mapper;

import com.desafio.boleto.domain.dto.BoletoDTO;
import com.desafio.boleto.domain.model.Boleto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BoletoMapper {

    @Mapping(target = "uuid", expression = "java(generateUUID())")
    Boleto convertToEntity(BoletoDTO boletoDTO);

    @InheritInverseConfiguration
    BoletoDTO convertToDTO(Boleto boleto);

    default String generateUUID() {
        return UUID.randomUUID().toString();
    }

    default Page<BoletoDTO> convertToPageDTO(Page<Boleto> pageBoleto) {
        return pageBoleto.map(this::convertToDTO);
    }
}
