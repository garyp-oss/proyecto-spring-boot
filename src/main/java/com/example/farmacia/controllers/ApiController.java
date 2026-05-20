package com.example.farmacia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmacia.models.Laboratorio;
import com.example.farmacia.models.Medicamento;
import com.example.farmacia.services.FarmaciaService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FarmaciaService farmaciaService;

    // ===== LABORATORIOS =====
    @GetMapping("/laboratorios")
    public List<Laboratorio> getLaboratorios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String pais) {

        if (nombre != null) return farmaciaService.getLaboratoriosByNombre(nombre);
        if (pais != null) return farmaciaService.getLaboratoriosByPais(pais);
        return farmaciaService.getAllLaboratorios();
    }

    @GetMapping("/laboratorios/{id}")
    public ResponseEntity<Laboratorio> getLaboratorioById(@PathVariable Long id) {
        return farmaciaService.getLaboratorioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/laboratorios")
    public Laboratorio createLaboratorio(@RequestBody Laboratorio laboratorio) {
        return farmaciaService.saveLaboratorio(laboratorio);
    }

    @PutMapping("/laboratorios/{id}")
    public ResponseEntity<Laboratorio> updateLaboratorio(@PathVariable Long id,
                                                          @RequestBody Laboratorio laboratorio) {
        return farmaciaService.getLaboratorioById(id).map(existing -> {
            existing.setNombre(laboratorio.getNombre());
            existing.setPais(laboratorio.getPais());
            existing.setWeb(laboratorio.getWeb());
            return ResponseEntity.ok(farmaciaService.saveLaboratorio(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/laboratorios/{id}")
    public ResponseEntity<Void> deleteLaboratorio(@PathVariable Long id) {
        if (farmaciaService.getLaboratorioById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        farmaciaService.deleteLaboratorio(id);
        return ResponseEntity.noContent().build();
    }

    // ===== MEDICAMENTOS =====
    @GetMapping("/medicamentos")
    public List<Medicamento> getMedicamentos(
            @RequestParam(required = false) String principioActivo,
            @RequestParam(required = false) Boolean conReceta,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax) {

        if (principioActivo != null) return farmaciaService.getMedicamentosByPrincipioActivo(principioActivo);
        if (conReceta != null) return farmaciaService.getMedicamentosByConReceta(conReceta);
        if (precioMin != null && precioMax != null) return farmaciaService.getMedicamentosByPrecio(precioMin, precioMax);
        return farmaciaService.getAllMedicamentos();
    }

    @GetMapping("/medicamentos/{id}")
    public ResponseEntity<Medicamento> getMedicamentoById(@PathVariable Long id) {
        return farmaciaService.getMedicamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/medicamentos")
    public Medicamento createMedicamento(@RequestBody Medicamento medicamento) {
        return farmaciaService.saveMedicamento(medicamento);
    }

    @PutMapping("/medicamentos/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable Long id,
                                                          @RequestBody Medicamento medicamento) {
        return farmaciaService.getMedicamentoById(id).map(existing -> {
            existing.setNombre(medicamento.getNombre());
            existing.setPrincipioActivo(medicamento.getPrincipioActivo());
            existing.setFormato(medicamento.getFormato());
            existing.setPrecioEuros(medicamento.getPrecioEuros());
            existing.setConReceta(medicamento.getConReceta());
            existing.setLaboratorio(medicamento.getLaboratorio());
            return ResponseEntity.ok(farmaciaService.saveMedicamento(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/medicamentos/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        if (farmaciaService.getMedicamentoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        farmaciaService.deleteMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}