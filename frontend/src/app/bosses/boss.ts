import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

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

  constructor(private fb: FormBuilder, private http: HttpClient,private route: ActivatedRoute, private ref: ChangeDetectorRef) {}

  ngOnInit() {
    this.bossForm = this.fb.group({
      name:  ['', [Validators.required, Validators.minLength(1)]],
      salary: ['',[Validators.required, Validators.pattern("^[0-9]*")]]
    })
    this.loadBosses();
    this.route.paramMap.subscribe(params =>{
      const id = params.get('id');
      if(id){
        this.isEditPressed = true;
        this.editingBossId = id;

        this.http.get<any>(`/api/bosses/${id}`).subscribe(boss => {
          this.bossForm.patchValue({
            name: boss.name,
            salary: boss.salary
          });
        });
      }
    });
  }

  onSubmit(){
    if(this.bossForm.valid){
      if(this.isEditing() == true){
        const data = this.bossForm.value;
        this.http.put(`/api/bosses/${this.editingBossId}`,data).subscribe({complete: () => this.loadBosses()});
        this.editingBossId = '';
        this.isEditPressed = false;
        this.bossForm.reset();
      }
      else {
        const data = this.bossForm.value;
        this.http.post("/api/bosses", data).subscribe({complete: () => this.loadBosses()});
        this.bossForm.reset();
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
      this.ref.detectChanges();
    });
  }

  onDelete(boss: any){
    this.http.delete(`/api/bosses/${boss.id}`).subscribe({complete: () => this.loadBosses()})
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
  }

  onDetails(boss: any):void{

  }
}
