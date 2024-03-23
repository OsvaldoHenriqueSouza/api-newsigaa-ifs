package br.edu.ifs.apinewsigaa.repository.Aluno;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
    List<AlunoModel> findByEmail(String email);
    List<AlunoModel> findByMatricula(int matricula);
}
