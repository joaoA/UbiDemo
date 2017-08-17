import { Component } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'getting-started',
  styleUrls: ['./first.component.css'],
  templateUrl: './first.component.html'
})
export class GettingStartedComponent {
  public name = `Native Angular widgets for Bootstrap 3 and Bootstrap 4`;

  constructor(private router: Router){

  }

  goToUsers(){
    this.router.navigate(["users"]);
  }

  goToMusics(){
    this.router.navigate(["musics"]);
  }
}