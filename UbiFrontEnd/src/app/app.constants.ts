export class Configuration {
    private server: string = 'https://evening-lake-70202.herokuapp.com';
    private apiUrl: string = '/api';

    getServerWithApiUrl(){
        return this.server + this.apiUrl;
    }
}