package br.com.winksys.winksyssb.atividadeDiaria;

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

import br.com.winksys.winksyssb.model.entities.AtividadeDiaria;
import br.com.winksys.winksyssb.model.repositories.AtividadesDiariaRepository;
import br.com.winksys.winksyssb.responsavel.ResponsavelRest;



@RestController
@RequestMapping("/atividade")
public class AtividadeDiariaRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponsavelRest.class);

	@Autowired
	private AtividadesDiariaRepository atividadesDiariaRepository;
	private final AtividadeDiariaService atividadeDiariaService;
	
	@Autowired
	public AtividadeDiariaRest (AtividadeDiariaService atividadeDiariaService) {
		this.atividadeDiariaService = atividadeDiariaService;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AtividadeDTO salvarAtividade(@RequestBody AtividadeDTO atividadeDTO) {	
		atividadeDiariaService.save(atividadeDTO);
		return atividadeDTO;
	}
	
	@PutMapping("/{id}")
    public AtividadeDTO update(@PathVariable Long id, @RequestBody AtividadeDTO atividadeDTO){
        LOGGER.info("Recebendo informações para alterar usuário... id: [{}]", id);
        LOGGER.debug("Payload: {}",atividadeDTO);
        return this.atividadeDiariaService.update(atividadeDTO, id);
    }
	
	@GetMapping
	public Iterable<AtividadeDiaria> obterAtividades() {
		return atividadesDiariaRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public AtividadeDTO obterAtividadesPorId(@PathVariable Long id) {
		
		LOGGER.info("Recebendo find by ID... id: [{}]", id);

		return this.atividadeDiariaService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		
		LOGGER.info("Recebendo Delete para Responsavel de ID: {}", id);

		this.atividadeDiariaService.delete(id);
	}
}
