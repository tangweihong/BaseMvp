package com.basemvp.hong.mvp.model.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hong on 2021/9/7 14:14.
 */
@NoArgsConstructor
@Data
public class StrategyListEntity {


    private Integer has_api;
    private ListBean list;

    @NoArgsConstructor
    @Data
    public static class ListBean {
        private Integer current_page;
        private List<DataBean> data;
        private String first_page_url;
        private Integer from;
        private Integer last_page;
        private String last_page_url;
        private Object next_page_url;
        private String path;
        private Integer per_page;
        private Object prev_page_url;
        private Integer to;
        private Integer total;

        @NoArgsConstructor
        @Data
        public static class DataBean {
            private String id;
            private String strategy;
            private String strategy_name;
            private String introduction;
            private String market_id;
            private String initial_used;
            private String initial_profit_ratio;
            private String created_at;
            private String market;
            private String used;
        }
    }
}
