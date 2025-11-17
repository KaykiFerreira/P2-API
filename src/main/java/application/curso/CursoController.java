package application.curso;

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
@RequestMapping("/cursos")
public class CursoController {
    @Autowired private CursoService service;

    @GetMapping
    public Iterable<CursoDTO> getAll() { return service.getAll(); }
    @GetMapping("/{id}")
    public CursoDTO getOne(@PathVariable long id) { return service.getOne(id); }
    @PostMapping
    public CursoDTO insert(@RequestBody CursoInsertDTO dados) { return service.insert(dados); }
    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable long id, @RequestBody CursoInsertDTO dados) {
        return service.update(id, dados);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
