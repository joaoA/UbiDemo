
import { Album } from '../models/album';

export class Artist {
    
    public  id: number; 	
    public name: string;
    public albuns:Array<Album>
    
    public constructor(data: any = {}){
        this.name = data.name || '';
    
    }    
}