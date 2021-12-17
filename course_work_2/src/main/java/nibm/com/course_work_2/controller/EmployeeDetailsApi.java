package nibm.com.course_work_2.controller;

import nibm.com.course_work_2.dto.ApiResponse;
import nibm.com.course_work_2.dto.EmployeeDetailsDto;
import nibm.com.course_work_2.service.EmployeeDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@CrossOrigin
@RequestMapping("/employee-management-system")
public class EmployeeDetailsApi {

    @Autowired
    private EmployeeDetailsSevice employeeDetailsSevice ;

    @PostMapping(value = {"/add-employeeDetails"})
    public ResponseEntity<ApiResponse> addEmployeeDetails(@RequestBody EmployeeDetailsDto employeeDetailsDto){

        boolean response = employeeDetailsSevice.addEmployeeDetails(employeeDetailsDto);

        return new ApiResponse.ApiResponseBuilder<>()
                .withHttpStatus(OK)
                .withData(Collections.singletonMap("status", response))
                .build();


    }


    @GetMapping("/get-employeeDetails/{nic}")
    public ResponseEntity<ApiResponse> getEmployeeDetails(@PathVariable String nic) {
        List<EmployeeDetailsDto> employeeDetailsDtos = employeeDetailsSevice.getEmployeeDetails(nic);

        return new ApiResponse.ApiResponseBuilder<>()
                .withHttpStatus(OK)
                .withData(employeeDetailsDtos)
                .build();
    }

    @DeleteMapping("/delete-employeeDetails/{nic}")
    public ResponseEntity<ApiResponse> deleteEmployeeDetails(@PathVariable String nic) {
        boolean delete = employeeDetailsSevice.deleteEmployeeDetails(nic);

        return new ApiResponse.ApiResponseBuilder<>()
                .withHttpStatus(OK)
                .withData(Collections.singletonMap("status", delete))
                .build();
    }

    @PutMapping("/update-employeeDetails/{nic}")
    public ResponseEntity<ApiResponse> updateEmployeeDetails(@PathVariable String nic,@RequestBody EmployeeDetailsDto employeeDetailsDto) {
        boolean response = employeeDetailsSevice.updateEmployeeDetails(nic,employeeDetailsDto);

        return new ApiResponse.ApiResponseBuilder<>()
                .withHttpStatus(OK)
                .withData(Collections.singletonMap("status", response))
                .build();
    }
}
