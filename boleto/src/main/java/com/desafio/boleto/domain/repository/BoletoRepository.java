package com.desafio.boleto.domain.repository;

import com.desafio.boleto.domain.model.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, String>, CrudRepository<Boleto, String> {

    Optional<Boleto> findBoletoByUuidAssociado(String uuidAssociado);

}
