package ec.edu.espe.arqsoftware.kafkaproducer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class KafkaProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void asyncSend(T obj, String topic){
        ListenableFuture<SendResult<String, T>> send = kafkaTemplate.send(topic, obj);
        send.addCallback(new KafkaSendCallback<String, T>(){
            @Override
            public void onSuccess(SendResult<String, T> result) {
                log.info("Mensaje enviado: {}",result.getRecordMetadata());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Error al enviar el mensaje {}",ex);
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                log.error("Error al enviar el mensaje {}",ex);
            }
        });
    }
}
