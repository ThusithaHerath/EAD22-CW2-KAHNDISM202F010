import { Injectable } from '@angular/core';
import { FormGroup, AbstractControl } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {
  constructor() { }

  numericValidator() {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (control.value == null || control.value == "") {
        return null;
      }

      if (control.value.match(/^[0-9,.]+$/)) {
        return null;
      } else {
        return { 'invalidNumeric': true };
      }
    }
  }
  
  textOnlyValidator() {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (control.value == null || control.value == "") {
        return null;
      }

      if (control.value.match(/^[a-zA-Z ]*$/)) {
        return null;
      } else {
        return { 'invalidText': true };
      }
    }
  }
    nicValidator() {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (control.value == null || control.value == "") {
        return null;
      }

      if (control.value.match(/^([0-9]{9}[x|X|v|V]|[0-9]{12})$/)) {
        return null;
      } else {
        return { 'invalidNic': true };
      }
    }
  }


}
