package com.mvc.boot.service;

import com.mvc.boot.annotation.ProductMasterDataSource;
import com.mvc.boot.annotation.ProductSlaveDataSource;
import com.mvc.boot.dao.product.master.StudentMasterMapper;
import com.mvc.boot.dao.product.slave.StudentSlaveMapper;
import com.mvc.boot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService implements IStudentService {
    @Autowired
    private StudentMasterMapper studentMasterMapper;
    @Autowired
    private StudentSlaveMapper studentSlaveMapper;
    @ProductMasterDataSource
    public int deleteByPrimaryKey(int uid) {
        return studentMasterMapper.deleteByPrimaryKey(uid);
    }
    @CachePut(value = "localCacheService", key="#record.uid")
    public int insert(Student record) {
        printInfo("insert");
        return studentMasterMapper.insert(record);
    }
    @ProductMasterDataSource
    public int insertSelective(Student record) {
        return studentMasterMapper.insertSelective(record);
    }
    @ProductSlaveDataSource
    @Cacheable(value = "localCacheService", key="#uid")
    public Student selectByPrimaryKey(int uid) {
        printInfo("selectByPrimaryKey");
        return studentSlaveMapper.selectByPrimaryKey(uid);
    }
    @ProductSlaveDataSource
    public List<Student> selectByCondition(Student record) {
        return studentSlaveMapper.selectByCondition(record);
    }
    @ProductMasterDataSource
    @CacheEvict(value = "localCacheService", key="#record.uid")
    public int updateByPrimaryKeySelective(Student record) {
        printInfo("updateByPrimaryKeySelective");
        return studentMasterMapper.updateByPrimaryKeySelective(record);
    }
    @ProductMasterDataSource
    public int updateByPrimaryKey(Student record) {
        return studentMasterMapper.updateByPrimaryKey(record);
    }
    private void printInfo(Object str){
        System.out.println("非缓存查询----------"+str);
    }
}
