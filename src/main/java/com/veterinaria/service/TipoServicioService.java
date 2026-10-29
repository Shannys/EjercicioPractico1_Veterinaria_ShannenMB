package com.veterinaria.service;
import com.veterinaria.domain.TipoServicio;
public interface TipoServicioService {
java.util.List<TipoServicio> listar();
java.util.Optional<TipoServicio> buscar(Integer id);
TipoServicio guardar(TipoServicio registro);
void eliminar(Integer id);
}
