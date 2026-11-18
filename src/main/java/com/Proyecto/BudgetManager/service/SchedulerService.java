package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainTransaction.GeneradorDeRecurrentes;
import com.Proyecto.BudgetManager.entity.TransactionEntity;
import com.Proyecto.BudgetManager.enums.TransactionType;
import com.Proyecto.BudgetManager.repository.TransactionRepository;
import com.Proyecto.BudgetManager.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SchedulerService {
    private static final Logger log = LoggerFactory.getLogger(SchedulerService.class);
    private final TransactionRepository transactions;
    private final UserRepository users;
    private final Clock clock;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${app.backup.dir:./backups}")
    private String backupDir;

    public SchedulerService(TransactionRepository transactions, UserRepository users, Clock clock) {
        this.transactions = transactions;
        this.users = users;
        this.clock = clock;
    }

    // Se ejecuta todos los días a medianoche
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void procesarGastosRecurrentes() {
        LocalDate hoy = LocalDate.now(clock);
        log.info("Revisando gastos recurrentes para el día: {}", hoy);
        // Buscar todas las transacciones que sean recurrentes
        List<TransactionEntity> recurrentes = transactions.findAll().stream()
                .filter(TransactionEntity::isRecurrent)
                .toList();
        for (TransactionEntity original : recurrentes) {
            if (original.getRecurrentDay() == null) continue;
            LocalDate fechaEjecucion = GeneradorDeRecurrentes.calcularFechaEjecucion(
                    original.getRecurrentDay(), hoy.getMonthValue(), hoy.getYear());
            // Si hoy es el día de ejecución, creamos el gasto
            if (fechaEjecucion.isEqual(hoy)) {
                // Verificar que no se haya generado ya (para evitar duplicados)
                boolean yaExiste = transactions.findAll().stream()
                        .anyMatch(t -> t.getUser().getId().equals(original.getUser().getId()) &&
                                    t.getAmount().equals(original.getAmount()) &&
                                    t.getTransactionDate().equals(hoy.toString()) &&
                                    t.getCategory() == original.getCategory());
                if (!yaExiste) {
                    TransactionEntity nueva = new TransactionEntity();
                    nueva.setUser(original.getUser());
                    nueva.setType(TransactionType.GASTO);
                    nueva.setAmount(original.getAmount());
                    nueva.setCategory(original.getCategory());
                    nueva.setPriority(original.getPriority());
                    nueva.setTransactionDate(hoy.toString());
                    nueva.setPlanned(false);
                    nueva.setRecurrent(false);
                    transactions.save(nueva);
                    log.info("Gasto recurrente generado para el usuario: {}", original.getUser().getEmail());
                }
            }
        }
    }

    // Se ejecuta cada 6 horas (00:00, 06:00, 12:00, 18:00)
    @Scheduled(cron = "0 0 */6 * * *")
    public void backupBaseDeDatos() {
        try {
            Path dir = Paths.get(backupDir);
            Files.createDirectories(dir);
            String dbPath = dbUrl.replace("jdbc:sqlite:", "");
            Path source = Paths.get(dbPath);
            if (Files.exists(source)) {
                String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
                Path target = dir.resolve("budgetmanager-" + stamp + ".db");
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                log.info("Respaldo de base de datos creado: {}", target.toString());
            }
        } catch (IOException e) {
            log.error("Error al crear el respaldo de la base de datos", e);
        }
    }
}
