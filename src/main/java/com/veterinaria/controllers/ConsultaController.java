package com.veterinaria.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller public class ConsultaController {
 @GetMapping("/consultas") public String verFormulario(){return "consultas";}
 @PostMapping("/consultas") public String recibirConsulta(@RequestParam String nombre,@RequestParam String correo,@RequestParam String mascota,@RequestParam String mensaje,RedirectAttributes aviso){
  if(nombre.isBlank()||correo.isBlank()||mascota.isBlank()||mensaje.isBlank()){
   aviso.addFlashAttribute("error","Completa todos los campos.");
  }else{aviso.addFlashAttribute("mensaje","¡Gracias por tu consulta, "+nombre+"! Hemos recibido tu mensaje.");}
  return "redirect:/consultas";
 }
}
