package com.veterinaria.service;
import com.veterinaria.domain.Cita;
import com.veterinaria.repository.CitaRepository;
import org.springframework.stereotype.Service;
@Service
public class CitaServiceImpl implements CitaService {
 private final CitaRepository datos;
 public CitaServiceImpl(CitaRepository datos) {this.datos=datos;}
 @Override public java.util.List<Cita> listar() {return datos.findAll();}
@Override public java.util.Optional<Cita> buscar(Integer id) {return datos.findById(id);}
}
