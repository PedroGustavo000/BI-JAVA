package school.sptech.ensine.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.sptech.ensine.api.security.jwt.GerenciadorTokenJwt;
import school.sptech.ensine.domain.Aula;
import school.sptech.ensine.domain.Materia;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.domain.ListaObj;
import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.enumeration.Privacidade;
import school.sptech.ensine.enumeration.Status;
import school.sptech.ensine.repository.AulaRepository;
import school.sptech.ensine.repository.MateriaRepository;
import school.sptech.ensine.repository.UsuarioRepository;
import school.sptech.ensine.service.usuario.dto.ContagemAula;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Aula> getAulasConcluidasPorProfessorAndUsuario(Usuario aluno, Professor professor) {
        return this.aulaRepository.findByUsuarioAndProfessorAndStatusConcluida(aluno, professor);
    }

    public List<Aula> getAulasPorDescricao(String termoDeBusca) {
        return this.aulaRepository.findByDescricaoContainingIgnoreCaseAndNormalize(termoDeBusca);
    }

    public List<Aula> getAulasPorTitulo(String termoDeBusca) {
        return this.aulaRepository.findByTituloContainingIgnoreCaseAndNormalize(termoDeBusca);
    }

    public List<Aula> getAulasPorMateria(String termoDeBusca) {
        return this.aulaRepository.findByMateriaContainingIgnoreCaseAndNormalize(termoDeBusca);
    }

    public int qtdeAulas(){
        return (int) aulaRepository.count();
    }
    public List<Aula> aulas(){
        return aulaRepository.findAll();
    }
    public ListaObj<Aula> getAulasPorStatus(String status) {
        ListaObj<Aula> listaObj = new ListaObj<>(Math.toIntExact(aulaRepository.countByStatus(status)));
        listaObj.adiciona(aulaRepository.findByStatus(status));
        return listaObj;
    }
    public List<Aula> getAulasPorPrivacidade(Privacidade privacidade) {
        List<Aula> aulas = aulaRepository.findByPrivacidade(privacidade);
        return aulas;
    }
    public Optional<Aula> encontraAulaId(int id){
        Optional<Aula> aulaEncontrada = aulaRepository.findById(id);

        if (aulaEncontrada.isEmpty()){

            throw new IllegalArgumentException("Essa aula não existe");
        }

        return aulaEncontrada;
    }
    public Boolean existePorId(int id){
        return aulaRepository.existsById(id);
    }
    public List<Aula> encontraAulaPeloIdAluno(int id){
        return aulaRepository.findByAlunosId(id);
    }
    public Aula referenciaId(int id){
        return aulaRepository.getReferenceById(id);
    }
    public Long countProfessorNome(String nome){
        return aulaRepository.countByProfessorNomeEqualsIgnoreCase(nome);
    }

    public Aula aulaNova(Aula aula){
        aula.setProfessor(usuarioRepository.findProfessorById(aula.getProfessor().getId()).get());
        String nome = aula.getMateria().getNome();

        Optional<Materia> materia = materiaRepository.findByNomeContainingIgnoreCase(nome);
        if(materia.isEmpty()){
            throw new IllegalArgumentException("MATERIA NAO EXISTE!");
        }
        aula.setMateria(materia.get());
        Aula novaAula = aulaRepository.save(aula);

        var usuarioClass = new Usuario();

        aula.getAlunos().forEach(usuarioClass::addObserver);
        usuarioClass.notifyObservers(aula, "Olá, uma aula que você tinha interesse foi agendada!");
        return novaAula;
    }

    public Long getQtdAulasHoje(){
        return aulaRepository.countAulasMarcadasParaHoje();
    }

    public Optional<Aula> atualizarStatusAula(int id, Status status) {
        Optional<Aula> aula = aulaRepository.findById(id);

        if (aula.isEmpty()){
            throw new IllegalArgumentException("Essa aula não existe");
        }
        aula.get().setStatus(status);
        aulaRepository.save(aula.get());
        return aula;
    }

    public List<ContagemAula> contagemAulas(int idProfessor){
        Optional<Professor> professor = usuarioRepository.findProfessorById(idProfessor);
       return aulaRepository.contagemAulas(professor.get());
    }

    public List<ContagemAula> getContagemAulasUltimosTresMeses(LocalDateTime currentTime, LocalDateTime threeMonthsAgo) {

        return aulaRepository.countAulasByMateriaAndMonth(threeMonthsAgo, currentTime);
    }

    public List<Object[]> getTotalValorAulas(LocalDateTime currentTime, LocalDateTime threeMonthsAgo) {

        return aulaRepository.totalValorArrecadadoUltimosTresMeses(threeMonthsAgo, currentTime);
    }
}
