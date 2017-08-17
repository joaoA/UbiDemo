import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RestService } from './services/rest.service';
import { RouterModule } from '@angular/router';
import { routing } from './app.routes';
import {GettingStartedComponent} from './pages/first/first.component';

import {AddUserComponent} from './pages/addUser/addUser.component';
import {UsersComponent} from './pages/users/users.component';
import {MusicsComponent} from './pages/musics/music.component';
import {AddMusicsComponent} from './pages/addMusic/addMusic.component';

import { MainMenuComponent } from './common/main-menu/main-menu.component';
import { TopMenuComponent } from './common/top-menu/top-menu.component';
import { AppFooterComponent } from './common/app-footer/app-footer.component';
import { HttpModule} from '@angular/http';
import { Configuration } from './app.constants';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MultiselectDropdownModule } from 'angular-2-dropdown-multiselect';

let pages = [
  GettingStartedComponent,
  UsersComponent,
  AddUserComponent,
  MusicsComponent,
  AddMusicsComponent,
]

let services = [
  RestService,
]

@NgModule({
  declarations: [
    ...pages,
    TopMenuComponent,
    MainMenuComponent,  
    AppFooterComponent,
    AppComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    routing,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    MultiselectDropdownModule
  ],
  providers: [...services,Configuration],
  bootstrap: [AppComponent]
})
export class AppModule { }
