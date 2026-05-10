export class LoginRequest{

    private email!: string;
    private password!: string;

    constructor(){
        
    }

    public getPassword(): string {
        return this.password;
    }
    public setPassword(value: string) {
        this.password = value;
    }

    public getEmail(): string {
        return this.email;
    }
    public setEmail(value: string) {
        this.email = value;
    }

}