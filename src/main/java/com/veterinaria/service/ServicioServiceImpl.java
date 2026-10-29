package com.veterinaria.service;
import com.veterinaria.domain.Servicio;
import com.veterinaria.repository.ServicioRepository;
import org.springframework.stereotype.Service;
@Service
public class ServicioServiceImpl implements ServicioService {
 private final ServicioRepository datos;
 public ServicioServiceImpl(ServicioRepository datos) {this.datos=datos;}
 @Override public java.util.List<Servicio> listar() {return datos.findAll();}
@Override public java.util.Optional<Servicio> buscar(Integer id) {return datos.findById(id);}
@Override public Servicio guardar(Servicio registro) {return datos.save(registro);}
@Override public void eliminar(Integer id) {datos.deleteById(id);}
}
