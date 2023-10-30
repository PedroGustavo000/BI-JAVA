package school.sptech.ensine.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import school.sptech.ensine.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.ensine.service.usuario.dto.*;
import school.sptech.ensine.domain.*;
import school.sptech.ensine.repository.MateriaRepository;
import school.sptech.ensine.service.usuario.UsuarioService;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.ensine.util.TabelaHashProfessor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

@Tag(name = "Usuário", description = "Requisições relacionadas aos usuários")
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

//  private List<UsuarioDto> usuariosLogados = new ArrayList<>();

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MateriaRepository materiaRepository;


    //pode ocorrer um bug na lista de usuariosLogados ao tentar cadastrar um novo usuário e logá-lo
    //logo em seguida, porque o limite da lista será o de número de usuários cadastrados anteriormente.
    private ListaObj<UsuarioDto> usuariosLogados = new ListaObj<>();
    private TabelaHashProfessor tabelaHashProfessor = new TabelaHashProfessor(3);
    @GetMapping("/existe-por-email")
    public ResponseEntity<Boolean> existePorEmail(@RequestParam String emailUsuario) {
        return ResponseEntity.ok(usuarioService.existePorEmail(emailUsuario));
    }

    @GetMapping("/professores-recomendados")
    public ResponseEntity<List<Professor>> listarProfessoresRecomendados(@RequestParam("disciplinas") List<String> disciplinas) {
        Set<Professor> professores = new HashSet<>(); // Use a Set to automatically remove duplicates

        for (String disciplina : disciplinas) {
            List<Professor> professoresEncontrados = this.usuarioService.getProfessoresByMateria(disciplina);
            professores.addAll(professoresEncontrados); // Use addAll to add all professors at once
        }

        if (professores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new ArrayList<>(professores)); // Convert Set to List for response
    }

