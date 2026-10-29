package com.veterinaria.service;
import com.veterinaria.domain.Cita;
public interface CitaService {
java.util.List<Cita> listar();
java.util.Optional<Cita> buscar(Integer id);
}
