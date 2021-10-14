package com.basemvp.hong.mvp.model.entity;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


    /**
     * Created by hong on 2021/8/26 16:52.
     */
    @NoArgsConstructor
    @Data
    public class HomeEntity {

        private List<BannerBean> banner;
        private List<MarketBean> market;
        private List<ArticleBean> article;
        private List<InformationBean> information;

        @NoArgsConstructor
        @Data
        public static class BannerBean {
            private String id;
            private String name;
            private String zh_image;
            private String en_image;
            private String img;
            private String url;
            private String status;
            private String created_at;

        }

        @NoArgsConstructor
        @Data
        public static class MarketBean {
            private String id;
            private Integer price_precision;
            private Integer number_precision;
            private String symbol;
            private String block;
            private String name;
            private String title;
            private String logo;
            private String status;
            private Integer is_exchange;
            private String exchange_amount;
            private String exchange_ratio;
            private Integer decimals;
            private String transfer_ratio;
            private String price;
            private String price_cny;
            private String scope;
        }

        @NoArgsConstructor
        @Data
        public static class ArticleBean {
            private Integer id;
            private String title;
            private String introduction;
            private String en_title;
            private String en_introduction;

            @Override
            public String toString() {
                return title;
            }
        }

        @NoArgsConstructor
        @Data
        public static class InformationBean {
            private String id;
            private String created_at;
            private String title;
            private String content;
        }
    }
