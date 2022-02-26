package ec.edu.espe.arqsoftware.kafkaproducer.service;

import com.github.javafaker.Faker;
import ec.edu.espe.arqsoftware.kafkaproducer.kafka.KafkaProducer;
import ec.edu.espe.arqsoftware.kafkaproducer.model.PagoPrestamo;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Service
public class PagoPrestamoService {
    private final KafkaProducer<PagoPrestamo> kafkaProducer;
    private final Faker faker = new Faker();

    public PagoPrestamoService(KafkaProducer<PagoPrestamo> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void publishPagoPrestamo(Integer numRegistros){
        for(Integer i=0;i<numRegistros;i++){
            PagoPrestamo pago = PagoPrestamo.builder()
                    .codigoPrestamo(Long.parseLong(faker.numerify("#######")))
                    .fechaPago(LocalDate.now())
                    .horaPago(LocalTime.now())
                    .valorPago(new BigDecimal(faker.numerify("########.##")))
                    .numeroCuota(Integer.parseInt("####"))
                    .build();
            log.info("Se ha generado el siguiente pago : {}", pago);
            
        }
    }
}
