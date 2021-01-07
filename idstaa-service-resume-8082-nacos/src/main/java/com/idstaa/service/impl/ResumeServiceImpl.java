package com.idstaa.service.impl;

import com.idstaa.dao.ResumeDao;
import com.idstaa.pojo.Resume;
import com.idstaa.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author chenjie
 * @date 2020/12/30 14:42
 */
@Service
public class ResumeServiceImpl  implements ResumeService {
    @Autowired
    private ResumeDao resumeDao;
    @Override
    public Resume findDefaultResumeByUserId(Long userId) {
        Resume resume = new Resume();
        resume.setUserId(userId);
        Example<Resume> example = Example.of(resume);
        return resumeDao.findOne(example).get();
    }
}
