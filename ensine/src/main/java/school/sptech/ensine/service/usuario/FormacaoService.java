package school.sptech.ensine.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.ensine.domain.Formacao;
import school.sptech.ensine.repository.FormacaoRepository;

@Service
public class FormacaoService {
    @Autowired
    private FormacaoRepository formacaoRepository;

    public Formacao cadastrar(Formacao formacao) {
        return this.formacaoRepository.save(formacao);
    }
}
