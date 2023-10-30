drop database ensineme;
create database ensineme;
use ensineme;

create table usuario (
	id_usuario int primary key auto_increment,
    nome varchar(80),
    email varchar(255),
    senha varchar(45),
    data_nasc date,
    is_professor boolean,
    qtd_experiencia int,
    fotoPerfil varchar(255)
);

create table log_experiencia(
	quantidade int,
    data_adquirida datetime,
    fk_usuario int,
    foreign key (fk_usuario) references usuario (id_usuario)
);

create table chat(
	id_chat int primary key auto_increment
);

create table chat_participante(
	fk_usuario int,
    fk_chat int,
    primary key (fk_usuario, fk_chat),
    foreign key (fk_usuario) references usuario (id_usuario),
    foreign key (fk_chat) references chat (id_chat)
);

create table disciplina(
	id_disciplina int primary key auto_increment,
    nome varchar(45)
);

create table disciplina_usuario(
	fk_disciplina int,
    fk_usuario int,
    foreign key (fk_usuario) references usuario (id_usuario),
    foreign key (fk_disciplina) references disciplina (id_disciplina)
);

create table professor(
	id_professor int primary key auto_increment,
    fk_usuario int,
    descricao varchar(2550),
    preco_hora_aula decimal(5,2),
    foreign key (fk_usuario) references usuario (id_usuario)
);

create table disponibilidade_professor(
	fk_professor int,
    dia_semana varchar(10),
    horario_inicio datetime,
    horario_fim datetime,
    foreign key (fk_professor) references professor (id_professor)
);

create table formacao(
	id_formacao int primary key auto_increment,
    data_inicio date,
    data_termino date,
    instituicao varchar(100),
    nome_curso varchar(30),
    tipo_formacao varchar(20),
    fk_professor int,
    foreign key (fk_professor) references professor (id_professor)
);

create table horario_disponivel(
	id_horario_disponivel int primary key auto_increment,
    fk_professor int,
    data_hora_inicial datetime,
    data_hora_final datetime,
    foreign key (fk_professor) references professor (id_professor)
);

create table aula(
	id_aula int primary key auto_increment,
    fk_professor int,
    fk_disciplina int,
    titulo varchar(255),
    data_hora datetime,
    descricao varchar(255),
    limite_participantes int,
    duracao_segundos int,
    status_aula varchar(35),
    preco decimal(5,2),
    foreign key (fk_professor) references professor (id_professor),
    foreign key (fk_disciplina) references disciplina (id_disciplina)
);

create table aluno_aula(
	fk_usuario int,
    fk_aula int,
    primary key (fk_usuario, fk_aula),
    foreign key (fk_usuario) references usuario (id_usuario),
    foreign key (fk_aula) references aula (id_aula)
);

create table mensagem(
	id_mensagem int primary key auto_increment,
    fk_remetente int,
    fk_chat int,
    conteudo varchar(300),
    foreign key (fk_remetente) references usuario (id_usuario),
    foreign key (fk_chat) references chat (id_chat)
);