package com.fabrickSB.enums;

import java.util.Arrays;

public enum TaxReliefId {

    INTERVENTI_SUPERBONUS("119R"),
    BONUS_FACCIATE("L027"),
    INTEREVENTI_ANTISISMICI("DL50"),
    AQUISTO_MOBILIO("L090"),
    RISPARMIO_ENERGETICO("L296"),
    RISTRUTTURAZIONE("L449"),
    BARRIERE_ARCHITETTONICHE("L234");

    private final String value;

    //controlla se i valori passati corrispondono ai valori dell'enum
    public static Boolean containsValue(String s) {
        return Arrays.stream(TaxReliefId.values()).anyMatch(t -> t.value.equals(s));
    }

    TaxReliefId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
