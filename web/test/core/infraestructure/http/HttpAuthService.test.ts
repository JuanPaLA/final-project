import {anything, instance, mock, verify, when} from "ts-mockito";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {HttpAuthService} from "@/core/infrastructure/HttpAuthService";
import {SessionState} from "@/session/SessionState";
import {AuthService} from "@/core/model/AuthService";
import {HttpResponse, LoginResponse} from "@/core/infrastructure/http/HttpResponse";
import {UserSession} from "@/session/UserSession";
import exp from "constants";
import {expect} from "expect";

it('login sends name and password post request to /login', async () => {
    let name = '@alice';
    let password = '1234';
    let response = <HttpResponse<LoginResponse>>({
        body: {
            token: '1234'
        }})

    when(client.post('/login', anything())).thenResolve(response)

    await service.login('@alice', '1234')

    verify(client.post('/login', {name, password})).called()
})

it('login saves token in session', async () => {
    let name = '@alice';
    let password = '1234';
    let response = <HttpResponse<LoginResponse>>({
            body: {
                token: '1234'
            }})

    when(client.post('/login', {name, password})).thenResolve(response)

    await service.login('@alice', '1234')

    verify(session.authenticate(new UserSession("@alice", "1234"))).called()
})

// it('failed login throws AxiosError with code 403', async() => {
//
// })

beforeEach(() => {
    session = mockEq<SessionState>()
    client = mockEq<HttpClient>()
    service = new HttpAuthService(instance(client), instance(session))
})

let service: AuthService
let client: HttpClient
let session: SessionState
