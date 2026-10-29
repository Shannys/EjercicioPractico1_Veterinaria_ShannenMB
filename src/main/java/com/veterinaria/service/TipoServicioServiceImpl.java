package com.veterinaria.service;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.TipoServicioRepository;
import org.springframework.stereotype.Service;
@Service
public class TipoServicioServiceImpl implements TipoServicioService {
 private final TipoServicioRepository datos;
 public TipoServicioServiceImpl(TipoServicioRepository datos) {this.datos=datos;}
 @Override public java.util.List<TipoServicio> listar() {return datos.findAll();}
@Override public java.util.Optional<TipoServicio> buscar(Integer id) {return datos.findById(id);}
@Override public TipoServicio guardar(TipoServicio registro) {return datos.save(registro);}
@Override public void eliminar(Integer id) {datos.deleteById(id);}
}
