import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HomeComponent }    from './pages/home/home.component';
import { RSUComponent }    from './pages/rsu/add-rsu.component';
import { NavComponent }    from './nav/nav.component';
import { DisableTimsComponent }  from './pages/disable-tims/disable-tims.component'; 
import { NguiDatetimePickerModule } from '@ngui/datetime-picker';
import { EsriLoaderService } from 'angular2-esri-loader';
import { EsriMapComponent } from './pages/esri-map/esri-map.component';

import { AppRoutingModule }     from './app-routing.module';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent, HomeComponent, NavComponent, RSUComponent, DisableTimsComponent, EsriMapComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    NguiDatetimePickerModule
  ],
  providers: [EsriLoaderService, 
    {
      provide: LocationStrategy, 
      useClass: HashLocationStrategy
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
