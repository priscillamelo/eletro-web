package com.tads.projeto_eletrodomesticos.controller;

import com.tads.projeto_eletrodomesticos.domain.Eletrodomestico;
import com.tads.projeto_eletrodomesticos.repository.EletrodomesticoRepository;
import com.tads.projeto_eletrodomesticos.service.EletrodomesticoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private final EletrodomesticoService service;

    public UserController(EletrodomesticoService eletrodomesticoService) {
        this.service = eletrodomesticoService;
    }

    List<Eletrodomestico> carrinho = new ArrayList<>();

    @GetMapping({ "/", "/index" })
    public String getIndex(Model model, HttpServletResponse response) {
        Date dataVisita = new Date();
        var formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatoData.format(dataVisita);

        Cookie cookie = new Cookie("visita", dataFormatada);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);

        List<Eletrodomestico> produtos = service.listProdutosNotDeleted();

        model.addAttribute("produtoEletro", produtos);

        return "index";
    }

    @GetMapping("adicionarCarrinho/{id}")
    public String addCarrinho(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Eletrodomestico produto = service.findById(id);
        System.out.println(produto);
        carrinho.add(produto);
        session.setAttribute("carrinho", carrinho);
        return "index";
    }

    @GetMapping("verCarrinho")
    public String getCarrinho(HttpServletRequest request, Model model) {
        HttpSession carrinhoSession = request.getSession();

        List<Eletrodomestico> listaCarrinho = (List<Eletrodomestico>) carrinhoSession.getAttribute("carrinho");

        if (listaCarrinho != null) {
            model.addAttribute("carrinho", listaCarrinho);
            return "carrinho";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/finalizarCompra")
    public String finalizaCompra(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }
}
