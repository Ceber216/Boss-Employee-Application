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
  isEditPressed: boolean = false;
  editingBossId: string = '';

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
      if(this.isEditing() == true){
        const data = this.bossForm.value;
        this.http.put(`/api/bosses/${this.editingBossId}`,data).subscribe({next: () => this.loadBosses()})
        this.editingBossId = '';
        this.isEditPressed = false;
        this.ngOnInit();
      }
      else {
        const data = this.bossForm.value;
        this.http.post("/api/bosses", data).subscribe({next: () => this.loadBosses()});
      }
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

  onDelete(boss: any){
    this.http.delete(`/api/bosses/${boss.id}`).subscribe({next: () => this.loadBosses()})
  }

  onEdit(boss: any){
    this.isEditPressed = true;
    this.editingBossId = boss.id
    this.bossForm = this.fb.group({
      name:  [boss.name, [Validators.required, Validators.minLength(1)]],
      salary: [boss.salary,[Validators.required, Validators.pattern("^[0-9]*")]]
    })
  }

  isEditing(): boolean{
    return this.isEditPressed;
  }

  cancelEdit(): void{
    this.isEditPressed = false;
    this.ngOnInit();
  }
}
