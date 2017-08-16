
import { Component, OnInit } from "@angular/core";
import { RestService } from "../../services/rest.service";
import { FormGroup, Validators, FormControl } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
    selector: 'app-AddMusic',
    styleUrls: ['./addMusic.component.css'],
    templateUrl: './addMusic.component.html'
})
export class AddMusicsComponent implements OnInit {

    musicForm;
    
    constructor(private rs: RestService,private router: Router,private route: ActivatedRoute){}
    
    ngOnInit(): void {}

}