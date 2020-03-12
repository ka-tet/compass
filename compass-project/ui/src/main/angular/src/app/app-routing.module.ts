import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConsoleComponent } from './console/console.component'


const routes: Routes = [
	{
		path: '',
		component: ConsoleComponent,
		data: {parm: "Default"}
	},
	{
		path: 'exists',
		component: ConsoleComponent,
		data: {parm: "Exists"}
	},
	{
		path: 'get',
		component: ConsoleComponent,
		data: {parm: "Get"}
	},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
