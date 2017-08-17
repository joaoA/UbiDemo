export class Music {

    public  id: number; 	
    public name: string;
    public downloadUrl: string;
    public listenUrl: string;
    // public album:Album;
    
    public constructor(data: any = {}){
        if (data.id){
            this.id = data.id;
        } 
        this.name = data.name || '';
        this.downloadUrl = data.downloadUrl || '';
        this.listenUrl = data.listenUrl || '';
    }


}