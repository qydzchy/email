package com.ruoyi.email.domain.bo;

import lombok.Data;

import java.util.Date;

@Data
public class TransceiverRuleBO {

    Boolean fixedFlag = false;
    Boolean readFlag = false;
    Long labelId = null;
    Long folderId = -1L;
    String forwardTo = null;
    Boolean pendingFlag = false;
    Date pendingTime = null;
    String autoResponse = null;
    String delFlag = null;
}
