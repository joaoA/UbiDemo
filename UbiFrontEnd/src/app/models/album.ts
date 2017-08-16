import { Music } from '../models/music';
import { Artist } from '../models/artist';


export class Album {
    
    public  id: number; 	
    public name: string;
    public musics:Array<Music>;
    public artist:Artist;
    
    public constructor(data: any = {}){
        this.name = data.name || '';
        this.musics = data.music || null ;
        this.artist = data.artist || null;
    }    
}