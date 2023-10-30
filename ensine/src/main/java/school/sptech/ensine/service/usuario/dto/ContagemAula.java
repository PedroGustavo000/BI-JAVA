package school.sptech.ensine.service.usuario.dto;


import java.time.LocalDateTime;

public class ContagemAula {

    private String nome;
    private Long total;
    private int mes;

    public ContagemAula(String materiaNome, Long total, int mes) {
        this.nome = materiaNome;
        this.total = total;
        this.mes = mes;
    }

    // Getters and setters
    public String getMateriaNome() {
        return nome;
    }

    public void setMateriaNome(String materiaNome) {
        this.nome = materiaNome;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
