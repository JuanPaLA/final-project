import { AuthService, SignUpResponse } from '../model/auth/AuthService'

export class SignUp {
    constructor(private authService: AuthService) {
    }

    async exec(username: string, password: string): Promise<SignUpResponse> {
        return await this.authService.signUp(username, password)
    }
}