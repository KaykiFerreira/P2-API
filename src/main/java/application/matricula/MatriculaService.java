package application.matricula;

import application.aluno.AlunoRepository;
import application.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;

@Service
public class MatriculaService {
    @Autowired private MatriculaRepository repo;
    @Autowired private AlunoRepository alunoRepo;
    @Autowired private CursoRepository cursoRepo;

    public MatriculaDTO insert(MatriculaInsertDTO dados) {
        var aluno = alunoRepo.findById(dados.idAluno())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        
        var curso = cursoRepo.findById(dados.idCurso())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        Matricula m = new Matricula();
        m.setAluno(aluno);
        m.setCurso(curso);
        m.setStatus(dados.status());
        m.setDataMatricula(LocalDate.now());

        return new MatriculaDTO(repo.save(m));
    }

    public Iterable<MatriculaDTO> getAll() {
        return repo.findAll().stream().map(MatriculaDTO::new).toList();
    }
}