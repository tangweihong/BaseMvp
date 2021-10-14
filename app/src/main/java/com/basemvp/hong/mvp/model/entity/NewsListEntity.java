package com.basemvp.hong.mvp.model.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hong on 2021/10/13 15:57.
 */
@NoArgsConstructor
@Data
public class NewsListEntity {

    private List<DataBean> data;
    private Integer count;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        private Integer id;
        private String title;
        private String author_name;
        private String author_img;
        private String created_at;
        private String news_img;
        private String post_lead;
        private String tag;
        private String content;
        private String detail_url;
        private String language_name;
        private Integer language_id;
        private String type1_name;
        private Integer type1_id;
        private String type2_name;
        private Integer type2_id;
        private Integer time;
    }
}
