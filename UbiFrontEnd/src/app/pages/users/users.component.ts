
import { Component, OnInit } from "@angular/core";
import { RestService } from "../../services/rest.service";
import { Person } from "../../models/person";
import { Router } from "@angular/router";

@Component({
    selector: 'app-users',
    styleUrls: ['./users.component.css'],
    templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {
    userList: Array<Person>=[];

    constructor(private rs: RestService,private router: Router){

    }


    ngOnInit(): void {
        this.rs.getAll("/users").subscribe(
            (users) => {
                for (let a of users){
                    this.userList.push(new Person(a))
                }
          }, error => { console.log(error); });
    }

    goToUser(i){
        this.router.navigate(["users",i]);
    }
}