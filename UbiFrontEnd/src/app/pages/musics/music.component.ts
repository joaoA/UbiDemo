
import { Component, OnInit } from "@angular/core";
import { RestService } from "../../services/rest.service";
import { Music } from "../../models/music";
import { Router } from "@angular/router";


@Component({
    selector: 'app-music',
    styleUrls: ['./music.component.css'],
    templateUrl: './music.component.html'
})
export class MusicsComponent implements OnInit {
    
    musicList:Array<Music>=[];

    constructor(private rs: RestService,private router: Router){

    }


    ngOnInit(): void {
        this.rs.getAll("/musics").subscribe(
            (musics) => {
                for (let a of musics){
                    this.musicList.push(new Music(a))
                }
          }, error => { console.log(error); });
    }

    goToMusic(i){
        this.router.navigate(["users",i]);
    }

}