import { UserService } from '@/core/model/UserService'
import { HttpClient } from '@/core/infrastructure/http/HttpClient'

export class HttpUserService implements UserService {
    constructor(private httpClient: HttpClient) {}

    async signup(name: string, password: string) {
        await this.httpClient.post('/users', { name, password })
    }
}
