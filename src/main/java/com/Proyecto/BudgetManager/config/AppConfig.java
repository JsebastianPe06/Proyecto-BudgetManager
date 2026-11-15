package com.Proyecto.BudgetManager.config;

import java.time.Clock;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {
    @Bean public Clock clock() { return Clock.systemDefaultZone(); }
}
