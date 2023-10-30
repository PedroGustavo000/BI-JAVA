package school.sptech.ensine.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.ensine.api.security.jwt.GerenciadorTokenJwt;
import school.sptech.ensine.domain.*;
import school.sptech.ensine.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.ensine.repository.*;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.ensine.service.usuario.dto.DisponibilidadeResumoDto;
import school.sptech.ensine.service.usuario.dto.FormacaoResumoDto;
import school.sptech.ensine.service.usuario.dto.ProfessorCriacaoDto;
import school.sptech.ensine.service.usuario.dto.mapper.DisponibilidadeMapper;
import school.sptech.ensine.service.usuario.dto.mapper.FormacaoMapper;
import school.sptech.ensine.service.usuario.dto.mapper.ProfessorMapper;
import school.sptech.ensine.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.ensine.service.usuario.dto.mapper.UsuarioMapper;
import school.sptech.ensine.util.TabelaHashProfessor;

import java.time.LocalDate;
import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private FormacaoRepository formacaoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;


    public List<Professor> getProfessoresRecomendados(List<Materia> materias) {
        return this.usuarioRepository.findAProfessoresRecomendados(materias);
    }

    public List<Professor> getProfessoresByDescricao(String termoDeBusca) {
        return this.usuarioRepository.findByDescricaoContainingIgnoreCase(termoDeBusca);
    }

    public List<Professor> getProfessoresByNome(String termoDeBusca) {
        return this.usuarioRepository.findByNomeContainingIgnoreCase(termoDeBusca);
    }

    public List<Professor> getProfessoresByMateria(String termoDeBusca) {
        return this.usuarioRepository.findByMateriaContainingIgnoreCaseAndNormalize(termoDeBusca);
    }

    public Professor cadastrarFormacao(int idProfessor, Formacao formacao) {
        Optional<Professor> professorOptional = this.usuarioRepository.findProfessorById(idProfessor);
        if (professorOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException("Professor não encontrado");
        }
        Professor professor = professorOptional.get();
        formacao.setProfessor(professor);
        Formacao formacaoSalva = this.formacaoRepository.save(formacao);
        professor.addFormacao(formacaoSalva);
        return this.usuarioRepository.save(professor);
    }

    public Professor cadastrarDisponibilidade(int idProfessor, Disponibilidade disponibilidade) {
        Optional<Professor> professorOptional = this.usuarioRepository.findProfessorById(idProfessor);
        if (professorOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException("Professor não encontrado");
        }
        Professor professor = professorOptional.get();
        disponibilidade.setProfessor(professor);
        Disponibilidade disponibilidadeSalva = this.disponibilidadeRepository.save(disponibilidade);
        professor.addDisponibilidade(disponibilidadeSalva);
        return this.usuarioRepository.save(professor);
    }

    public List<FormacaoResumoDto> getFormacoes(int idProfessor) {
        Optional<Professor> professorOptional = this.usuarioRepository.findProfessorById(idProfessor);
        if (professorOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException("Professor não encontrado");
        }
        List<Formacao> formacoes = this.formacaoRepository.findByProfessorId(idProfessor);
        List<FormacaoResumoDto> formacoesResumo = formacoes.stream().map(FormacaoMapper::mapFormacaoToFormacaoResumoDto).toList();
        return formacoesResumo;
    }

    public List<DisponibilidadeResumoDto> getDisponibilidades(int idProfessor) {
        Optional<Professor> professorOptional = this.usuarioRepository.findProfessorById(idProfessor);
        if (professorOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException("Professor não encontrado");
        }
        List<Disponibilidade> disponibilidades = this.disponibilidadeRepository.findByProfessorId(idProfessor);
        List<DisponibilidadeResumoDto> disponibilidadesResumo = disponibilidades
                .stream()
                .map(DisponibilidadeMapper::mapDisponibilidadeToDisponibilidadeResumoDto)
                .toList();
        return disponibilidadesResumo;
    }

    public Boolean existeNomeIgnoreCase(String nome){
        boolean existe = usuarioRepository.existsByNomeIgnoreCase(nome);
        return existe;
    }
    public Optional<Usuario> encontraPorNome(String nome){
        Optional<Usuario> nomeEncontrado = usuarioRepository.findByNomeIgnoreCase(nome);

        if (nomeEncontrado.isEmpty()){

            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return nomeEncontrado;
    }
    public Optional<Usuario> encontraPorEmail(String email){
        Optional<Usuario> emailEncontrado = usuarioRepository.findByEmailIgnoreCase(email);

        if (emailEncontrado.isEmpty()){

            throw new IllegalArgumentException("Usuário não encontrado");
        }

        return emailEncontrado;
    }
    public Optional<Professor> encontraProfessorID(Integer id){
        Optional<Professor> professorEncontrado = usuarioRepository.findProfessorById(id);
        if(professorEncontrado.isPresent()) {
            return professorEncontrado;
        } else {
            throw new IllegalStateException("Professor não foi encontrado!");
        }

    }

    public Optional<Usuario> encontraUsuarioId(Integer id) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);
        if(usuarioEncontrado.isPresent()) {
            return usuarioEncontrado;
        } else {
            throw new IllegalStateException("Usuário não foi encontrado!");
        }
    }
    public List<Usuario> todosUsuarios(){
        List<Usuario> todos = usuarioRepository.findAll();
        return todos;
    }
    public Integer qtdeUsuario(){
        int qtd = Math.toIntExact(usuarioRepository.count());
        return qtd;
    }
    public ListaObj<Usuario> listar(){
        int qtdUsuarios = Math.toIntExact(usuarioRepository.count());
        ListaObj<Usuario> usuarios = new ListaObj<>(qtdUsuarios);
        usuarios.adiciona(usuarioRepository.findAll().toArray(new Usuario[qtdUsuarios]));

        if(usuarios.isEmpty()){
            return null;
        }

        return usuarios;
    }

    public Usuario criarAluno(UsuarioCriacaoDto alunoNovo){

        alunoNovo.setProfessor(false);
        List<String> materiasNome = new ArrayList<>();
        alunoNovo.getMaterias().forEach(materia -> materiasNome.add(materia.getNome()));
        alunoNovo.getMaterias().clear(); // salva vidas
        for (int i = 0; i < materiasNome.size(); i++) {
            Optional<Materia> materiaCorreta = materiaRepository.findByNomeContainingIgnoreCase(materiasNome.get(i));

            alunoNovo.addMateria(materiaCorreta.get());
        }
        String senhaCripto = passwordEncoder.encode(alunoNovo.getSenha());
        alunoNovo.setSenha(senhaCripto);

        Usuario aluno = usuarioRepository.save(UsuarioMapper.of(alunoNovo));
        return aluno;

    }
    public ProfessorCriacaoDto criarProfessor(@RequestBody ProfessorCriacaoDto profNovo){
        // arrumar aulas
        profNovo.setProfessor(true);

        List<String> materiasNome = new ArrayList<>();
        profNovo.getMaterias().forEach(materia -> materiasNome.add(materia.getNome()));
        profNovo.getMaterias().clear(); // salva vidas2 funcionando
        for (int i = 0; i < materiasNome.size(); i++) {
            Optional<Materia> materiaCorreta = materiaRepository.findByNomeContainingIgnoreCase(materiasNome.get(i));

            profNovo.addMateria(materiaCorreta.get());
        }


        String senhaCripto = passwordEncoder.encode(profNovo.getSenha());
        profNovo.setSenha(senhaCripto);

        Professor professor = usuarioRepository.save(ProfessorMapper.of(profNovo));
        //adicionarMateriaUsuario(professor.getId(), materias);
        return profNovo;
    }


    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmailIgnoreCase(email);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmailIgnoreCase(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public Map<Avaliacao.Insignia, Integer> countInsigniasProfessor(Integer idProfessor) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByProfessor_Id(idProfessor);
        Map<Avaliacao.Insignia, Integer> insigniaMap = new HashMap<>();
        for (Avaliacao.Insignia insignia:
                Avaliacao.Insignia.values()) {
            insigniaMap.put(insignia, 0);
        }
        for (Avaliacao avaliacao:
             avaliacoes) {
            for (Avaliacao.Insignia insignia:
                 avaliacao.getInsignias()) {
                insigniaMap.replace(insignia, insigniaMap.get(insignia) + 1);
            }
        }
        return insigniaMap;
    }

    public TabelaHashProfessor buscarListaProfessor(String nome){
        List<Professor> professores = usuarioRepository.findByIsProfessorAndNomeStartsWithIgnoreCase(true,nome);

        TabelaHashProfessor tabelaHashProfessor = new TabelaHashProfessor(3);
        for (Professor profesor:
                professores) {
            tabelaHashProfessor.insere(profesor);
        }
        return tabelaHashProfessor;
    }

    private void popularTabelaHash(List<Professor> professores) {

    }

    public List<Object[]> countUsuariosByMonth(LocalDate startDate, LocalDate endDate) {
        return usuarioRepository.countUsuariosByMonth(startDate, endDate);
    }
}
