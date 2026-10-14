import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.html',
  imports: [CommonModule, ReactiveFormsModule],
  styleUrl: './employee.css'
})

export class EmployeeComponent implements OnInit{

  ngOnInit() {

  }
}

