package school.sptech.ensine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ensine.domain.Mensagem;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findByChat_Id(int id);
    
}
