package com.example.farmacia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmacia.models.Laboratorio;
import com.example.farmacia.models.Medicamento;
import com.example.farmacia.repositories.LaboratorioRepository;
import com.example.farmacia.repositories.MedicamentoRepository;

@Service
public class FarmaciaService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    // ===== LABORATORIO =====
    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioRepository.findAll();
    }

    public Optional<Laboratorio> getLaboratorioById(Long id) {
        return laboratorioRepository.findById(id);
    }

    public List<Laboratorio> getLaboratoriosByNombre(String nombre) {
        return laboratorioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Laboratorio> getLaboratoriosByPais(String pais) {
        return laboratorioRepository.findByPais(pais);
    }

    public Laboratorio saveLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public void deleteLaboratorio(Long id) {
        laboratorioRepository.deleteById(id);
    }

    // ===== MEDICAMENTO =====
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    public Optional<Medicamento> getMedicamentoById(Long id) {
        return medicamentoRepository.findById(id);
    }

    public List<Medicamento> getMedicamentosByLaboratorio(Long laboratorioId) {
        return medicamentoRepository.findByLaboratorioId(laboratorioId);
    }

    public List<Medicamento> getMedicamentosByPrincipioActivo(String principioActivo) {
        return medicamentoRepository.findByPrincipioActivoContainingIgnoreCase(principioActivo);
    }

    public List<Medicamento> getMedicamentosByConReceta(Boolean conReceta) {
        return medicamentoRepository.findByConReceta(conReceta);
    }

    public List<Medicamento> getMedicamentosByPrecio(Double min, Double max) {
        return medicamentoRepository.findByPrecioEurosBetween(min, max);
    }

    public Medicamento saveMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public void deleteMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }
}