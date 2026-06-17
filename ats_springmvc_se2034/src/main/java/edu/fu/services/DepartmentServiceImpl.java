package edu.fu.services;

import edu.fu.dao.DepartmentDao;
import edu.fu.entities.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements  DepartmentService {
    private final DepartmentDao departmentDao;


    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
