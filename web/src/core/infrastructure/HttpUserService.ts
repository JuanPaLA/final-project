import {UserService} from '@/core/model/UserService'
import {HttpClient} from '@/core/infrastructure/http/HttpClient'
import {RepeatedUserError} from "@/core/infrastructure/RepeatedUserError";
import {HttpResponse, ReadResponse, UserResponse, UsersListResponse} from "@/core/infrastructure/http/HttpResponse";

export class HttpUserService implements UserService {
    constructor(private httpClient: HttpClient) {}

    async createUser(name: string, password: string) {
        try {
            await this.httpClient.post<UserResponse>('/users', { name, password })
        } catch (e) {
            if (e.response.status === 409) {
                throw new RepeatedUserError(`User ${name} already exists`)
            } else {
                throw new Error(e.message)
            }
        }
    }

    async getUsers(name: string, token: string): Promise<HttpResponse<UsersListResponse>> {
        return  await this.httpClient.get('/users', { Authorization: token, Requester: name })
    }
}
