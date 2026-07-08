package com.harveyfonseca.proyecto3.controller;

import com.harveyfonseca.proyecto3.model.ReservationEventHF;
import com.harveyfonseca.proyecto3.util.ReservationFiltersHF;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationControllerHF {

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReservationEventHF> getReservationStreamHF() {

        // flujo de 5 reservas en memoria (3 válidas, 2 invalidas) usando Flux.just()
        ReservationEventHF r1 = new ReservationEventHF("V01", "Harvey Fonseca", 280.0, List.of("hjfonseca@espe.edu.ec"));
        ReservationEventHF r2 = new ReservationEventHF("I01", "Pasajero no valido 1", -5.0, List.of("error@aerolineahf.com")); // precio no valido
        ReservationEventHF r3 = new ReservationEventHF("V02", "Amelia Escobar", 350.50, List.of("bafonseca@uce.edu.ec"));
        ReservationEventHF r4 = new ReservationEventHF("I02", "Pasajero no valido 2", 120.0, List.of()); // lista vacia
        ReservationEventHF r5 = new ReservationEventHF("V03", "Lidia Sanchez", 195.0, List.of("lesanchez@espe.edu.ec"));

        // Reserva genérica por defecto requerida si el flujo queda vacío
        ReservationEventHF defaultReservation = new ReservationEventHF("DEF", "Pasajero Anonimo HF", 0.01, List.of("soporte@aerolineahf.com"));

        return Flux.just(r1, r2, r3, r4, r5)
                // Se aplica .filter() usando el Predicate
                .filter(ReservationFiltersHF.hasValidPriceAndEmailHF)

                // Se aplica .doOnNext() usando el Consumer
                .doOnNext(ReservationFiltersHF.printProcessedEventHF)

                // Se aplica .defaultIfEmpty() proveyendo una reserva generica
                .defaultIfEmpty(defaultReservation);
    }
}