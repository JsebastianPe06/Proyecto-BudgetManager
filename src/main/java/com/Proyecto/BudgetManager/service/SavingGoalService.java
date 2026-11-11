package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.AporteValid;
import com.Proyecto.BudgetManager.domain.MetaValid;
import com.Proyecto.BudgetManager.entity.AporteAhorro;
import com.Proyecto.BudgetManager.entity.MetaAhorro;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.repository.AporteAhorroRepository;
import com.Proyecto.BudgetManager.repository.MetaAhorroRepository;
import java.math.BigDecimal;

public class SavingGoalService {
        
    MetaValid metaValid = new MetaValid();
    AporteValid aporteValid = new AporteValid();;
    
    public void crearMeta(int userId, String name, BigDecimal objetivo, fecha fechaInicial, fecha fechaFinal)
    {
        MetaAhorro meta = new MetaAhorro(0, userId, name, objetivo, fechaInicial, fechaFinal);
        
        metaValid.validar(meta);

        //MetaAhorroRepository.guardar(meta);
    }

    public void registrarAporte(int metaId, BigDecimal monto, fecha fecha)
    {
        AporteAhorro aporte = new AporteAhorro(0, metaId, monto, fecha);
        
        aporteValid.validar(aporte);

        //AporteAhorroRepository.guardar(aporte);
    }

    public void evaluarProgresoMeta()
    {
        
    }

    public void getMetaResumen()
    {
        
    }
}
