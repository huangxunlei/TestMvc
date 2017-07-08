package com.seereals.beidou.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/7/9 0009.
 */
public class FileModel {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
