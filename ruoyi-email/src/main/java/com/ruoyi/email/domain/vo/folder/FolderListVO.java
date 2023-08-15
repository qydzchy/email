package com.ruoyi.email.domain.vo.folder;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FolderListVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父文件夹的ID,如果是顶级文件夹,此项为NULL
     */
    private Long parentFolderId;

    /**
     * 文件夹名称
     */
    private String name;

    /**
     * 类型: 1.系统文件夹 2.自定义文件夹
     */
    private Integer type;

    /**
     * 顺序
     */
    private Long orderNum;

    /**
     * 子节点
     */
    private List<FolderListVO> children = new ArrayList<>();

}
