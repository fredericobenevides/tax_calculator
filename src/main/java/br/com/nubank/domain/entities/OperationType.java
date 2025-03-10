package br.com.nubank.domain.entities;

import com.google.gson.annotations.SerializedName;

public enum OperationType {

    @SerializedName("buy")
    BUY,

    @SerializedName("sell")
    SELL
}
