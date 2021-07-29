package br.com.winksys.winksyssb.atividadeDiaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.winksys.winksyssb.model.entities.AtividadeDiaria;

/**
 * Classe responsável pela comunciação com o banco de dados
 */

@Repository
public interface IAtividadeDiariaRepository extends JpaRepository<AtividadeDiaria, Long> {

}
