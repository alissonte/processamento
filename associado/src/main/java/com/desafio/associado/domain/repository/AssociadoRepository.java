package com.desafio.associado.domain.repository;

import com.desafio.associado.domain.model.Associado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends CrudRepository<Associado, String>, PagingAndSortingRepository<Associado, String> {
}
