
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

    constructor(private rs: RestService,private router: Router){}

    ngOnInit(): void {
       this.getAllMusics();
    }

    goToMusic(i){
        this.router.navigate(["musics",i]);
    }


    deleteMusic(id){
        this.rs.delete("/musics/"+id).subscribe(
            (done) => {
                console.log("apagou");                
                this.getAllMusics();
          }, error => { console.log(error); });
    }

    getAllMusics(){
        this.rs.getAll("/musics").subscribe(
            (musics) => {
                this.musicList=[];
                for (let a of musics){
                    this.musicList.push(new Music(a))
                }
          }, error => { console.log(error); });
    }
}