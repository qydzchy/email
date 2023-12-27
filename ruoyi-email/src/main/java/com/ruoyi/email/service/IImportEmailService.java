package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.ImportEmail;
import com.ruoyi.email.domain.vo.ImportListVO;
import com.ruoyi.email.domain.vo.TaskListVO;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入邮件Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface IImportEmailService 
{
    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    public ImportEmail selectImportEmailById(Long id);

    /**
     * 查询导入邮件列表
     * 
     * @param importEmail 导入邮件
     * @return 导入邮件集合
     */
    public List<ImportEmail> selectImportEmailList(ImportEmail importEmail);

    /**
     * 新增导入邮件
     * 
     * @param file
     * @return 结果
     */
    public boolean insertImportEmail(MultipartFile file, Long taskId, Long folderId, Boolean filterEmailFlag);

    /**
     * 修改导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    public int updateImportEmail(ImportEmail importEmail);

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的导入邮件主键集合
     * @return 结果
     */
    public int deleteImportEmailByIds(Long[] ids);

    /**
     * 删除导入邮件信息
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    public int deleteImportEmailById(Long id);

    /**
     * 邮箱任务列表
     * @return
     */
    List<TaskListVO> getTaskList();

    /**
     * 导入邮件列表（分页）
     * @return
     */
    Pair<Integer, List<ImportListVO>> page(Integer pageNum, Integer pageSize);
}
