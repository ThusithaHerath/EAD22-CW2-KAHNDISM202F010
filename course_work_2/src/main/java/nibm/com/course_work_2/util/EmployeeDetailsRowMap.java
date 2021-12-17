package nibm.com.course_work_2.util;

import nibm.com.course_work_2.dto.EmployeeDetailsDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDetailsRowMap implements RowMapper<EmployeeDetailsDto> {


    @Override
    public EmployeeDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();


        employeeDetailsDto.setNic(rs.getString("NIC"));
        employeeDetailsDto.setFirstName(rs.getString("FIRST_NAME"));
        employeeDetailsDto.setLastName(rs.getString("LAST_NAME"));
        employeeDetailsDto.setAddress1(rs.getString("ADDRESS1"));
        employeeDetailsDto.setAddress2(rs.getString("ADDRESS2"));
        employeeDetailsDto.setPostalCode(rs.getString("POSTALCODE"));
        employeeDetailsDto.setContacNumber(rs.getString("CONTACTNUMBER"));

        return employeeDetailsDto;
    }
}
