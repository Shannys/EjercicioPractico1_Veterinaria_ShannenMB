package com.veterinaria.controllers;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.TipoServicioService;
import com.veterinaria.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.*;
@Controller @RequestMapping("/tipos")
public class TipoServicioController {
 private final TipoServicioService catalogo;
 private final ServicioService servicios;
 public TipoServicioController(TipoServicioService catalogo, ServicioService servicios){this.catalogo=catalogo;this.servicios=servicios;}
 @GetMapping public String listar(Model model){
   model.addAttribute("tipos",catalogo.listar());
   Map<Integer,Long> conteos=new HashMap<>();
   servicios.listar().forEach(s->conteos.merge(s.getTipoServicio().getId(),1L,Long::sum));
   model.addAttribute("conteos",conteos);return "tipos/listado";
 }
 @GetMapping("/nuevo") public String nuevo(Model model){model.addAttribute("tipo",new TipoServicio());return "tipos/formulario";}
 @GetMapping("/{id}/editar") public String editar(@PathVariable Integer id,Model model){
   Optional<TipoServicio> registro=catalogo.buscar(id);
   if(registro.isEmpty())return "redirect:/tipos";
   model.addAttribute("tipo",registro.get());return "tipos/formulario";
 }
 @PostMapping("/guardar") public String guardar(@ModelAttribute("tipo") TipoServicio entrada,RedirectAttributes aviso){
   if(entrada.getNombre()==null || entrada.getNombre().isBlank()){
     aviso.addFlashAttribute("error","Escribe el nombre del tipo de servicio.");return "redirect:/tipos";
   }
   catalogo.guardar(entrada);aviso.addFlashAttribute("mensaje","Tipo de servicio guardado correctamente.");return "redirect:/tipos";
 }
 @PostMapping("/{id}/eliminar") public String eliminar(@PathVariable Integer id,RedirectAttributes aviso){
   try{catalogo.eliminar(id);aviso.addFlashAttribute("mensaje","Tipo eliminado.");}
   catch(DataIntegrityViolationException e){aviso.addFlashAttribute("error","No se puede eliminar un tipo que tiene servicios asociados.");}
   return "redirect:/tipos";
 }
}
