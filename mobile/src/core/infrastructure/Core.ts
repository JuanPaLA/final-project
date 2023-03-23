import { AuthService } from '@/core/model/auth/AuthService'
import { HttpAuthService } from '@/core/infrastructure/http/HttpAuthService'
import { HttpPostService } from '@/core/infrastructure/http/HttpPostService'
import { HttpClient } from '@/core/infrastructure/http/HttpClient'
import { SignUp } from '@/core/useCases/Signup'

export class Core {
    private readonly authService: AuthService

    constructor(private config: CoreConfig) {
        this.authService = new HttpAuthService(config.httpClient)
    }

    signUp = () => new SignUp(this.authService)
}

export interface CoreConfig {
    httpClient: HttpClient
}
