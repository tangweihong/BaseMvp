package com.basemvp.hong.mvp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hong on 2021/4/13 18:29.
 */
@NoArgsConstructor
@Data
public class MarketItemEntity {
    private String id;
    private String symbol;
    private String block;
    private String status;
    private String name;
    private Integer is_trade;
    private String buy_fee;
    private String sell_fee;
    private int price_precision;
    private String number_precision;
    private int cny_precision;
    private String min_buy;
    private String max_buy;
    private String min_buy_amount;
    private String min_sell;
    private String min_sell_amount;
    private String max_sell;
    private String price;
    private String price_cny;
    private String scope;
}
