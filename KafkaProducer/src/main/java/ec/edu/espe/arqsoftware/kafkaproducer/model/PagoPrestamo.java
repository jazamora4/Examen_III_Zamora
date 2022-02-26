package ec.edu.espe.arqsoftware.kafkaproducer.model;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@ToString
public class PagoPrestamo {
    private Long codigoPrestamo;
    private BigDecimal valorPago;
    private LocalDate fechaPago;
    private LocalTime horaPago;
    private Integer numeroCuota;
}
