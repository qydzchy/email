package com.ruoyi.email.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class FolderTypeListVO {

    /**
     * 文件夹类型
     * 系统文件夹：-1.收件箱 -2.发件箱 -3.已删除邮件 -4.草稿箱 -5.垃圾邮件
     * 自定义文件夹
     */
    private String label;

    /**
     *
     */
    private List<FolderListVO> folderList;
}
