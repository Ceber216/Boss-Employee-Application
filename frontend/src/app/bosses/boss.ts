import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

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

  constructor(private fb: FormBuilder, private http: HttpClient,private route: ActivatedRoute, private ref: ChangeDetectorRef, private router: Router) {}

  ngOnInit() {
    this.bossForm = this.fb.group({
      name:  ['', [Validators.required, Validators.minLength(1)]],
      salary: ['',[Validators.required, Validators.pattern("^[0-9]*")]]
    })
    this.loadBosses();
  }

  onSubmitPressed(){
    if(this.bossForm.valid){
      if(this.isEditing() == true){
        this.http.put(`/api/bosses/${this.editingBossId}`,this.bossForm.value).subscribe({complete: () =>
            this.loadBosses()
        });
        this.editingBossId = '';
        this.isEditPressed = false;
      }
      else {
        this.http.post("/api/bosses", this.bossForm.value).subscribe({complete: () =>
            this.loadBosses()
        });
      }
      this.bossForm.reset();
    }
    else{
      console.log("Form is incorrect")
    }
  }

  loadBosses(){
    this.http.get("/api/bosses").subscribe((data) =>
    {
      this.bosses = data;
      this.ref.detectChanges();
    });
  }

  onDeletePressed(boss: any){
    this.http.delete(`/api/bosses/${boss.id}`).subscribe({complete: () =>
        this.loadBosses()
    });
  }

  onEditPressed(boss: any){
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
    this.bossForm.reset();
  }

  onDetailsPressed(boss: any):void{
    this.router.navigate([boss.id], {relativeTo: this.route} );
  }
}
