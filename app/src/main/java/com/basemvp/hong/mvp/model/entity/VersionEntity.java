package com.basemvp.hong.mvp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hong on 2021/4/16 11:15.
 */
@NoArgsConstructor
@Data
public class VersionEntity {

    private String version;
    private int versionCode;
    private String url;
    private String title;
    private String content;
    private Integer compulsory;
    private String download_resource;
}
