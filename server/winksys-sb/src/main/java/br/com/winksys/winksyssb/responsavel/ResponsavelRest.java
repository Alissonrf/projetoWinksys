package br.com.winksys.winksyssb.responsavel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import br.com.winksys.winksyssb.model.entities.Responsavel;
import br.com.winksys.winksyssb.model.repositories.ResponsavelRepository;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelRest.class);

	@Autowired
	private ResponsavelRepository responsavelRepository;
	private final ResponsavelService responsavelService;
	
	@Autowired
	public ResponsavelRest (ResponsavelService responsavelService) {
		this.responsavelService = responsavelService;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponsavelDTO salvarAtividade(@RequestBody ResponsavelDTO responsavelDTO) {	
		responsavelService.save(responsavelDTO);
		return responsavelDTO;
	}
	
	@PutMapping("/{id}")
    public ResponsavelDTO update(@PathVariable Long id, @RequestBody ResponsavelDTO responsavelDTO){
        LOGGER.info("Recebendo informações para alterar usuário... id: [{}]", id);
        LOGGER.debug("Payload: {}",responsavelDTO);
        return this.responsavelService.update(responsavelDTO, id);
    }
	
	@GetMapping
	public Iterable<Responsavel> obterResponsaveis() {
		return responsavelRepository.findAll();
	}

	@GetMapping(path = "/nome/{parteNome}")
	public Iterable<Responsavel> obterResponsaveisNome(@PathVariable String parteNome) {
		return responsavelRepository.findByNomeContainingIgnoreCase(parteNome);
	}

	@GetMapping(path = "/{id}")
	public ResponsavelDTO obterResponsavelPorId(@PathVariable Long id) {
		
		LOGGER.info("Recebendo find by ID... id: [{}]", id);

		return this.responsavelService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		
		LOGGER.info("Recebendo Delete para Responsavel de ID: {}", id);

		this.responsavelService.delete(id);
	}
}
