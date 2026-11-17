package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.domain.domainTransaction.ImpactoDeGasto;
import com.Proyecto.BudgetManager.dto.TransactionRequest;
import com.Proyecto.BudgetManager.entity.TransactionEntity;
import com.Proyecto.BudgetManager.enums.Category;
import com.Proyecto.BudgetManager.enums.Priority;
import com.Proyecto.BudgetManager.enums.TransactionType;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.service.TransactionService;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String list(Authentication auth, Model model) {
        try {
            var transacciones = transactionService.findByEmail(auth.getName());
            var saldo = transactionService.calcularSaldoActual(auth.getName());
            model.addAttribute("transactions", transacciones);
            // Si el saldo es nulo, enviamos cero
            model.addAttribute("saldo", saldo != null ? saldo : java.math.BigDecimal.ZERO);
            return "transactions/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar transacciones: " + e.getMessage());
            return "transactions/list";
        }
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("form", new TransactionRequest(
                TransactionType.GASTO, null, Category.NECESIDADES, Priority.MEDIA, LocalDate.now(), false,
                false, null, null, null
        ));
        model.addAttribute("types", TransactionType.values());
        model.addAttribute("categories", Category.values());
        model.addAttribute("priorities", Priority.values());
        return "transactions/form";
    }

    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("form") TransactionRequest req,
                    BindingResult errors, Authentication auth, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("types", TransactionType.values());
            model.addAttribute("categories", Category.values());
            model.addAttribute("priorities", Priority.values());
            return "transactions/form";
        }
        try {
            TransactionEntity entity = new TransactionEntity();
            entity.setType(req.type());
            entity.setAmount(req.amount());
            entity.setCategory(req.category());
            entity.setPriority(req.priority());
            entity.setTransactionDate(req.transactionDate().toString());
            entity.setPlanned(req.planned() != null && req.planned());
            entity.setRecurrent(req.recurrent() != null && req.recurrent());
            entity.setRecurrentDay(req.recurrentDay());
            entity.setSatisfied(req.satisfied() != null ? req.satisfied() : true);
            entity.setReflectionNote(req.reflectionNote());
            ImpactoDeGasto impacto = transactionService.registrar(auth.getName(), entity);
            // Si fue gasto y quedó en riesgo, redirigimos con advertencia
            if (impacto != null && impacto.getEstado() == ImpactoDeGasto.Estado.EN_RIESGO) {
                return "redirect:/transactions?warning=riesgo";
            }
            return "redirect:/transactions?ok";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("types", TransactionType.values());
            model.addAttribute("categories", Category.values());
            model.addAttribute("priorities", Priority.values());
            return "transactions/form";
        }
    }
}
