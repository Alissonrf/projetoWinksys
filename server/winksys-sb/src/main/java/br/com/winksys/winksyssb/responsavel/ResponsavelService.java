package br.com.winksys.winksyssb.responsavel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.winksys.winksyssb.model.entities.Responsavel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

@Service
public class ResponsavelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelService.class);

	@Autowired
	private final IResponsavelRepository iResponsavelRepository;

	public ResponsavelService(IResponsavelRepository iResponsavelRepository) {
		this.iResponsavelRepository = iResponsavelRepository;
	}

	@Transactional
	public ResponsavelDTO save(ResponsavelDTO responsavelDTO) {

		this.validate(responsavelDTO);

		LOGGER.info("Salvando responsavel");
		LOGGER.debug("Responsavel: {}", responsavelDTO);

		Responsavel responsavel = new Responsavel();
		responsavel.setCpf(responsavelDTO.getCpf());
		responsavel.setNome(responsavelDTO.getNome());

		responsavel = this.iResponsavelRepository.save(responsavel);

		return ResponsavelDTO.of(responsavel);
	}
	
	public void saveAll(Set<Responsavel> responsavelList) {
		this.iResponsavelRepository.saveAll(responsavelList);
	}

	private void validate(ResponsavelDTO responsavelDTO) {
		LOGGER.info("Validando Usuario");

		if (responsavelDTO == null) {
			throw new IllegalArgumentException("ResponsavelDTO não deve ser nulo");
		}

		if (responsavelDTO.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome não deve ser nulo/vazio");
		}

		if (responsavelDTO.getCpf().isEmpty()) {
			throw new IllegalArgumentException("Cpf não deve ser nulo/vazio");
		}
	}

	public ResponsavelDTO findById(Long id) {
		Optional<Responsavel> usuarioOptional = this.iResponsavelRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			return ResponsavelDTO.of(usuarioOptional.get());
		}

		throw new IllegalArgumentException(String.format("ID %s não existe", id));
	}

	public ResponsavelDTO update(ResponsavelDTO responsavelDTO, Long id) {
		Optional<Responsavel> responsavelExistenteOptional = this.iResponsavelRepository.findById(id);

		if (responsavelExistenteOptional.isPresent()) {
			Responsavel responsavelExistente = responsavelExistenteOptional.get();

			LOGGER.info("Atualizando responsável... id: [{}]", responsavelExistente.getId());
			LOGGER.debug("Payload: {}", responsavelDTO);
			LOGGER.debug("responsável Existente: {}", responsavelExistente);

			responsavelExistente.setCpf(responsavelDTO.getCpf());
			responsavelExistente.setNome(responsavelDTO.getNome());

			responsavelExistente = this.iResponsavelRepository.save(responsavelExistente);

			return ResponsavelDTO.of(responsavelExistente);
		}

		throw new IllegalArgumentException(String.format("ID %s não existe", id));
	}

	public void delete(Long id) {
		LOGGER.info("Executando delete para responsável de ID: [{}]", id);

		this.iResponsavelRepository.deleteById(id);
	}
}