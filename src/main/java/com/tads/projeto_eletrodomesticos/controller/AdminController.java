package com.tads.projeto_eletrodomesticos.controller;

import com.tads.projeto_eletrodomesticos.domain.Eletrodomestico;
import com.tads.projeto_eletrodomesticos.service.EletrodomesticoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    private final EletrodomesticoService service;

    public AdminController(EletrodomesticoService eletrodomesticoService) {
        this.service = eletrodomesticoService;
    }

    @GetMapping("/admin")
    public String doPageAdminIndex(Model model) {
        List<Eletrodomestico> produtos = service.listProdutosNotDeleted();
        model.addAttribute("produtoEletro", produtos);
        return "indexAdmin";
    }

    @GetMapping("/cadastrar")
    public String getEletrodomesticoCadastro(Model model) {
        Eletrodomestico eletrodomestico = new Eletrodomestico();
        model.addAttribute("eletrodomestico", eletrodomestico);
        return "cadastro";
    }

    @PostMapping("salvar")
    public String doSalvarEletrodomestico(@ModelAttribute @Valid Eletrodomestico eletrodomestico, Errors errors) {
        if (errors.hasErrors()) {
            return "cadastro";
        } else {
            service.createProduto(eletrodomestico);
            return "redirect:/admin";
        }
    }

    @GetMapping("editar/{id}")
    public String doEditar(Model model, @PathVariable Long id) {
        Eletrodomestico eletrodomestico = service.findById(id);
        model.addAttribute("eletrodomestico", eletrodomestico);
        service.updateProduto(eletrodomestico);

        return "cadastro";
    }

    @GetMapping("deletar/{id}")
    public String doDeletar(@PathVariable Long id) {
        service.deleteProduto(id);

        return "redirect:/admin";
    }
}
