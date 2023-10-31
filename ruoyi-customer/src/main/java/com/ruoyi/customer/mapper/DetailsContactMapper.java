package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.DetailsContact;
import org.apache.ibatis.annotations.Param;

/**
 * 客户联系人Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
public interface DetailsContactMapper 
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
     * 删除客户联系人
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    public int deleteDetailsContactById(Long id);

    /**
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDetailsContactByIds(Long[] ids);

    /**
     * 批量新增客户联系人
     * @param detailsContactList
     */
    void batchInsertDetailsContact(@Param("detailsContactList") List<DetailsContact> detailsContactList);

    /**
     * 删除指定客户的联系人
     * @param customerDetailsId
     */
    void deleteDetailsContactByCustomerDetailsId(@Param("customerDetailsId") Long customerDetailsId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);
}
