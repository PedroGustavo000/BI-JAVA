package school.sptech.ensine.service.usuario.dto;

public class ProfessorCriacaoDto extends UsuarioCriacaoDto{

    private String descricao;

    private Double precoHoraAula;

    public ProfessorCriacaoDto(String descricao) {
        this.descricao = descricao;
    }

    public ProfessorCriacaoDto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoHoraAula() {
        return precoHoraAula;
    }

    public void setPrecoHoraAula(Double precoHoraAula) {
        this.precoHoraAula = precoHoraAula;
    }
}
