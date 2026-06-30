package edu.fu.services;

import edu.fu.dao.DepartmentDao;
import edu.fu.entities.Department;
import edu.fu.repositities.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements  DepartmentService {
    private final DepartmentRepository departmentRepository;


    @Override
    @Transactional
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
