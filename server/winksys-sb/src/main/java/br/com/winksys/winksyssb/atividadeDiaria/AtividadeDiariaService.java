package br.com.winksys.winksyssb.atividadeDiaria;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.winksys.winksyssb.model.entities.AtividadeDiaria;
import br.com.winksys.winksyssb.model.entities.Responsavel;
import br.com.winksys.winksyssb.responsavel.ResponsavelService;

@Service
public class AtividadeDiariaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AtividadeDiariaService.class);


	private final IAtividadeDiariaRepository iAtividadeDiariaRepository;

	private final ResponsavelService responsavelService;
	
	@Autowired
	public AtividadeDiariaService(IAtividadeDiariaRepository iAtividadeDiariaRepository, ResponsavelService responsavelService) {
		this.iAtividadeDiariaRepository = iAtividadeDiariaRepository;
		this.responsavelService = responsavelService;
	}

	@Transactional
	public AtividadeDTO save(AtividadeDTO atividadeDTO) {

		this.validate(atividadeDTO);

		LOGGER.info("Salvando atividade");
		LOGGER.debug("Atividade: {}", atividadeDTO);
		
		var responsavelList = atividadeDTO.getResponsaveis().stream().map(responsavelDTO -> new Responsavel(responsavelDTO.getNome(), responsavelDTO.getCpf())).collect(Collectors.toSet());
		
		responsavelService.saveAll(responsavelList);
		
		AtividadeDiaria atividadeDiaria = new AtividadeDiaria();
		atividadeDiaria.setDataCadastro(atividadeDTO.getDataCadastro());
		atividadeDiaria.setDataPrevista(atividadeDTO.getDataPrevista());
		atividadeDiaria.setDataConclusao(atividadeDTO.getDataConclusao());
		atividadeDiaria.setDescricao(atividadeDTO.getDescricao());
		atividadeDiaria.setResponsaveis(responsavelList);
		atividadeDiaria.setPrioridade(atividadeDTO.getPrioridade());

		atividadeDiaria = this.iAtividadeDiariaRepository.save(atividadeDiaria);

		return AtividadeDTO.of(atividadeDiaria);
	}

	private void validate(AtividadeDTO atividadeDTO) {
		LOGGER.info("Validando Usuario");

		if (atividadeDTO == null) {
			throw new IllegalArgumentException("ResponsavelDTO não deve ser nulo");
		}
		
		if (atividadeDTO.getResponsaveis().isEmpty()) {
			throw new IllegalArgumentException("Nome não deve ser nulo/vazio");
		}

		if (atividadeDTO.getPrioridade().isEmpty()) {
			throw new IllegalArgumentException("Cpf não deve ser nulo/vazio");
		}
	}

	public AtividadeDTO findById(Long id) {
		Optional<AtividadeDiaria> atividadeOptional = this.iAtividadeDiariaRepository.findById(id);

		if (atividadeOptional.isPresent()) {
			return AtividadeDTO.of(atividadeOptional.get());
		}

		throw new IllegalArgumentException(String.format("ID %s não existe", id));
	}

	public AtividadeDTO update(AtividadeDTO atividadeDTO, Long id) {
		Optional<AtividadeDiaria> atividadeExistenteOptional = this.iAtividadeDiariaRepository.findById(id);

		if (atividadeExistenteOptional.isPresent()) {
			AtividadeDiaria atividadeExistente = atividadeExistenteOptional.get();

			LOGGER.info("Atualizando responsável... id: [{}]", atividadeExistente.getId());
			LOGGER.debug("Payload: {}", atividadeDTO);
			LOGGER.debug("responsável Existente: {}", atividadeExistente);
			
			var responsavelList = atividadeDTO.getResponsaveis().stream().map(responsavelDTO -> new Responsavel(responsavelDTO.getNome(), responsavelDTO.getCpf())).collect(Collectors.toSet());
			responsavelService.saveAll(responsavelList);

			atividadeExistente.setDataCadastro(atividadeDTO.getDataCadastro());
			atividadeExistente.setDataPrevista(atividadeDTO.getDataPrevista());
			atividadeExistente.setDataConclusao(atividadeDTO.getDataConclusao());
			atividadeExistente.setDescricao(atividadeDTO.getDescricao());
			atividadeExistente.setResponsaveis(responsavelList);
			atividadeExistente.setPrioridade(atividadeDTO.getPrioridade());

			atividadeExistente = this.iAtividadeDiariaRepository.save(atividadeExistente);

			return AtividadeDTO.of(atividadeExistente);
		}

		throw new IllegalArgumentException(String.format("ID %s não existe", id));
	}

	public void delete(Long id) {
		LOGGER.info("Executando delete para responsável de ID: [{}]", id);

		this.iAtividadeDiariaRepository.deleteById(id);;
	}
}
