
import { Component, OnInit } from "@angular/core";
import { RestService } from "../../services/rest.service";
import { FormGroup, Validators, FormControl } from "@angular/forms";
import { Person } from '../../models/person'
import { Router, ActivatedRoute } from "@angular/router";
import { IMultiSelectOption } from 'angular-2-dropdown-multiselect';
import { Music } from "../../models/music";



@Component({
    selector: 'app-AddUser',
    styleUrls: ['./addUser.component.css'],
    templateUrl: './addUser.component.html'
})
export class AddUserComponent implements OnInit {

    userForm;
    userId:number;
    musics: Array<Music>=[];
    optionsModel: number[]=[];    
    myOptions: IMultiSelectOption[];

    constructor(private rs: RestService,private router: Router,private route: ActivatedRoute){}

    ngOnInit(): void {        
        this.rs.getAll("/musics").subscribe(
            (musics) => {
                this.myOptions=[];
                for (let a of musics){                    
                    this.musics.push(new Music(a))
                    this.myOptions.push({ id: parseInt(a.id), name: a.name });
                    // this.optionsModel.push(a.id);
                }                
        }, error => { console.log(error); });
        
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
            email: new FormControl('', Validators.required),
            optionsModel:new FormControl()
          });

    }

    /*
    * populate form with given user info
    */    
    populateForm(user: Person){
        this.userForm.patchValue({
            name: user.name,
            email: user.email,
            optionsModel: user.favoriteMusics.map(({ id }) => id)
        });
        
    }

    /*
    * create or edit user
    */    
    addUser(user: Person){
        console.log("creating new user");
        console.log("user >>> " + JSON.stringify(user));

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
        // console.log("go to /users");
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

        if(this.userForm.value.optionsModel != null && this.userForm.value.optionsModel.length > 0){
            user.favoriteMusics = [];
            for(let m of this.userForm.value.optionsModel){          
                var list:Music[] = this.musics.filter((item) => item.id == m);
                if(list!=null)
                    user.favoriteMusics.push(list[0]);
            }
        }
        console.log("this.userForm.value.optionsModel >> " + JSON.stringify(this.userForm.value.optionsModel))
        console.log("user >> " + JSON.stringify(user))

        this.addUser(user);
    }

}