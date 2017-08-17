
import { Component, OnInit } from "@angular/core";
import { RestService } from "../../services/rest.service";
import { FormGroup, Validators, FormControl } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { Music } from "../../models/music";

@Component({
    selector: 'app-AddMusic',
    styleUrls: ['./addMusic.component.css'],
    templateUrl: './addMusic.component.html'
})
export class AddMusicsComponent implements OnInit {

    musicForm;
    musicId:number;
    constructor(private rs: RestService,private router: Router,private route: ActivatedRoute){}
    
    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.musicId = params['id'];
            //set current position
            if (this.musicId) {
              this.rs.get('/musics/', this.musicId).subscribe(
                (music) => {
                  this.populateForm(music);
                }
                , err => console.log(err)
              );
            }
        });

        this.musicForm = new FormGroup({
            name: new FormControl('', Validators.required),
            downloadUrl: new FormControl('') ,           
            listenUrl: new FormControl('')            
        });
    }

    /*
    * populate form with given user info
    */    
    populateForm(music: Music){
        this.musicForm.patchValue({
            name: music.name,
            downloadUrl: music.downloadUrl,
            listenUrl: music.listenUrl
        });
    }

    /*
    * create or edit user
    */    
    addMusic(music: Music){        
        let url:string='';
        if (music.id){
            url='/musics/' + this.musicId;
            this.rs.put(music, url).subscribe(
                (user) => this.goToMusics()
                , err => console.log(err)
            );
        }
        else{
            url='/musics';
            this.rs.post(music, url).subscribe(
                (user) => this.goToMusics()
                , err => console.log(err)
            );
        }            
    }

    goToMusics(){
        this.router.navigate(["musics"]);
    }

    onSubmit() {
        // console.log(this.userForm._value);
        let music: Music = new Music(this.musicForm._value);

        if (this.musicId)
          music.id = this.musicId;

        this.addMusic(music);
    }

}