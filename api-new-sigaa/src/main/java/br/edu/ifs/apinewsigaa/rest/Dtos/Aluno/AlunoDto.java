package br.edu.ifs.apinewsigaa.rest.Dtos.Aluno;

import java.util.Date;

public record AlunoDto(int id, String nome, String cpf, String email, Date dataNascimento, String celular, int matricula, String apelido) {
}
