package school.sptech.ensine.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.ensine.domain.Disponibilidade;
import school.sptech.ensine.repository.DisponibilidadeRepository;

import java.util.List;

@Service
public class DisponibilidadeService {

    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    public List<Disponibilidade> getDisponibilidadesByProfessorId(int idProfessor) {
        return this.disponibilidadeRepository.findByProfessorId(idProfessor);
    }

}
