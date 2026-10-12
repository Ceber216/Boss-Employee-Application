import {Component,OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-boss',
  templateUrl: './boss.html',
  imports: [CommonModule, ReactiveFormsModule],
  styleUrl: './boss.css'
})

export class BossComponent implements OnInit{
  bossForm!: FormGroup;
  bosses: any = [];

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit() {
    this.bossForm = this.fb.group({
      name:  ['', [Validators.required, Validators.minLength(1)]],
      salary: ['',[Validators.required, Validators.pattern("^[0-9]*")]]
    })
    this.loadBosses();
  }

  onSubmit(){
    if(this.bossForm.valid){
      const data = this.bossForm.value;
      this.http.post("/api/bosses",data ).subscribe({next: () => this.loadBosses()});
    }
    else{
      console.log("Form is incorrect")
    }
  }

  loadBosses(){
    const data = this.http.get<any[]>("/api/bosses").subscribe((data) =>
    {
      this.bosses = data;
    });
  }
}
