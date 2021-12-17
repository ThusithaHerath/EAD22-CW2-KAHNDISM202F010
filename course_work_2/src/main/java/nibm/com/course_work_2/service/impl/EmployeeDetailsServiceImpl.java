package nibm.com.course_work_2.service.impl;

import nibm.com.course_work_2.dto.EmployeeDetailsDto;
import nibm.com.course_work_2.repository.EmployeeDetailsRepo;
import nibm.com.course_work_2.service.EmployeeDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsSevice {

    @Autowired
    private EmployeeDetailsRepo employeeDetailsRepo;

    @Override
    public List<EmployeeDetailsDto> getEmployeeDetails(String nic) {

        return employeeDetailsRepo.getEmployeeDetails(nic);
    }

    @Override
    public boolean addEmployeeDetails(EmployeeDetailsDto employeeDetailsDto){

        return employeeDetailsRepo.addEmployeeDetails(employeeDetailsDto);
    }

    @Override
    public boolean deleteEmployeeDetails(String nic){

        return employeeDetailsRepo.deleteEmployeeDetails(nic);
    }

    @Override
    public boolean updateEmployeeDetails(String nic,EmployeeDetailsDto employeeDetailsDto){

        return employeeDetailsRepo.updateEmployeeDetails(nic,employeeDetailsDto);
    }
}
