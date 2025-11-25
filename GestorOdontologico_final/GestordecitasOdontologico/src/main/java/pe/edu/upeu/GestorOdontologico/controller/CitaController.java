
package pe.edu.upeu.GestorOdontologico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import pe.edu.upeu.GestorOdontologico.model.Cita;
import pe.edu.upeu.GestorOdontologico.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.findAll();
    }

    @PostMapping
    public Cita guardar(@RequestBody Cita c) {
        return service.save(c);
    }

    @DeleteMapping("/<built-in function id>")
    public void eliminar(@PathVariable Long id) {
        service.deleteById(id);
    }
}
