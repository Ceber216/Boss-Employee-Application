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
  //TODO  - get bossId of the boss we have in URL!!!
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
    this.http.delete(`/api/bosses/${this.bossId}/employees/${employee.id}`).subscribe({
      complete: () => {
        this.loadEmployees()
      }
    });
  }

  onEditPressed(employee: any){
    this.isEditingPressed = true;
    this.employeeForm = this.fb.group({
      name: [`${employee.name}`,[Validators.required,Validators.minLength(1)]],
      badgeNumber: [`${employee.badgeNumber}`,[Validators.required, Validators.pattern("^[0-9]*")]]
    })
  }

  onSubmitPressed(){
    if(this.employeeForm.valid){
      if(this.isEditingPressed){
        this.http.put(`/api/bosses/${this.bossId}/employees/${this.employeeId}`,this.employeeForm).subscribe({
          complete: () => {
            this.loadEmployees();
          }
        });
        this.isEditingPressed = false;
        this.employeeId = "";
      }
      else{
        this.http.post(`/api/bosses/${this.bossId}/employees`,this.employeeForm).subscribe({
          complete: () =>
            this.employeeForm
        });
      }
      this.employeeForm.reset();
    }
  }

  loadEmployees(){
    this.employees = this.http.get(`/api/bosses/${this.bossId}/employees`).subscribe(
    (data) => {
        this.employees = data;
        this.ref.detectChanges();
      }
    )
  }

  isEditing(){
    return this.isEditingPressed;
  }

  cancelEdit(){
    this.isEditingPressed = false;
    this.employeeForm.reset();
  }
}

