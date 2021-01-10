package com.idstaa.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author chenjie
 * @date 2021/1/10 12:28
 */
public class SentinelHandlersClass {
    public static Integer handlerException(Long userId, BlockException blockException){
        return  -100;
    }
    public static Integer handleError(Long userId) {
        return -500;
    }
}
