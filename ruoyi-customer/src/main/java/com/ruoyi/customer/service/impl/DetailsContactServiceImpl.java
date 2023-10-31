package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.DetailsContactMapper;
import com.ruoyi.customer.domain.DetailsContact;
import com.ruoyi.customer.service.IDetailsContactService;

/**
 * 客户联系人Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
@Service
public class DetailsContactServiceImpl implements IDetailsContactService 
{
    @Autowired
    private DetailsContactMapper detailsContactMapper;

    /**
     * 查询客户联系人
     * 
     * @param id 客户联系人主键
     * @return 客户联系人
     */
    @Override
    public DetailsContact selectDetailsContactById(Long id)
    {
        return detailsContactMapper.selectDetailsContactById(id);
    }

    /**
     * 查询客户联系人列表
     * 
     * @param detailsContact 客户联系人
     * @return 客户联系人
     */
    @Override
    public List<DetailsContact> selectDetailsContactList(DetailsContact detailsContact)
    {
        return detailsContactMapper.selectDetailsContactList(detailsContact);
    }

    /**
     * 新增客户联系人
     * 
     * @param detailsContact 客户联系人
     * @return 结果
     */
    @Override
    public int insertDetailsContact(DetailsContact detailsContact)
    {
        detailsContact.setCreateTime(DateUtils.getNowDate());
        return detailsContactMapper.insertDetailsContact(detailsContact);
    }

    /**
     * 修改客户联系人
     * 
     * @param detailsContact 客户联系人
     * @return 结果
     */
    @Override
    public int updateDetailsContact(DetailsContact detailsContact)
    {
        detailsContact.setUpdateTime(DateUtils.getNowDate());
        return detailsContactMapper.updateDetailsContact(detailsContact);
    }

    /**
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的客户联系人主键
     * @return 结果
     */
    @Override
    public int deleteDetailsContactByIds(Long[] ids)
    {
        return detailsContactMapper.deleteDetailsContactByIds(ids);
    }

    /**
     * 删除客户联系人信息
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    @Override
    public int deleteDetailsContactById(Long id)
    {
        return detailsContactMapper.deleteDetailsContactById(id);
    }
}
