package nibm.com.course_work_2.repository;

import nibm.com.course_work_2.dto.EmployeeDetailsDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDetailsRepo {

    List<EmployeeDetailsDto> getEmployeeDetails(String nic);

    boolean addEmployeeDetails(EmployeeDetailsDto employeeDetailsDto);

    boolean deleteEmployeeDetails(String nic);

    boolean updateEmployeeDetails(String nic,EmployeeDetailsDto employeeDetailsDto);
}
