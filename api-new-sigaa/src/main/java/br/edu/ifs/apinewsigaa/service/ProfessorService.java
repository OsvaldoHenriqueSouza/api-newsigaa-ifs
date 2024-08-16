package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public List<ProfessorDto> getProfessores() {
        List<ProfessorModel> professores = professorRepository.findAll();
        return professores.stream().map(ProfessorModel::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProfessorDto getProfessorPorId(int id) {
        Optional<ProfessorModel> professorOptional = professorRepository.findById(id);
        ProfessorModel professorModel = professorOptional.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado: " + id));
        return professorModel.toDto();
    }

    @Transactional
    public ProfessorDto registroProfessor(ProfessorModel professor) {
        try {
            return professorRepository.save(professor).toDto();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao salvar professor: " + e.getMessage());
        }
    }

    @Transactional
    public void updateProfessor(ProfessorModel professor) {
        try {
            Optional<ProfessorModel> professorOptional = professorRepository.findById(professor.getId());
            if (professorOptional.isPresent()) {
                professorRepository.save(professor);
            } else {
                throw new ObjectNotFoundException("Professor não encontrado: " + professor.getNome());
            }
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteProfessorPorId(int id) {
        if (!verificaExistenciaProfessor(id)) {
            throw new ObjectNotFoundException("Professor não encontrado: " + id);
        }

        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao deletar professor: " + e.getMessage());
        }
    }

//    {
//        "professor": {
//        "matricula": "PROF12345",
//                "nome": "Glauco Luiz Rezende de Carvalho",
//                "cpf": "009.717.570-62",
//                "email": "glauco.carvalho@academico.ifs.edu.br",
//                "dataNascimento": "1979-10-09",
//                "celular": "(79)98113-0366"
//    },
//        "disciplinas": [
//        {
//            "id": 1,
//                "nome": "Programação",
//                "numeroCreditos": 4
//        },
//        {
//            "id": 2,
//                "nome": "Banco de Dados",
//                "numeroCreditos": 3
//        },
//        {
//            "id": 3,
//                "nome": "Estrutura de Dados",
//                "numeroCreditos": 4
//        }
//  ]
//    }

    // o metodo a seguir deve retornar o professor e as disciplinas que ele leciona
    // o json acima é um exemplo de como deve ser o retorno

//    @Query(value = """
//            SELECT p.matricula, p.nome, p.cpf, p.email, p.dataNascimento, p.celular,
//                d.id AS disciplinaId, d.nome AS disciplinaNome, d.numeroCredito
//            FROM professor p
//            INNER JOIN turma t ON p.id = t.idProfessor
//            INNER JOIN disciplina d ON t.idDisciplina = d.id
//            WHERE p.matricula = :matricula
//            """, nativeQuery = true)


    @Transactional(readOnly = true)
    public ProfessorModel getProfessorPorMatricula(String matricula) {
        return professorRepository.findByMatricula(matricula)
                .orElseThrow(() -> new ObjectNotFoundException("Professor com a matrícula: " + matricula +  " não encontrado"));
    }

    @Transactional(readOnly = true)
    public ProfessorDisciplinasDto getProfessorDisciplinas(String matricula) {
        ProfessorModel professor = getProfessorPorMatricula(matricula);

        List<DisciplinaDto> disciplinasProfessor = professor.getDisciplinas()
                .stream()
                .map(DisciplinaModel::toDto)
                .collect(Collectors.toList());

        ProfessorDisciplinasDto professorDisciplinasDto = new ProfessorDisciplinasDto();
        professorDisciplinasDto.setProfessor(professor.toDto());
        professorDisciplinasDto.setDisciplinas(disciplinasProfessor);

        return professorDisciplinasDto;
    }

//    {
//        "professor": {
//        "matricula": "PROF12345",
//                "nome": "Glauco Luiz Rezende de Carvalho",
//                "cpf": "009.717.570-62",
//                "email": "glauco.carvalho@academico.ifs.edu.br",
//                "dataNascimento": "1979-10-09",
//                "celular": "(79)98113-0366"
//    },
//        "disciplinas": [
//        {
//            "disciplina": {
//            "id": 1,
//                    "nome": "Programação",
//                    "numeroCreditos": 4
//        },
//            "alunos": [
//            {
//                "id": 1,
//                    "nome": "Ana Paula da Silva",
//                    "cpf": "108.887.090-21",
//                    "email": "ana.silva@academico.ifs.edu.br",
//                    "dataNascimento": "2000-05-12",
//                    "celular": "(79)98765-4321",
//                    "apelido": "Ana",
//                    "matricula": "ALU2020001"
//            },
//            {
//                "id": 2,
//                    "nome": "Carlos Eduardo Pereira",
//                    "cpf": "469.062.520-46",
//                    "email": "carlos.pereira@academico.ifs.edu.br",
//                    "dataNascimento": "1999-10-20",
//                    "celular": "(79)99876-5432",
//                    "apelido": "Cadu",
//                    "matricula": "ALU2020002"
//            }
//      ]
//        },
//        {
//            "disciplina": {
//            "id": 2,
//                    "nome": "Banco de Dados",
//                    "numeroCreditos": 3
//        },
//            "alunos": [
//            {
//                "id": 3,
//                    "nome": "Maria Fernanda Costa",
//                    "cpf": "961.508.410-73",
//                    "email": "maria.costa@academico.ifs.edu.br",
//                    "dataNascimento": "2001-03-15",
//                    "celular": "(79)98765-1234",
//                    "apelido": "Mari",
//                    "matricula": "ALU2020003"
//            },
//            {
//                "id": 4,
//                    "nome": "João Pedro Almeida",
//                    "cpf": "474.468.310-06",
//                    "email": "joao.almeida@academico.ifs.edu.br",
//                    "dataNascimento": "1998-12-05",
//                    "celular": "(79)99876-5432",
//                    "apelido": "JP",
//                    "matricula": "ALU2020004"
//            }
//      ]
//        },
//        {
//            "disciplina": {
//            "id": 3,
//                    "nome": "Estrutura de Dados",
//                    "numeroCreditos": 4
//        },
//            "alunos": [
//            {
//                "id": 2,
//                    "nome": "Carlos Eduardo Pereira",
//                    "cpf": "801.879.720-01",
//                    "email": "carlos.pereira@academico.ifs.edu.br",
//                    "dataNascimento": "1999-10-20",
//                    "celular": "(79)99876-5432",
//                    "apelido": "Cadu",
//                    "matricula": "ALU2020002"
//            },
//            {
//                "id": 5,
//                    "nome": "Luiza Martins",
//                    "cpf": "460.454.960-51",
//                    "email": "luiza.martins@academico.ifs.edu.br",
//                    "dataNascimento": "2000-09-10",
//                    "celular": "(79)98765-4321",
//                    "apelido": "Lu",
//                    "matricula": "ALU2020005"
//            }
//      ]
//        }
//  ]
//    }

    @Transactional(readOnly = true)
    public ProfessorDisciplinasAlunosDto getProfessorDisciplinasAlunos(String matricula) {
        ProfessorModel professor = getProfessorPorMatricula(matricula);

        ProfessorDisciplinasAlunosDto professorDisciplinasAlunosDto = new ProfessorDisciplinasAlunosDto();
        professorDisciplinasAlunosDto.setProfessor(professor.toDto());

        List<DisciplinaComAlunosDto> disciplinasComAlunos = professor.getDisciplinas()
                .stream()
                .map(disciplina -> {
                    DisciplinaComAlunosDto disciplinaComAlunosDto = new DisciplinaComAlunosDto();
                    disciplinaComAlunosDto.setDisciplina(disciplina.toDto());

                    List<AlunoDto> alunos = disciplina.getAlunos()
                            .stream()
                            .map(AlunoModel::toDto)
                            .collect(Collectors.toList());

                    disciplinaComAlunosDto.setAlunos(alunos);
                    return disciplinaComAlunosDto;
                }).collect(Collectors.toList());

        professorDisciplinasAlunosDto.setDisciplinas(disciplinasComAlunos);
        return professorDisciplinasAlunosDto;

    }


    public boolean verificaExistenciaProfessor(int id) {
        return professorRepository.existsById(id);
    }

}
