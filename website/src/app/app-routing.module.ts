import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent }        from './pages/home/home.component'; 
import { RSUComponent }        from './pages/rsu/add-rsu.component'; 
import { DisableTimsComponent }	from './pages/disable-tims/disable-tims.component'; 

const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
	{ path: 'home',  component: HomeComponent },
	{ path: 'rsu',  component: RSUComponent },
	{ path: 'disableTims',  component: DisableTimsComponent },
	{ path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}