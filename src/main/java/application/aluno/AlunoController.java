package application.aluno;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository repo; // Para simplificar, usando repo direto, mas Service é recomendado
    @PostMapping
    @Operation(summary = "Cadastrar Aluno")
    public AlunoDTO insert(@RequestBody AlunoInsertDTO dados) {
        return new AlunoDTO(repo.save(new Aluno(dados)));
    }

    @PutMapping("/{id}")
    public AlunoDTO update(@PathVariable Long id, @RequestBody AlunoInsertDTO dados) {
        var opt = repo.findById(id);
        if (opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Aluno a = opt.get();
        a.setNome(dados.nome());
        a.setEmail(dados.email());
        a.setTelefone(dados.telefone());
        repo.save(a);
        return new AlunoDTO(a);
    }

    @GetMapping
    @Operation(summary = "Listar Alunos")
    public Iterable<AlunoDTO> getAll() {
        return repo.findAll().stream().map(AlunoDTO::new).toList();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repo.deleteById(id);
    }
}