//    @GetMapping("/{idUsuario}/professores-recomendados/")
//    public List<Professor> listarProfessoresRecomendados(@PathVariable int idUsuario) {
//        Optional<Usuario> usuario = this.usuarioService.encontraUsuarioId(idUsuario);
//        if(usuario.isEmpty()){
//            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
//        }
//        List<Materia> materias = this.materiaRepository.findMateriaByUsuariosContains(usuario.get());
//        usuario.get().setMaterias(materias);
//        return this.usuarioService.getProfessoresRecomendados(usuario.get().getMaterias());
//    }

    @GetMapping("/professor/busca-por-descricao")
    public ResponseEntity<List<Professor>> buscarProfessoresPorDescricao(@RequestParam String termo) {
        List<Professor> professores = this.usuarioService.getProfessoresByDescricao(termo);
        if(professores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(professores);
    }

    @GetMapping("/professor/busca-por-nome")
    public ResponseEntity<List<Professor>> buscarProfessoresPorNome(@RequestParam String termo) {
        List<Professor> professores = this.usuarioService.getProfessoresByNome(termo);
        if(professores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(professores);
    }

    @GetMapping("/professor/busca-por-materia")
    public ResponseEntity<List<Professor>> buscarProfessoresPorMateria(@RequestParam String termo) {
        List<Professor> professores = this.usuarioService.getProfessoresByMateria(termo);
        if(professores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(professores);
    }

    @PatchMapping("/professor/{idProfessor}/formacao") //cadastra formação nova em um professor existente
    public ResponseEntity<Professor> cadastrarFormacao(@PathVariable int idProfessor, @RequestBody Formacao formacao) {
        Professor professor = this.usuarioService.cadastrarFormacao(idProfessor, formacao);
        return ResponseEntity.created(null).body(professor);
    }

    @PatchMapping("/professor/{idProfessor}/disponibilidade") //cadastra disponibilidade nova em um professor existente
    public ResponseEntity<Professor> cadastrarDisponibilidade(@PathVariable int idProfessor, @RequestBody Disponibilidade disponibilidade) {
        Professor professor = this.usuarioService.cadastrarDisponibilidade(idProfessor, disponibilidade);
        return ResponseEntity.created(null).body(professor);
    }

    @GetMapping("/professor/{idProfessor}/formacoes")
    public ResponseEntity<List<FormacaoResumoDto>> listarFormacoes(@PathVariable int idProfessor) {
        List<FormacaoResumoDto> formacoes = this.usuarioService.getFormacoes(idProfessor);
        if(formacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(formacoes);
    }

    @GetMapping("/professor/{idProfessor}/disponibilidades")
    public ResponseEntity<List<DisponibilidadeResumoDto>> listarDisponibilidades(@PathVariable int idProfessor) {
        List<DisponibilidadeResumoDto> disponibilidades = this.usuarioService.getDisponibilidades(idProfessor);
        if(disponibilidades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(disponibilidades);
    }

    @GetMapping("professor/busca")
    @Tag(name = "Encontrar professor", description = "Devolve um professor pesquisado pelo id")
    @ApiResponse(responseCode = "200", description = "Professor encontrado")
    @ApiResponse(responseCode = "404", description = "Professor não existe!", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<Optional<Professor>> buscarProfessor(@RequestParam Integer id){
        Optional<Professor> prof = usuarioService.encontraProfessorID(id);
        return ResponseEntity.status(200).body(prof);
    }


    @GetMapping("professor/busca/lista")
    @Tag(name = "Encontrar professor", description = "Devolve um professor pesquisado pelo id")
    @ApiResponse(responseCode = "200", description = "Professor encontrado")
    @ApiResponse(responseCode = "404", description = "Professor não existe!", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<List<Professor>> buscarListaProfessor(@RequestParam String nome){
        TabelaHashProfessor prof = usuarioService.buscarListaProfessor(nome);
        List<Professor> list = prof.listAll();
        popularTabelaHash(list);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("professor/busca/lista/letra")
    @Tag(name = "Encontrar professor", description = "Devolve um professor pesquisado pelo id")
    @ApiResponse(responseCode = "200", description = "Professor encontrado")
    @ApiResponse(responseCode = "404", description = "Professor não existe!", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<List<Professor>> buscarListaProfessorPorLetra(@RequestParam String nome){
        List<Professor> professors = tabelaHashProfessor.buscaLista(nome);
        return ResponseEntity.status(200).body(professors);
    }
    @GetMapping("/materias")
    @SecurityRequirement(name = "Bearer")
    @Tag(name = "Listar matérias", description = "Devolve uma lista de disciplinas")
    @ApiResponse(responseCode = "200", description = "Disciplinas encontradas")
    @ApiResponse(responseCode = "204", description = "Não há disciplinas cadastradas", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "401", description = "Login não foi realizado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<ListaObj<Materia>> listarMaterias(){
        Long qtdMaterias = materiaRepository.count();
        ListaObj<Materia> materias = new ListaObj<>(Math.toIntExact(qtdMaterias));
        materias.adiciona(materiaRepository.findAll().toArray(new Materia[Math.toIntExact(qtdMaterias)]));

        if (materias.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(materias);
    }

    @PostMapping("/cadastrar")
    @Tag(name = "Cadastrar usuários", description = "Cadastra um usuário do sistema")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "406", description = "Erro: o aluno informado não respeita as validações", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "409", description = "Erro: o e-mail informado já possui cadastro", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<Usuario> adicionaAluno(@RequestBody @Valid UsuarioCriacaoDto alunoNovo, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("ERRO(CADASTRO) >>> O ALUNO NÃO RESPEITA AS VALIDAÇÕES");
            return ResponseEntity.status(406).build();
        }
        if (usuarioService.existePorEmail(alunoNovo.getEmail())) {
            System.out.println("ERRO(CADASTRO) >>> ALUNO COM EMAIL JÁ CADASTRADO");
            return ResponseEntity.status(409).build();
        }

        //usuariosLogados.adiciona(new UsuarioDto(aluno));
        return ResponseEntity.status(201).body(usuarioService.criarAluno(alunoNovo));
    }


    @PostMapping("/professor/cadastrar")
    @Tag(name = "Cadastrar professor", description = "Cadastra um professor")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "406", description = "Erro: o professor informado não respeita as validações", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "409", description = "Erro: o e-mail informado já possui cadastro", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<ProfessorCriacaoDto> adicionaProfessor(@RequestBody @Valid ProfessorCriacaoDto professorNovo, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("ERRO(CADASTRO) >>> O PROFESSOR NÃO RESPEITA AS VALIDAÇÕES");
            return ResponseEntity.status(406).build();
        }
        if (usuarioService.existePorEmail(professorNovo.getEmail())) {
            System.out.println("ERRO(CADASTRO) >>> PROFESSOR COM EMAIL JÁ CADASTRADO");
            return ResponseEntity.status(409).build();
        }

        //usuariosLogados.adiciona(new UsuarioDto(professor));
        return ResponseEntity.status(201).body(usuarioService.criarProfessor(professorNovo));
    }


    @GetMapping
    @SecurityRequirement(name = "Bearer")
    @Tag(name = "Listar usuários", description = "Lista os usuários cadastrados")
    @ApiResponse(responseCode = "204", description = "Não há usuários cadastrados", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso")
    public ResponseEntity<ListaObj<Usuario>> listar(){
        int qtdUsuarios = usuarioService.qtdeUsuario();
        ListaObj<Usuario> usuarios = new ListaObj<>(qtdUsuarios);
        usuarios.adiciona(usuarioService.todosUsuarios().toArray(new Usuario[qtdUsuarios]));

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/buscar-por-id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> listarPorId(@RequestParam int id){
        Optional<Usuario> usuario = usuarioService.encontraUsuarioId(id);

        return usuario.map(value -> ResponseEntity.status(200).body(value)).orElseGet(() -> ResponseEntity.status(400).build());
    }

    @SecurityRequirement(name = "Bearer")
    @Tag(name = "Listar usuários logados", description = "Lista os usuários atualmente logados no sistema")
    @ApiResponse(responseCode = "401", description = "Login não foi realizado", content = @Content(schema = @Schema(hidden = true)))
    @GetMapping("/logados")
    @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso")
    public ResponseEntity<ListaObj<UsuarioDto>> listarLogados(){
        ListaObj<UsuarioDto> usuariosLogadosDto = new ListaObj<>(usuariosLogados.size());
        for (int i = 0; i < usuariosLogados.size(); i++) {
            UsuarioDto usuarioDto = new UsuarioDto(usuariosLogados.get(i));
            usuariosLogadosDto.adiciona(usuarioDto);
        }
        return ResponseEntity.status(200).body(usuariosLogadosDto);
    }


    @PostMapping("/login")
    @Tag(name = "Login", description = "Autentica os usuários no sistema")
    @ApiResponse(responseCode = "409", description = "Usuário já está logado", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLogar){

        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLogar);

//        for(int i = 0; i < usuariosLogados.size(); i++){
//            if (usuariosLogados.get(i).getEmail().equals(usuarioToken.getEmail())){
//                return ResponseEntity.status(409).build();
//            }
//        }

//        Optional<Usuario> usuario = usuarioService.encontraPorEmail(usuarioToken.getEmail());

//        usuariosLogados.adiciona(new UsuarioDto (usuario.get()));

        return ResponseEntity.status(200).body(usuarioToken);
    }

    @DeleteMapping("/logoff/{email}")
    @SecurityRequirement(name = "Bearer")
    @Tag(name = "LogOff", description = "Desautentica os usuários no sistema")
    @ApiResponse(responseCode = "404", description = "Usuário não existe ou não está logado")
    @ApiResponse(responseCode = "200", description = "Usuário deslogado com sucesso")
    @ApiResponse(responseCode = "401", description = "Login não foi realizado", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<String> logoff(@PathVariable String email){

        int tamanho = usuariosLogados.size();

        for(int i = 0; i < usuariosLogados.size(); i++){
            if(usuariosLogados.get(i).getEmail().equals(email)){
                usuariosLogados.remove(i);
                break;
            }
        }
        // por que estamos comparando o tamanho da lista com o tamanho da lista?
        // por que isso implica que o usuário não está logado?
        // remover o IF não dá no mesmo?
        if (usuariosLogados.size() == tamanho){
            return ResponseEntity.status(404).body("Usuario não está logado");
        }
        return ResponseEntity.status(200).body("Usuario deslogado com sucesso");
    }

    @GetMapping("/professor/insignia/{idProfessor}")
    public ResponseEntity<Map<Avaliacao.Insignia, Integer>> countInsigniasProfessor(@PathVariable Integer idProfessor) {
        Optional<Professor> professor = this.usuarioService.encontraProfessorID(idProfessor);
        if (professor.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Professor não encontrado");
        }
        Map<Avaliacao.Insignia, Integer> insignias = usuarioService.countInsigniasProfessor(idProfessor);

        return ResponseEntity.ok(insignias);
    }

    private void popularTabelaHash(List<Professor> professores) {
        tabelaHashProfessor = new TabelaHashProfessor(2);
        for (Professor profesor:
                professores) {
            tabelaHashProfessor.insere(profesor);
        }
    }

    @GetMapping("/count-usuarios-meses")
    public ResponseEntity<List<Object[]>> countUsuariosByMonth() {
        LocalDate currentTime = LocalDate.now();
        LocalDate threeMonthsAgo = currentTime.minusMonths(3);

        List<Object[]> contagemUsuarios = usuarioService.countUsuariosByMonth(currentTime, threeMonthsAgo);

        if (contagemUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(contagemUsuarios);
    }
}
