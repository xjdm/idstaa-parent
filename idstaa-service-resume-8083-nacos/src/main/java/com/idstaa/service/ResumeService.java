package com.idstaa.service;

import com.idstaa.pojo.Resume;

/**
 * @author chenjie
 * @date 2020/12/30 14:41
 */
public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
