package school.sptech.ensine.service.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.ensine.api.security.jwt.GerenciadorTokenJwt;
import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.repository.MateriaRepository;
import school.sptech.ensine.repository.UsuarioRepository;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;
import school.sptech.ensine.service.usuario.dto.ProfessorCriacaoDto;
import school.sptech.ensine.service.usuario.dto.UsuarioCriacaoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Mock
    private MateriaRepository materiaRepository;

    @Mock
    private AuthenticationManager authenticationManager;


    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private PasswordEncoder passwordEncoder;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        passwordEncoder = Mockito.mock(PasswordEncoder.class);
//        UsuarioBuilder.setPasswordEncoder(passwordEncoder);
//    }

    @Test
    @DisplayName("Deve retornar false quando o nome não for válido")
    void deveRetornarFalseQuandoNomeNaoExistir() {

        // given
        String nomeInexistente = "NomeInexistente";

        // when
        Mockito.when(repository.existsByNomeIgnoreCase(nomeInexistente)).thenReturn(false);

        // then
        Boolean resultado = service.existeNomeIgnoreCase(nomeInexistente);

        // assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve retornar true quando o nome for válido")
    void deveRetornarTrueQuandoNomeExistir(){

        // given
        Usuario usuario = UsuarioBuilder.criarUsuarioAluno();

        // when
        Mockito.when(repository.existsByNomeIgnoreCase(usuario.getNome())).thenReturn(true);

        // then
        Boolean resultado = service.existeNomeIgnoreCase(usuario.getNome());

        // assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve retornar usuário quando o mesmo for enontrado no sistema de acordo com o nome")
    void deveRetornarUsuarioQuandoEncontrarPorNome(){

        // given
        Usuario usuario = UsuarioBuilder.criarUsuarioAluno();

        // when
        Mockito.when(repository.findByNomeIgnoreCase(usuario.getNome())).thenReturn(Optional.of(usuario));

        // then
        Optional<Usuario> resultado = service.encontraPorNome(usuario.getNome());

        // assert
        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.get().getId());
        assertEquals(usuario.getNome(), resultado.get().getNome());
        assertEquals(usuario.getEmail(), resultado.get().getEmail());
        assertEquals(usuario.getSenha(), resultado.get().getSenha());
        assertEquals(usuario.getDataNasc(), resultado.get().getDataNasc());
    }

    @Test
    @DisplayName("Deve retornar excessão quando não encontrar usuario pelo nome")
    void deveRetornarExcessaoQuandoNaoEncontrarUsuarioPorNome(){

        // given
        String nome = "NomeInexistente";

        // when
        Mockito.when(repository.findByNomeIgnoreCase(nome)).thenReturn(Optional.empty());

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.encontraPorNome(nome);
        });

        // assert
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar usuário quando o mesmo for enontrado no sistema de acordo com o email")
    void deveRetornarUsuarioQuandoEncontrarPorEmail(){

        // given
        Usuario usuario = UsuarioBuilder.criarUsuarioAluno();

        // when
        Mockito.when(repository.findByEmailIgnoreCase(usuario.getEmail())).thenReturn(Optional.of(usuario));

        // then
        Optional<Usuario> resultado = service.encontraPorEmail(usuario.getEmail());

        // assert
        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.get().getId());
        assertEquals(usuario.getNome(), resultado.get().getNome());
        assertEquals(usuario.getEmail(), resultado.get().getEmail());
        assertEquals(usuario.getSenha(), resultado.get().getSenha());
        assertEquals(usuario.getDataNasc(), resultado.get().getDataNasc());
    }

    @Test
    @DisplayName("Deve retornar excessão quando não encontrar usuario pelo email")
    void deveRetornarExcessaoQuandoNaoEncontrarUsuarioPorEmail(){

        // given
        String email = "emailinexistente@gmail.com";

        // when
        Mockito.when(repository.findByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.encontraPorEmail(email);
        });

        // assert
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar quando a lista vazia quando não tiver nenhum usuario cadastrado")
    void deveRetornarListaVaziaQuandoNaoTiverUsuarioCadastrado(){

        // given
        int qtdUsuarios = 0;

        // when
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        // then
        List<Usuario> resultado = service.todosUsuarios();

        // assert
        assertTrue(resultado.isEmpty());
        assertTrue(resultado.isEmpty());
        assertEquals(qtdUsuarios, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista de 2 usuarios")
    void deveRetornarUmaListadeDoisUsuariosQuandoTiverDoisUsuariosCadastrados(){

        // given
        List<Usuario> listaUsuarios = UsuarioBuilder.criarListaUsuario();
        int qtdUsuarios = 2;

        // when
        Mockito.when(repository.findAll()).thenReturn(listaUsuarios);

        // then
        List<Usuario> resultado = service.todosUsuarios();

        // assert
        assertFalse(listaUsuarios.isEmpty());
        assertFalse(resultado.isEmpty());
        assertEquals(qtdUsuarios, resultado.size());
    }

//    @Test
//    @DisplayName("Deve criar aluno quando passar dados válidos")
//    void deveCriarNovoUsuarioAlunoQuandoDadosForemValidos() {
//        // given
//        UsuarioCriacaoDto usuarioCriacaoDto = UsuarioBuilder.criarUsuarioAlunoCriacaoDto();
//        String senhaNova = "nova-senha-crypto";
//
//        // when
//        Mockito.when(materiaRepository.findByNomeContainingIgnoreCase(usuarioCriacaoDto.getMaterias().get(1).getNome())).
//                thenReturn(Optional.of(usuarioCriacaoDto.getMaterias().get(1)));
//
////        Mockito.when(passwordEncoder.encode(usuarioCriacaoDto.getSenha())).
////                thenReturn(senhaNova);
//
//        // then
//        Usuario resultado = service.criarAluno(usuarioCriacaoDto);
//
//        // assert
//        assertNotNull(resultado);
//        assertEquals(resultado.isProfessor(), usuarioCriacaoDto.isProfessor());
//        assertEquals(resultado.getNome(), usuarioCriacaoDto.getNome());
//        assertEquals(resultado.getEmail(), usuarioCriacaoDto.getEmail());
////        assertEquals(resultado.getSenha(), senhaNova);
//        assertEquals(resultado.getDataNasc(), usuarioCriacaoDto.getDataNasc());
//    }

    @Test
    @DisplayName("Deve criar professor quando passar dados válidos")
    void deveCriarNovoUsuarioProfessorQuandoDadosForemValidos() {
        // given
        ProfessorCriacaoDto professorCriacaoDto = UsuarioBuilder.criarUsuarioProfessorCriacaoDto();
        String senhaNova = "nova-senha-crypto";

        // when
        Mockito.when(materiaRepository.findByNomeContainingIgnoreCase(professorCriacaoDto.getMaterias().get(1).getNome())).
                thenReturn(Optional.of(professorCriacaoDto.getMaterias().get(1)));

        Mockito.when(passwordEncoder.encode(professorCriacaoDto.getSenha())).
                thenReturn(senhaNova);
        // then
        ProfessorCriacaoDto resultado = service.criarProfessor(professorCriacaoDto);

        // assert
        assertNotNull(resultado);
        assertEquals(resultado.getDescricao(), professorCriacaoDto.getDescricao());
        assertEquals(resultado.getSenha(), senhaNova);
    }

    @Test
    @DisplayName("Deve retornar false quando o emial não for válido")
    void deveRetornarFalseQuandoEmailNaoExistir() {

        // given
        String emailInexistente = "emailInexistente@gmail.com";

        // when
        Mockito.when(repository.existsByEmailIgnoreCase(emailInexistente)).thenReturn(false);

        // then
        Boolean resultado = service.existePorEmail(emailInexistente);

        // assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve retornar true quando o email for válido")
    void deveRetornarTrueQuandoEmailExistir(){

        // given
        Usuario usuario = UsuarioBuilder.criarUsuarioAluno();

        // when
        Mockito.when(repository.existsByEmailIgnoreCase(usuario.getEmail())).thenReturn(true);

        // then
        Boolean resultado = service.existePorEmail(usuario.getEmail());

        // assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve autenticar usuário quando passar dados válidos")
    void deveAutenticarUsuarioQuandoDadosForemValidos() {

        // given
        Usuario usuario = UsuarioBuilder.criarUsuarioAluno();
        UsuarioLoginDto usuarioLogin = UsuarioBuilder.criarUsuarioLoginDto();
        String token = "novo-token";

        // when
        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).
                thenReturn(Mockito.mock(Authentication.class));

        Mockito.when(repository.findByEmailIgnoreCase(usuarioLogin.getEmail())).
                thenReturn(Optional.of(usuario));

        Mockito.when(gerenciadorTokenJwt.generateToken(any(Authentication.class))).
                thenReturn(token);

        // then
        UsuarioTokenDto resultado = service.autenticar(usuarioLogin);

        // assert
        assertEquals(resultado.getToken(), token);
        assertEquals(resultado.getEmail(), usuarioLogin.getEmail());
    }
}
