package com.idstaa.service.impl;

import com.idstaa.dao.ResumeDao;
import com.idstaa.pojo.Resume;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

/**
 * @author chenjie
 * @date 2020/12/30 14:42
 */
@Service
public class ResumeServiceImpl  implements ResumeService {
    @Autowired
    private ResumeDao resumeDao;
    @Override
    public Integer findDefaultResumeByUserId(Long userId) {
        Resume resume = new Resume();
        resume.setUserId(userId);
        Example<Resume> example = Example.of(resume);
        return resumeDao.findOne(example).get().getIsOpenResume();
    }
}
