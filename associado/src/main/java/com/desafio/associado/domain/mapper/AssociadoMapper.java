package com.desafio.associado.domain.mapper;


import com.desafio.associado.domain.dto.AssociadoDTO;
import com.desafio.associado.domain.model.Associado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "numeroDocumento", expression = "java(com.desafio.associado.core.util.DocumentoUtil.somenteNumeros(associadoDTO.getNumeroDocumento()))")
    Associado convertToEntity(AssociadoDTO associadoDTO);

    @InheritInverseConfiguration
    AssociadoDTO convertToDTO(Associado associado);

    default String generateUUID() {
        return UUID.randomUUID().toString();
    }

    default Page<AssociadoDTO> convertToPageDTO(Page<Associado> pageAssociados) {
        return pageAssociados.map(this::convertToDTO);
    }

}
