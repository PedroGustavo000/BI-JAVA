package school.sptech.ensine.service.usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.ensine.domain.Avaliacao;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.repository.AvaliacaoRepository;

import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criarAvaliacao(@Valid Avaliacao avaliacao) {
        if (avaliacaoRepository.findByIdAndAula_Alunos_Id(avaliacao.getAula().getId(),
                avaliacao.getUsuario().getId()).isEmpty()) {
            return this.avaliacaoRepository.save(avaliacao);
        }
        throw new IllegalStateException("Aluno já avaliou esta aula");
    }

    public Double getNotaByProfessor(Professor professor) {
        return this.avaliacaoRepository.findMeanNotaByProfessor(professor);
    }

    public List<Avaliacao> listAvaliacaoByAulaId(Integer idAula) {
        return this.avaliacaoRepository.findByAula_Id(idAula);
    }

    public List<Avaliacao> listAvaliacaoByProfessorId(Integer idProfessor) {
        return this.avaliacaoRepository.findByProfessor_Id(idProfessor);
    }

    public List<Avaliacao> listAvaliacaoByAlunoId(Integer idAluno) {
        return this.avaliacaoRepository.findByUsuario_Id(idAluno);
    }


}
