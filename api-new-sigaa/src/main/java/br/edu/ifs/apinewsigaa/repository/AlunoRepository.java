package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
    Optional<AlunoModel> findByEmail(String email);

    Optional<AlunoModel> findByMatricula(String matricula);

    void deleteByMatricula(String matricula);

    Boolean existsByMatricula(String matricula);

    Boolean existsByCpf(String cpf);
}
