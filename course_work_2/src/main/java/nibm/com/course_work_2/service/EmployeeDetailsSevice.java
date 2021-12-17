package nibm.com.course_work_2.service;

import nibm.com.course_work_2.dto.EmployeeDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeDetailsSevice {

    List<EmployeeDetailsDto> getEmployeeDetails(String nic);

    boolean addEmployeeDetails(EmployeeDetailsDto employeeDetailsDto);

    boolean deleteEmployeeDetails(String nic);

    boolean updateEmployeeDetails(String nic,EmployeeDetailsDto employeeDetailsDto);


}
