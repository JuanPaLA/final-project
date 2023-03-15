import {UserService} from '@/core/model/UserService'
import {HttpClient} from '@/core/infrastructure/http/HttpClient'
import {RepeatedUserError} from "@/core/infrastructure/RepeatedUserError";
import {PostResponse} from "@/core/infrastructure/http/HttpResponse";

export class HttpUserService implements UserService {
    constructor(private httpClient: HttpClient) {}

    async signup(name: string, password: string) {
        let response = await this.httpClient.post<PostResponse>('/users', { name, password })
        if (response.status === 201) {
            return
        }
        if (response.status === 409) {
            throw new RepeatedUserError('Repeated username')
        } else {
            throw new Error('Unexpected server error')
        }
    }
}
