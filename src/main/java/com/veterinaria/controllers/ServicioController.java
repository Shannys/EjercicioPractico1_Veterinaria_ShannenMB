package com.veterinaria.controllers;
import com.veterinaria.domain.Servicio;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.ServicioService;
import com.veterinaria.service.TipoServicioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;
@Controller @RequestMapping("/servicios")
public class ServicioController {
 private final ServicioService catalogo;
 private final TipoServicioService tipos;
 public ServicioController(ServicioService catalogo,TipoServicioService tipos){this.catalogo=catalogo;this.tipos=tipos;}
 @GetMapping public String listar(Model model){model.addAttribute("servicios",catalogo.listar());return "servicios/listado";}
 @GetMapping("/nuevo") public String nuevo(Model model){Servicio nuevo=new Servicio();nuevo.setTipoServicio(new TipoServicio());model.addAttribute("servicio",nuevo);model.addAttribute("tipos",tipos.listar());return "servicios/formulario";}
 @GetMapping("/{id}/editar") public String editar(@PathVariable Integer id,Model model){
   Optional<Servicio> actual=catalogo.buscar(id);
   if(actual.isEmpty())return "redirect:/servicios";
   model.addAttribute("servicio",actual.get());model.addAttribute("tipos",tipos.listar());return "servicios/formulario";
 }
 @PostMapping("/guardar") public String guardar(@ModelAttribute("servicio") Servicio entrada,RedirectAttributes aviso){
   if(entrada.getNombre()==null||entrada.getNombre().isBlank()||entrada.getPrecio()==null||entrada.getPrecio().signum()<0||entrada.getDuracionMinutos()==null||entrada.getDuracionMinutos()<=0||entrada.getTipoServicio()==null||entrada.getTipoServicio().getId()==null){
      aviso.addFlashAttribute("error","Completa correctamente todos los campos.");return "redirect:/servicios";
   }
   Optional<TipoServicio> tipoElegido=tipos.buscar(entrada.getTipoServicio().getId());
   if(tipoElegido.isEmpty()){aviso.addFlashAttribute("error","El tipo de servicio seleccionado no existe.");return "redirect:/servicios";}
   entrada.setTipoServicio(tipoElegido.get());catalogo.guardar(entrada);
   aviso.addFlashAttribute("mensaje","Servicio guardado correctamente.");return "redirect:/servicios";
 }
 @PostMapping("/{id}/eliminar") public String eliminar(@PathVariable Integer id,RedirectAttributes aviso){
   try{catalogo.eliminar(id);aviso.addFlashAttribute("mensaje","Servicio eliminado.");}
   catch(DataIntegrityViolationException e){aviso.addFlashAttribute("error","No se puede eliminar un servicio con citas asociadas.");}
   return "redirect:/servicios";
 }
}
