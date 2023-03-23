import { AuthService, LoginResponse } from '../../model/auth/AuthService'
import { HttpClient } from './HttpClient'
import { InvalidAuthError } from '../../model/auth/InvalidAuthError'

export class HttpAuthService implements AuthService {
    constructor(private httpClient: HttpClient) {}

    async login(username: string, password: string): Promise<LoginResponse> {
        const response = await this.httpClient.post('/login', { username, password })
        if (response.statusCode != 200) throw new InvalidAuthError(response.data.error)
        return {
            sessionToken: response.data.sessionToken
        }
    }

    async signUp(name: string, password: string): Promise<void> {
        const response = await this.httpClient.post('/users', { name, password })
        if (response.statusCode !== 201) throw new InvalidAuthError(response.data.error)
    }
}
