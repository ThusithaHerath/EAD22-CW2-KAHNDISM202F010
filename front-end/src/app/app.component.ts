import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from './models/employee.vm';
import { EmployeeService } from './services/employee.service';
import { ValidationService } from './services/validation.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MessageService]
})
export class AppComponent {
  title = 'Employee Management';
  employees: Employee[]
  employeeForm: FormGroup
  isUpdate: boolean
  nicNo: string;

  constructor(
    private empService: EmployeeService,
    private formBuilder: FormBuilder,
    private validationService: ValidationService,
    private messageService: MessageService
    ) {}

  get firstNameController(): AbstractControl {return this.employeeForm.get('firstName');}
  get lastNameController(): AbstractControl {return this.employeeForm.get('lastName');}
  get address1Controller(): AbstractControl {return this.employeeForm.get('address1');}
  get address2Controller(): AbstractControl {return this.employeeForm.get('address2');}
  get nicController(): AbstractControl {return this.employeeForm.get('nic');}
  get contactNumberController(): AbstractControl {return this.employeeForm.get('contactNumber');}
  get postalCodeController(): AbstractControl {return this.employeeForm.get('postalCode');}

  ngOnInit() {
    this.getEmployees();
    this.initForm();
    this.isUpdate = false
  }
  initForm() {
    this.employeeForm = this.formBuilder.group({
      firstName:['',[Validators.required,this.validationService.textOnlyValidator()]],
      lastName:['',[Validators.required,this.validationService.textOnlyValidator()]],
      address1:['',Validators.required],
      address2:['',Validators.required],
      nic:['',[Validators.required,Validators.minLength(10), Validators.maxLength(10),this.validationService.nicValidator()]],
      contactNumber:['',[Validators.required, this.validationService.numericValidator(),Validators.minLength(10), Validators.maxLength(10)]],
      postalCode:['',this.validationService.numericValidator()],
    })
  }
  getEmployees() {
    this.empService.getEmployeeDetail().subscribe(
      data => {
        if (data && data.employeeList && data.employeeList.length > 0) {
          this.employees = data.employeeList
        }
      }
    )
  }

  addEmployee() {
    const employee: Employee = {
      firstName: this.firstNameController.value,
      lastName: this.lastNameController.value,
      address1: this.address1Controller.value,
      address2: this.address2Controller.value ? this.address2Controller.value : null,
      nic: this.nicController.value,
      contactNumber: this.contactNumberController.value,
      postalCode: this.postalCodeController.value ? this.postalCodeController.value : null
    }

    if (this.employees.find(data => data.nic == employee.nic)){
      this.messageService.add({key: 'tl',severity:'error', summary:'Error', detail:'Nic No is Alredy Added'});
    } else {
      this.employees.push(employee)
      this.messageService.add({key: 'tl',severity:'success', summary:'Succeed', detail:'New Employee Added Successfully'});
    }
  }
  
  updateEmployee() {
    const seletedEmployee: Employee = this.employees.find(data => data.nic ===  this.nicController.value)
    const index: number = this.employees.indexOf(seletedEmployee)
    console.log(index);
    
    const updatedEmployee : Employee = {
      firstName: this.firstNameController.value,
      lastName: this.lastNameController.value,
      address1: this.address1Controller.value,
      address2: this.address2Controller.value ? this.address2Controller.value : null,
      nic: this.nicController.value,
      contactNumber: this.contactNumberController.value,
      postalCode: this.postalCodeController.value ? this.postalCodeController.value : null
    }
    this.employees[index] = updatedEmployee
    this.isUpdate = false
    this.messageService.add({key: 'tl',severity:'success', summary:'Succeed', detail:'Employee Updated Successfully'});
    this.reset()

  }

  editEmployee(employee: Employee) { 
    this.isUpdate = true
    this.firstNameController.setValue(employee.firstName);
    this.lastNameController.setValue(employee.lastName);
    this.nicController.setValue(employee.nic);
    this.address1Controller.setValue(employee.address1);
    this.address2Controller.setValue(employee.address2);
    this.address2Controller.setValue(employee.address2);
    this.contactNumberController.setValue(employee.contactNumber);
    this.postalCodeController.setValue(employee.postalCode);

    this.nicController.disable();
  }

  deleteEmployee(employee: Employee) {
    if (employee && employee.nic) {
      this.nicNo = employee.nic
      this.messageService.add({key: 'c', sticky: true, severity:'warn', summary:'Are you sure?', detail:'Confirm to proceed'});
    }
  }
  reset() {
    this.employeeForm.reset();
    this.isUpdate = false
  }

  onConfirm() {
    const seletedEmployee: Employee = this.employees.find(data => data.nic ===  this.nicNo)
    this.employees = this.employees.filter(data=> data.nic !== seletedEmployee.nic)
    this.messageService.clear('c');
    this.messageService.add({key: 'tl',severity:'success', summary:'Succeed', detail:'Employee Deleted Successfully'});
  }

  onReject() {
      this.messageService.clear('c');
  }

  clear() {
      this.messageService.clear();
  }
}

