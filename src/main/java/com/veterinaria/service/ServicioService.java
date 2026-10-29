package com.veterinaria.service;
import com.veterinaria.domain.Servicio;
public interface ServicioService {
java.util.List<Servicio> listar();
java.util.Optional<Servicio> buscar(Integer id);
Servicio guardar(Servicio registro);
void eliminar(Integer id);
}
