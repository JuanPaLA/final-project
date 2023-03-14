import {LoginResponse} from "@/core/infrastructure/http/HttpResponse";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {AuthService} from "@/core/model/AuthService";
import {SessionState} from "@/session/SessionState";
import {UserSession} from "@/session/UserSession";
import {InvalidCredentialsError} from "@//core/infrastructure/InvalidCredentialsError";
import {UserNotFoundError} from "@/core/infrastructure/UserNotFoundError";

export class HttpAuthService implements AuthService {
    constructor(private httpClient: HttpClient, private session: SessionState) {}

    async login(name: string, password: string) {
        let response = await this.httpClient.post<LoginResponse>('/login', { name, password })
        if (response.status == 200) {
            let userSession = new UserSession(name, response.body.token)
            this.session.authenticate(userSession)
        } else {
            if (response.status === 401) {
                throw new InvalidCredentialsError('Invalid credentials')
            } else if (response.status === 404) {
                throw new UserNotFoundError('User not found')
            } else {
                throw new Error('Unexpected error')
            }
        }
    }
}