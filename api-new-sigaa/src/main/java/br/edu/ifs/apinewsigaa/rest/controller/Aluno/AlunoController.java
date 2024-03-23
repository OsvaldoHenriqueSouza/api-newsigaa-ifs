package br.edu.ifs.apinewsigaa.rest.controller.Aluno;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.Aluno.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.Aluno.AlunoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity getAllAlunos() {
        var allAlunos = alunoRepository.findAll();
        return ResponseEntity.ok(allAlunos);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity getAluno(@PathVariable String email) {
//        var aluno = alunoRepository.findByEmail(email);
//        return ResponseEntity.ok(aluno);
//    }

    @GetMapping("/{matricula}")
    public ResponseEntity getAluno(@PathVariable int matricula) {
        var aluno = alunoRepository.findByMatricula(matricula);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity registerAluno(@RequestBody AlunoDto aluno) {
        AlunoModel alunoModel = new AlunoModel(aluno);
        alunoRepository.save(alunoModel);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAluno(@RequestBody AlunoDto aluno) {
        var optionalAluno = alunoRepository.findById(aluno.id());
        if (optionalAluno.isPresent()) {
            AlunoModel alunoModel = optionalAluno.get();
            alunoModel.setNome(aluno.nome());
            alunoModel.setCpf(aluno.cpf());
            alunoModel.setEmail(aluno.email());
            alunoModel.setDataNascimento(aluno.dataNascimento());
            alunoModel.setCelular(aluno.celular());
            alunoModel.setMatricula(aluno.matricula());
            alunoModel.setApelido(aluno.apelido());
            alunoRepository.save(alunoModel);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAluno(@PathVariable int id) {
        var optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            alunoRepository.delete(optionalAluno.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}