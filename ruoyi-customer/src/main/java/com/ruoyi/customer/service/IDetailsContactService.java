package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.DetailsContact;

/**
 * 客户联系人Service接口
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
public interface IDetailsContactService 
{
    /**
     * 查询客户联系人
     * 
     * @param id 客户联系人主键
     * @return 客户联系人
     */
    public DetailsContact selectDetailsContactById(Long id);

    /**
     * 查询客户联系人列表
     * 
     * @param detailsContact 客户联系人
     * @return 客户联系人集合
     */
    public List<DetailsContact> selectDetailsContactList(DetailsContact detailsContact);

    /**
     * 新增客户联系人
     * 
     * @param detailsContact 客户联系人
     * @return 结果
     */
    public int insertDetailsContact(DetailsContact detailsContact);

    /**
     * 修改客户联系人
     * 
     * @param detailsContact 客户联系人
     * @return 结果
     */
    public int updateDetailsContact(DetailsContact detailsContact);

    /**
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的客户联系人主键集合
     * @return 结果
     */
    public int deleteDetailsContactByIds(Long[] ids);

    /**
     * 删除客户联系人信息
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    public int deleteDetailsContactById(Long id);
}
