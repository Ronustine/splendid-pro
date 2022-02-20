package com.ronustine.splendidpro.common.exception;

/**
 * 业务异常枚举
 * 格式：SYS(FUNC)100001: 业务校验失败原因
 */
public interface CustomException {

    /**
     * @return 码
     */
    Integer getCode();

    /**
     *
     * @return 用于调试的信息
     */
    String getDebugMsg();

    /**
     * @return 可读的信息
     */
    String getReadableMsg();

    // ------- 标志设计 --------
    /**
     * @return 系统标志（必须实现）
     */
    default String systemMark() {
        // TODO 似乎会影响后续的枚举自动检查功能 可能要改回 return "";
        throw new UnsupportedOperationException("CustomException need override method: systemMark()");
    }

    /**
     * @return 功能标志 默认空
     */
    default String funcMark() {
        return "";
    }
}
