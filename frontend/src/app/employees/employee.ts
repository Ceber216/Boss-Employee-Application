import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.html',
  imports: [CommonModule, ReactiveFormsModule],
  styleUrl: './employee.css'
})

export class EmployeeComponent implements OnInit{
  employees: any = [];
  isEditingPressed: boolean = false;

  bossId : String = "";
  //TODO  - get bossId of the boss we have in URL
  employeeId : String = "";

  employeeForm !: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private route: ActivatedRoute, private router: Router, private ref: ChangeDetectorRef) { }

  ngOnInit() {
    this.employeeForm = this.fb.group({
      name: ['',[Validators.required, Validators.minLength(1)]],
      badgeNumber: ['',[Validators.required, Validators.pattern("^[0-9]*")]]
    })
    this.loadEmployees();
  }

  onDeletePressed(employee: any){
    //TODO - request to server to delete employee of this boss
  }

  onEditPressed(employee: any){
    this.isEditingPressed = true;
    //TODO - request to server to edit (put) employee of this boss
  }

  onSubmitPressed(){
    //TODO - request to server to create (post) employee of this boss
  }

  loadEmployees(){
    //TODO - request to server to get (get) employees of this boss
  }

  isEditing(){
    return this.isEditingPressed;
  }

  cancelEdit(){
    this.isEditingPressed = false;
  }
}

