package com.ruoyi.common.enums.email;

public enum TaskExecutionStatusEnum {

    NOT_STARTED(1), IN_PROGRESS(2), SUCCESS(3), FAILURE(4);

    private int status;

    TaskExecutionStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
