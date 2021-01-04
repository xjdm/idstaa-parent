package com.idstaa.dao;

import com.idstaa.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenjie
 * @date 2020/12/30 14:48
 */
public interface ResumeDao extends JpaRepository<Resume,Long> {
}
