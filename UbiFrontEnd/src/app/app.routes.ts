import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GettingStartedComponent } from './pages/first/first.component'
import { AddUserComponent } from './pages/addUser/addUser.component';
import { UsersComponent } from './pages/users/users.component';

import { MusicsComponent } from './pages/musics/music.component';
import { AddMusicsComponent } from './pages/addMusic/addMusic.component';

export const routes = [
    {
      path: '',
      data: [],
      component: GettingStartedComponent
    },
    {
      path: 'users',
      data: [],
      component: UsersComponent
    },
    {
      path: 'newuser',
      data: [],
      component: AddUserComponent
    },
    {
      path: 'users/:id',
      data: [],
      component: AddUserComponent
    },
    {
      path: 'musics',
      data: [],
      component: MusicsComponent
    },
    {
      path: 'newmusic',
      data: [],
      component: AddMusicsComponent
    },
    {
      path: 'musics/:id',
      data: [],
      component: AddMusicsComponent
    },
]

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);