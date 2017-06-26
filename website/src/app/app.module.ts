import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HomeComponent }    from './pages/home/home.component';
import { RSUComponent }    from './pages/rsu/add-rsu.component';
import { NavComponent }    from './nav/nav.component';
import { DisableTimsComponent }  from './pages/disable-tims/disable-tims.component'; 

import { AppRoutingModule }     from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent, HomeComponent, NavComponent, RSUComponent, DisableTimsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
