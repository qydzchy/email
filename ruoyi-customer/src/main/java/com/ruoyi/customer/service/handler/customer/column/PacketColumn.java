package com.ruoyi.customer.service.handler.customer.column;

import com.ruoyi.common.enums.customer.CustomerColumnEnum;
import com.ruoyi.customer.domain.bo.SegmentConditionRuleBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.SimplePacketVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 分组
 */
@Slf4j
@Component
public class PacketColumn extends ColumnAbstract implements IColumnService {

    @Override
    public CustomerColumnEnum getCustomerColumnEnum() {
        return CustomerColumnEnum.PACKET;
    }

    @Override
    public boolean handler(CustomerDetailVO customerDetail, SegmentConditionRuleBO segmentConditionRule) {
        SimplePacketVO packet = customerDetail.getPacket();
        Long packetId = packet != null ? packet.getId() : null;

        try {
            return super.inNullLongHandler(packetId, segmentConditionRule);
        } catch (Exception e) {
            log.error("客户分组处理异常", e);
            return false;
        }
    }
}
