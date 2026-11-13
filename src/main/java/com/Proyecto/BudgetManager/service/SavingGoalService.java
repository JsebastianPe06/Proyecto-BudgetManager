package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.AporteValid;
import com.Proyecto.BudgetManager.domain.DateManager;
import com.Proyecto.BudgetManager.domain.MetaValid;
import com.Proyecto.BudgetManager.dto.MetaForm;
import com.Proyecto.BudgetManager.dto.AporteForm;
import com.Proyecto.BudgetManager.entity.AporteAhorro;
import com.Proyecto.BudgetManager.entity.MetaAhorro;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.repository.AporteAhorroRepository;
import com.Proyecto.BudgetManager.repository.MetaAhorroRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class SavingGoalService {
        
    MetaValid metaValid = new MetaValid();
    AporteValid aporteValid = new AporteValid();
    
    private static final BigDecimal CIEN = new BigDecimal("100");
    
    public void crearMeta(int userId, MetaForm form)
    {
        fecha dateInicial = DateManager.aFecha(form.getFechaInicial());
        fecha dateFinal = DateManager.aFecha(form.getFechaFinal());
        
        MetaAhorro meta = new MetaAhorro(0, userId, form.getNombre(), form.getObjetivo(), dateInicial ,dateFinal );
        
        metaValid.validar(meta);

        //MetaAhorroRepository.guardar(meta);
    }

    public void registrarAporte(int userId, AporteForm form)
    {
        fecha date = DateManager.aFecha(form.getFecha());
        
        MetaAhorro meta = MetaAhorroRepository.findById(form.getMetaId()).orElseThrow();

        if (meta.getUserId() != userId) 
        {        
            throw new IllegalArgumentException("No puedes aportar a una meta de otro usuario");    
        }
        
        AporteAhorro aporte = new AporteAhorro(0,userId, form.getMetaId(), form.getMonto(),date);        
        aporteValid.validar(aporte);

        //AporteAhorroRepository.guardar(aporte);
    }

    
    public void getMetaResumen(int userId, int metaId)
    {
        BigDecimal totalAvance = sumarAportesMeta(userId, metaId);
        
        MetaAhorro meta = MetaAhorroRepository.findById(metaId);
        
        BigDecimal porcentaje = porcentajeProgreso(totalAvance,meta.getObjetivo());

        BigDecimal faltante = faltante(totalAvance,meta.getObjetivo());
        
    }
    
    //Calcula la suma total de los aportes de una meta
    private BigDecimal sumarAportesMeta(int userId, int metaId)
    {
        throw new UnsupportedOperationException("Falta AporteAhorroRepository.sumarAportesMeta (capa de datos)");
    }
    
    public BigDecimal porcentajeProgreso(BigDecimal acumulado, BigDecimal objetivo)
    {
        return acumulado.multiply(CIEN).divide(objetivo, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal faltante(BigDecimal acumulado, BigDecimal objetivo)
    {
        return objetivo.subtract(acumulado);
    }
}

/*Crear una meta.
Registrar aportes a una meta.
Consultar una meta.
Calcular cuánto se ha ahorrado.
Calcular el progreso de la meta.
Determinar si se alcanzó el objetivo.*/
