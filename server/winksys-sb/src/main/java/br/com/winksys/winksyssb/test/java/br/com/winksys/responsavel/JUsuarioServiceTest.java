package br.com.winksys.winksyssb.test.java.br.com.winksys.responsavel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mysql.cj.util.StringUtils;

import br.com.winksys.winksyssb.responsavel.IResponsavelRepository;
import br.com.winksys.winksyssb.responsavel.Responsavel;
import br.com.winksys.winksyssb.responsavel.ResponsavelDTO;
import br.com.winksys.winksyssb.responsavel.ResponsavelService;

@SpringBootTest
class JUsuarioServiceTest {

	@Mock
	private IResponsavelRepository responsavelRepository;

	@Capto
	private ArgumentCaptor<Responsavel> argumentCaptor;

	@InjectMocks
	private ResponsavelService responsavelService;

	@Test
	public void save() {
		ResponsavelDTO responsavelDTO = new ResponsavelDTO("meu.cpf", "meu.nome");

		ResponsavelDTO responsavelMock = Mockito.mock(Responsavel.class);

		when(responsavelMock.getCpf()).thenReturn(responsavelDTO.getCpf());
		when(responsavelMock.getNome()).t+henReturn(responsavelDTO.getNome());

		when(this.responsavelRepository.save(any())).thenReturn(responsavelMock);

		this.responsavelRepository.save(responsavelMock);

		verify(this.responsavelRepository, times(1)).save(this.argumentCaptor.capture());
		Responsavel createdResponsavel = argumentCaptor.getValue();

		assertTrue(StringUtils.isNullOrEmpty(createdResponsavel.getNome()), "Nome não deve ser nulo");
		assertTrue(StringUtils.isNullOrEmpty(createdResponsavel.getCpf()), "Cpf não deve ser nulo");
	}

	@Test
	public void responsavelDTONull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.responsavelService.save(null);
		});
	}

	@Test
	public void responsavelComCpfNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			ResponsavelDTO responsavelDTO = new ResponsavelDTO(null, "cpf_vazio");
			this.responsavelService.save(responsavelDTO);
		});
	}

	@Test
	void usuarioComNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			ResponsavelDTO responsavelDTO = new ResponsavelDTO("nome_vazio", "");
			this.responsavelService.save(responsavelDTO);
		});
	}

}

