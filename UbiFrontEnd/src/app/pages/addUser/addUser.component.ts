
import { Component, OnInit } from "@angular/core";
import { RestService } from "../../services/rest.service";
import { FormGroup, Validators, FormControl } from "@angular/forms";
import { Person } from '../../models/person'
import { Router, ActivatedRoute } from "@angular/router";

@Component({
    selector: 'app-AddUser',
    styleUrls: ['./addUser.component.css'],
    templateUrl: './addUser.component.html'
})
export class AddUserComponent implements OnInit {

    userForm;
    userId:number;

    constructor(private rs: RestService,private router: Router,private route: ActivatedRoute){}

    ngOnInit(): void {
        // throw new Error("Method not implemented.");

        this.route.params.subscribe(params => {
            this.userId = params['id'];
            //set current position
            if (this.userId) {
              this.rs.get('/users/', this.userId).subscribe(
                (user) => {
                  this.populateForm(user);
                }
                , err => console.log(err)
              );
            }
          });

        this.userForm = new FormGroup({
            name: new FormControl('', Validators.required),
            email: new FormControl('', Validators.required)            
          });
    }

    /*
    * populate form with given user info
    */    
    populateForm(user: Person){
        this.userForm.patchValue({
            name: user.name,
            email: user.email
        });
    }

    /*
    * create or edit user
    */    
    addUser(user: Person){
        // console.log("creating new user");
        // console.log("user >>> " + JSON.stringify(user));

        let url:string='';
        if (user.id){
            url='/users/' + this.userId;
            this.rs.put(user, url).subscribe(
                (user) => this.goToUsers()
                , err => console.log(err)
            );
        }
        else{
            url='/users';
            this.rs.post(user, url).subscribe(
                (user) => this.goToUsers()
                , err => console.log(err)
            );
        }            
    }

    goToUsers(){
        console.log("go to /users");
        this.router.navigate(["users"]);
    }

    /*
    * form submit
    */
    
    onSubmit() {
        // console.log(this.userForm._value);
        let user: Person = new Person(this.userForm._value);

        if (this.userId)
          user.id = this.userId;

        this.addUser(user);
      }

}