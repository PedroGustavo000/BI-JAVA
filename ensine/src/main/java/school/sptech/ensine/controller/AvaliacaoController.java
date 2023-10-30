package school.sptech.ensine.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ensine.domain.Aula;
import school.sptech.ensine.domain.Avaliacao;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.ensine.enumeration.Status;
import school.sptech.ensine.service.usuario.AulaService;
import school.sptech.ensine.service.usuario.AvaliacaoService;
import school.sptech.ensine.service.usuario.UsuarioService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    AvaliacaoService avaliacaoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AulaService aulaService;

    @PostMapping("/{idAluno}/{idAula}")
    public ResponseEntity<Avaliacao> criarAvaliacao(@PathVariable Integer idAluno, @PathVariable Integer idAula, @RequestBody @Valid Avaliacao avaliacao) {
        Optional<Usuario> usuario = this.usuarioService.encontraUsuarioId(idAluno);
        Optional<Aula> aula = this.aulaService.encontraAulaId(idAula);
        if (usuario.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Usuário não foi encontrado");
        }
        if (aula.isEmpty()) {
           throw new EntidadeNaoEncontradaException("Aula não encontrada");
        }
        Professor professor = aula.get().getProfessor();

        if (aula.get().getAlunos().isEmpty()) {
            throw new IllegalStateException("Aluno não pertence a essa aula");
        } else {
            for (Usuario aluno:
                 aula.get().getAlunos()) {
                if(aluno.getId() == idAluno) {
                    if (aula.get().getStatus() != Status.CONCLUIDA) {
                        throw new IllegalStateException("Aula não concluída");
                    }
                    avaliacao.setProfessor(professor);
                    avaliacao.setUsuario(usuario.get());
                    return ResponseEntity.ok(this.avaliacaoService.criarAvaliacao(avaliacao));
                }
            }
            throw new IllegalStateException("Aluno não pertence a essa aula");
        }
    }

    @GetMapping("/aula/{idAula}")
    public ResponseEntity<List<Avaliacao>> listAvaliacaoByAulaId(@PathVariable Integer idAula) {
        Optional<Aula> aula = this.aulaService.encontraAulaId(idAula);
        if (aula.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Aula não encontrada");
        }
        List<Avaliacao> avaliacoes = avaliacaoService.listAvaliacaoByAulaId(idAula);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<List<Avaliacao>> listAvaliacaoByProfessorId(@PathVariable Integer idProfessor) {
        Optional<Professor> professor = this.usuarioService.encontraProfessorID(idProfessor);
        if (professor.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Professor não encontrado");
        }
        List<Avaliacao> avaliacoes = avaliacaoService.listAvaliacaoByProfessorId(idProfessor);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<Avaliacao>> listAvaliacaoByidAlunoId(@PathVariable Integer idAluno) {
        Optional<Usuario> aluno = this.usuarioService.encontraUsuarioId(idAluno);
        if (aluno.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Aluno não encontrado");
        }
        List<Avaliacao> avaliacoes = avaliacaoService.listAvaliacaoByAlunoId(idAluno);
        return ResponseEntity.ok(avaliacoes);
    }


}
