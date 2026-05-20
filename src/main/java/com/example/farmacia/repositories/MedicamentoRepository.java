package com.example.farmacia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmacia.models.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    List<Medicamento> findByLaboratorioId(Long laboratorioId);
    List<Medicamento> findByPrincipioActivoContainingIgnoreCase(String principioActivo);
    List<Medicamento> findByConReceta(Boolean conReceta);
    List<Medicamento> findByPrecioEurosBetween(Double min, Double max);
}