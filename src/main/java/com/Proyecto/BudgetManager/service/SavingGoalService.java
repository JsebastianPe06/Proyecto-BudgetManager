package com.Proyecto.BudgetManager.service;


import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;
import com.Proyecto.BudgetManager.domain.domainMeta.AporteAhorro;
import com.Proyecto.BudgetManager.domain.domainMeta.AporteValid;
import com.Proyecto.BudgetManager.domain.domainMeta.MetaAhorro;
import com.Proyecto.BudgetManager.domain.domainMeta.MetaValid;
import java.math.BigDecimal;

public class SavingGoalService {
        
    MetaValid metaValid = new MetaValid();
    AporteValid aporteValid = new AporteValid();;
    
    public void crearMeta(int userId, String name, BigDecimal objetivo, Fecha fechaInicial, Fecha fechaFinal)
    {
        MetaAhorro meta = new MetaAhorro(0, userId, name, objetivo, fechaInicial, fechaFinal);
        
        metaValid.validar(meta);

        //MetaAhorroRepository.guardar(meta);
    }

    public void registrarAporte(int metaId, BigDecimal monto, Fecha fecha)
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
