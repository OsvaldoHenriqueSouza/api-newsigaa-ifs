package br.edu.ifs.apinewsigaa.rest.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProfessorDisciplinasAlunosDto {
    private ProfessorDto professor;
    private List<DisciplinaComAlunosDto> disciplinas;
}
