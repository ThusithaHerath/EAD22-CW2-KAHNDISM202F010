import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { EmployeeResponse } from '../models/employee.response';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(
    private http: HttpClient,
  ) { }

  getEmployeeDetail(): Observable<EmployeeResponse> {
    const url = '../assets/employee.json'
    return this.http.get<EmployeeResponse>(url);
  }
}
