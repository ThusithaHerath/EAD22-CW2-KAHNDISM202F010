package nibm.com.course_work_2.repository.impl;

import jdk.nashorn.internal.runtime.ScriptFunction;
import nibm.com.course_work_2.dto.EmployeeDetailsDto;
import nibm.com.course_work_2.repository.EmployeeDetailsRepo;
import nibm.com.course_work_2.util.EmployeeDetailsRowMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDetailsRepoImpl implements EmployeeDetailsRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public List<EmployeeDetailsDto> getEmployeeDetails(String nic) {

        String sql = "SELECT * FROM EMP_DETAILS";
        SqlParameterSource namedParametersPendingConnection = new MapSqlParameterSource().addValue("nic", nic);

        List<EmployeeDetailsDto> employeeDetailsDtos = namedParameterJdbcTemplate.query(sql, namedParametersPendingConnection, new EmployeeDetailsRowMap());
        return employeeDetailsDtos;

    }

    @Override
    @Transactional
    public boolean addEmployeeDetails(EmployeeDetailsDto employeeDetailsDto) {

        String sql = "INSERT INTO EMP_DETAILS(NIC,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,POSTALCODE,CONTACTNUMBER) VALUES (:nic,:firstName,:lastName,:address1,:address2,:postalCode,:contacNumber)";

        Map<String, Object> params = new HashMap<>();

        params.put("nic", employeeDetailsDto.getNic());
        params.put("firstName", employeeDetailsDto.getFirstName());
        params.put("lastName", employeeDetailsDto.getLastName());
        params.put("address1", employeeDetailsDto.getAddress1());
        params.put("address2", employeeDetailsDto.getAddress2());
        params.put("postalCode", employeeDetailsDto.getNic());
        params.put("contacNumber", employeeDetailsDto.getNic());
        return namedParameterJdbcTemplate.update(sql, params) > 0;
    }

    @Override
    @Transactional
    public boolean deleteEmployeeDetails(String nic) {
        String sql = "DELETE FROM EMP_DETAILS WHERE NIC=:nic";
        Map<String, Object> params = new HashMap<>();
        params.put("nic", nic);
        return namedParameterJdbcTemplate.update(sql, params) > 0;
    }

    @Override
    @Transactional
    public boolean updateEmployeeDetails(String nic,EmployeeDetailsDto employeeDetailsDto) {
        String sql = "UPDATE EMP_DETAILS SET FIRST_NAME=:firstName,LAST_NAME=:lastName,ADDRESS1=:address1,ADDRESS2=:address2,POSTALCODE=:postalCode,CONTACTNUMBER=:contacNumber WHERE NIC=:nic";

        Map<String, Object> params = new HashMap<>();
        params.put("nic", employeeDetailsDto.getNic());
        params.put("firstName", employeeDetailsDto.getFirstName());
        params.put("lastName", employeeDetailsDto.getLastName());
        params.put("address1", employeeDetailsDto.getAddress1());
        params.put("address2", employeeDetailsDto.getAddress2());
        params.put("postalCode", employeeDetailsDto.getNic());
        params.put("contacNumber", employeeDetailsDto.getNic());
        return namedParameterJdbcTemplate.update(sql, params) > 0;
    }
}
