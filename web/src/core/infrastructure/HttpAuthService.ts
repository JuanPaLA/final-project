import {LoginResponse} from "@/core/infrastructure/http/HttpResponse";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {AuthService} from "@/core/model/AuthService";
import {SessionState} from "@/session/SessionState";
import {UserSession} from "@/session/UserSession";
import {InvalidCredentialsError} from "@//core/infrastructure/InvalidCredentialsError";

export class HttpAuthService implements AuthService {
    constructor(private httpClient: HttpClient, private session: SessionState) {}

    async login(name: string, password: string) {
        try {
            let response = await this.httpClient.post<LoginResponse>('/login', { name, password })
            let userSession = new UserSession(name, response.body.token)
            this.session.authenticate(userSession)
        } catch (e) {
            if (e.response.status === 401) {
                throw new InvalidCredentialsError(`Invalid credentials for user ${name}`)
            } else if (e.response.status == 404) {
                throw new InvalidCredentialsError(`Invalid credentials for user ${name}`)
            } else {
                throw new Error("Unexpected server error")
            }
        }
    }
}
