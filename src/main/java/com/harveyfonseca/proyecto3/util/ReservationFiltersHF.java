package com.harveyfonseca.proyecto3.util;

import com.harveyfonseca.proyecto3.model.ReservationEventHF;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ReservationFiltersHF {

    private ReservationFiltersHF() {
        throw new UnsupportedOperationException("Esta es una clase utilitaria inmutable");
    }
    // predicate que valida el precio y la lista de emails
    public static final Predicate<ReservationEventHF> hasValidPriceAndEmailHF = event ->
            event != null &&
                    event.getPrice() > 0 &&
                    event.getEmails() != null &&
                    !event.getEmails().isEmpty();
    //Consumer que imprimira por consola el evento
    public static final Consumer<ReservationEventHF> printProcessedEventHF = event ->
            System.out.println("[HF-STREAM-LOG] Procesando con éxito: " + event);
}