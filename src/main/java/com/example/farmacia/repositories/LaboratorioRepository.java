package com.example.farmacia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmacia.models.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    
    List<Laboratorio> findByNombreContainingIgnoreCase(String nombre);
    List<Laboratorio> findByPais(String pais);
}