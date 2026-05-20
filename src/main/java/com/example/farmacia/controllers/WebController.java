package com.example.farmacia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.farmacia.models.Laboratorio;
import com.example.farmacia.models.Medicamento;
import com.example.farmacia.services.FarmaciaService;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private FarmaciaService farmaciaService;

    // ===== LABORATORIOS =====
    @GetMapping("/laboratorios")
    public String listaLaboratorios(@RequestParam(required = false) String nombre,
                                     @RequestParam(required = false) String pais,
                                     Model model) {
        if (nombre != null && !nombre.isEmpty()) {
            model.addAttribute("laboratorios", farmaciaService.getLaboratoriosByNombre(nombre));
        } else if (pais != null && !pais.isEmpty()) {
            model.addAttribute("laboratorios", farmaciaService.getLaboratoriosByPais(pais));
        } else {
            model.addAttribute("laboratorios", farmaciaService.getAllLaboratorios());
        }
        return "laboratorios/lista";
    }

    @GetMapping("/laboratorios/{id}")
    public String detalleLaboratorio(@PathVariable Long id, Model model) {
        farmaciaService.getLaboratorioById(id).ifPresent(lab -> {
            model.addAttribute("laboratorio", lab);
            model.addAttribute("medicamentos", farmaciaService.getMedicamentosByLaboratorio(id));
        });
        return "laboratorios/detalle";
    }

    @GetMapping("/laboratorios/nueva")
    public String nuevaLaboratorioForm(Model model) {
        model.addAttribute("laboratorio", new Laboratorio());
        return "laboratorios/nueva";
    }

    @PostMapping("/laboratorios/nueva")
    public String guardarLaboratorio(@ModelAttribute Laboratorio laboratorio) {
        farmaciaService.saveLaboratorio(laboratorio);
        return "redirect:/web/laboratorios";
    }

    @GetMapping("/laboratorios/editar/{id}")
    public String editarLaboratorioForm(@PathVariable Long id, Model model) {
        farmaciaService.getLaboratorioById(id).ifPresent(lab ->
                model.addAttribute("laboratorio", lab));
        return "laboratorios/editar";
    }

    @PostMapping("/laboratorios/editar/{id}")
    public String actualizarLaboratorio(@PathVariable Long id,
                                         @ModelAttribute Laboratorio laboratorio) {
        farmaciaService.getLaboratorioById(id).ifPresent(existing -> {
            existing.setNombre(laboratorio.getNombre());
            existing.setPais(laboratorio.getPais());
            existing.setWeb(laboratorio.getWeb());
            farmaciaService.saveLaboratorio(existing);
        });
        return "redirect:/web/laboratorios";
    }

    @PostMapping("/laboratorios/borrar/{id}")
    public String borrarLaboratorio(@PathVariable Long id) {
        farmaciaService.deleteLaboratorio(id);
        return "redirect:/web/laboratorios";
    }

    // ===== MEDICAMENTOS =====
    @GetMapping("/medicamentos")
    public String listaMedicamentos(@RequestParam(required = false) String principioActivo,
                                     @RequestParam(required = false) Boolean conReceta,
                                     Model model) {
        if (principioActivo != null && !principioActivo.isEmpty()) {
            model.addAttribute("medicamentos", farmaciaService.getMedicamentosByPrincipioActivo(principioActivo));
        } else if (conReceta != null) {
            model.addAttribute("medicamentos", farmaciaService.getMedicamentosByConReceta(conReceta));
        } else {
            model.addAttribute("medicamentos", farmaciaService.getAllMedicamentos());
        }
        return "medicamentos/lista";
    }

    @GetMapping("/medicamentos/nueva")
    public String nuevoMedicamentoForm(Model model) {
        model.addAttribute("medicamento", new Medicamento());
        model.addAttribute("laboratorios", farmaciaService.getAllLaboratorios());
        return "medicamentos/nueva";
    }

    @PostMapping("/medicamentos/nueva")
    public String guardarMedicamento(@ModelAttribute Medicamento medicamento,
                                      @RequestParam Long laboratorioId) {
        farmaciaService.getLaboratorioById(laboratorioId).ifPresent(medicamento::setLaboratorio);
        farmaciaService.saveMedicamento(medicamento);
        return "redirect:/web/medicamentos";
    }

    @GetMapping("/medicamentos/editar/{id}")
    public String editarMedicamentoForm(@PathVariable Long id, Model model) {
        farmaciaService.getMedicamentoById(id).ifPresent(med ->
                model.addAttribute("medicamento", med));
        model.addAttribute("laboratorios", farmaciaService.getAllLaboratorios());
        return "medicamentos/editar";
    }
    //No a la Explotacion :(
    @PostMapping("/medicamentos/editar/{id}")
    public String actualizarMedicamento(@PathVariable Long id,
                                         @ModelAttribute Medicamento medicamento,
                                         @RequestParam Long laboratorioId) {
        farmaciaService.getMedicamentoById(id).ifPresent(existing -> {
            existing.setNombre(medicamento.getNombre());
            existing.setPrincipioActivo(medicamento.getPrincipioActivo());
            existing.setFormato(medicamento.getFormato());
            existing.setPrecioEuros(medicamento.getPrecioEuros());
            existing.setConReceta(medicamento.getConReceta());
            farmaciaService.getLaboratorioById(laboratorioId).ifPresent(existing::setLaboratorio);
            farmaciaService.saveMedicamento(existing);
        });
        return "redirect:/web/medicamentos";
    }

    @PostMapping("/medicamentos/borrar/{id}")
    public String borrarMedicamento(@PathVariable Long id) {
        farmaciaService.deleteMedicamento(id);
        return "redirect:/web/medicamentos";
    }
}