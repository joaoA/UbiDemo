
import { Music } from '../models/music'

export class Person{

    public  id: number; 	
    public name: string;
    public email: string;
    public favoriteMusics:Array<Music>

    public constructor(data: any = {}){      
        if (data.id){
            this.id = data.id;
        }  
        this.name = data.name || '';
        this.email = data.email || '';
        this.favoriteMusics = data.favMusics;
    }

}