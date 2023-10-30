package school.sptech.ensine.service.formacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.ensine.domain.Formacao;
import school.sptech.ensine.repository.FormacaoRepository;
import school.sptech.ensine.service.formacao.builder.FormacaoBuilder;
import school.sptech.ensine.service.usuario.FormacaoService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FormacaoServiceTest {

    @Mock
    private FormacaoRepository repository;

    @InjectMocks
    private FormacaoService service;

    @Test
    @DisplayName("Deve criar formação quando dados válidos")
    void deveCriarFormacaoDadosValidos(){

        // given
        Formacao formacao = FormacaoBuilder.criarFormacao();

        // when
        Mockito.when(repository.save(formacao)).
                thenReturn(formacao);

        // then
        Formacao resultado = service.cadastrar(formacao);

        // assert
        assertEquals(resultado.getId(), formacao.getId());
        assertEquals(resultado.getDtInicio(), formacao.getDtInicio());
        assertEquals(resultado.getDtTermino(), formacao.getDtTermino());
        assertEquals(resultado.getInstituicao(), formacao.getInstituicao());
        assertEquals(resultado.getTipoFormacao(), formacao.getTipoFormacao());
        assertEquals(resultado.getProfessor(), formacao.getProfessor());
    }
}
