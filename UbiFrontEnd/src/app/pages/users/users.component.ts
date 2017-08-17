
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
        this.getAllUsers();
    }

    goToUser(i){
        this.router.navigate(["users",i]);
    }

    deleteUser(id){
        this.rs.delete("/users/"+id).subscribe(
            (done) => {
                this.getAllUsers();             
          }, error => { console.log(error); });
    }


    getAllUsers(){
        this.rs.getAll("/users").subscribe(
            (users) => {
                this.userList=[];
                for (let a of users){
                    this.userList.push(new Person(a))
                }
          }, error => { console.log(error); });
    }
}