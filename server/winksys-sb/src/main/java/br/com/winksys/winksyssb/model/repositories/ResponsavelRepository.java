package br.com.winksys.winksyssb.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.winksys.winksyssb.model.entities.Responsavel;


public interface ResponsavelRepository extends PagingAndSortingRepository<Responsavel, Long>{

	public Iterable<Responsavel> findByNomeContainingIgnoreCase(String parteNome);
	
}
