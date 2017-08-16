export class Configuration {
    private server: string = 'http://localhost:8080';
    private apiUrl: string = '/api';

    getServerWithApiUrl(){
        return this.server + this.apiUrl;
    }
}