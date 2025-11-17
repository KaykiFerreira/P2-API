package application.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired private AlunoService service;

    @GetMapping
    public Iterable<AlunoDTO> getAll() { return service.getAll(); }
    @GetMapping("/{id}")
    public AlunoDTO getOne(@PathVariable long id) { return service.getOne(id); }
    @PostMapping
    public AlunoDTO insert(@RequestBody AlunoInsertDTO dados) { return service.insert(dados); }
    @PutMapping("/{id}")
    public AlunoDTO update(@PathVariable long id, @RequestBody AlunoInsertDTO dados) {
        return service.update(id, dados);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}