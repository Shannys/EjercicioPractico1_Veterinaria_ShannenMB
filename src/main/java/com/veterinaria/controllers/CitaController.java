package com.veterinaria.controllers;
import com.veterinaria.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller public class CitaController {
 private final CitaService agenda;
 public CitaController(CitaService agenda){this.agenda=agenda;}
 @GetMapping("/citas") public String listar(Model model){model.addAttribute("citas",agenda.listar());return "citas/listado";}
}
