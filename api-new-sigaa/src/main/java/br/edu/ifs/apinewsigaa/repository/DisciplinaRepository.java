package br.edu.ifs.apinewsigaa.repository;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Integer> {
    Boolean existsByNome(String nome);

    Optional<DisciplinaModel> findByNome(String nome);

    void deleteByNome(String nome);
}
