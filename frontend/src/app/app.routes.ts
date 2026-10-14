import { Routes } from '@angular/router';
import {BossComponent} from './bosses/boss';
import {EmployeeComponent} from './employees/employee';


export const routes: Routes = [
  {
    path: 'bosses',
    component: BossComponent
  },
  {
    path: 'bosses/:id',
    component: EmployeeComponent
  }
];
