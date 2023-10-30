package school.sptech.ensine.service.usuario.dto;


import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import school.sptech.ensine.domain.Materia;

import java.time.LocalDate;
import java.util.List;

public class UsuarioCriacaoDto {

    private boolean isProfessor;

    @NotBlank
    @Size(min = 2, max = 80)
    private String nome;

    @NotBlank
    @Email
    @Size(min = 9, max = 40)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String senha;

    @PastOrPresent
    private LocalDate dataNasc;

    private List<Materia> materias;

    private String foto;

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
    public void addMateria(Materia materia){
        this.materias.add(materia);
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

