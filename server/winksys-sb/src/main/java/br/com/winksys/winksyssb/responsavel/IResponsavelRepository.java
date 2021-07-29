package br.com.winksys.winksyssb.responsavel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.winksys.winksyssb.model.entities.Responsavel;

/**
 * Classe responsável pela comunciação com o banco de dados
 */

@Repository
public interface IResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
