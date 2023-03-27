import { AuthService, SignUpResponse } from '../model/auth/AuthService'

export class SignUp {
    constructor(private authService: AuthService) { }

    async exec(name: string, password: string): Promise<void> {
        return await this.authService.signUp(name, password)
    }
}