package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.DisciplinaDto;
import br.edu.ifs.apinewsigaa.rest.Dtos.ProfessorDisciplinasDto;
import br.edu.ifs.apinewsigaa.rest.Dtos.ProfessorDto;
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


    public boolean verificaExistenciaProfessor(int id) {
        return professorRepository.existsById(id);
    }

}
