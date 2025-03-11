package br.com.nubank.model;

import com.google.gson.annotations.SerializedName;

public enum OperationType {

    @SerializedName("buy")
    BUY,

    @SerializedName("sell")
    SELL
}
