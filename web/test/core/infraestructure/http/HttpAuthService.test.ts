import {anything, instance, verify, when} from "ts-mockito";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {HttpAuthService} from "@/core/infrastructure/HttpAuthService";
import {SessionState} from "@/session/SessionState";
import {AuthService} from "@/core/model/AuthService";
import {FakeHttpResponse} from "./FakeHttpResponse";
import {InvalidCredentialsError} from "@/core/infrastructure/InvalidCredentialsError";
import {UserNotFoundError} from "@/core/infrastructure/UserNotFoundError";

it('login sends name and password post request to /login', async () => {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse({token: "token"}, 200))
    let name = '@alice';
    let password = '1234';

    await service.login('@alice', '1234')

    verify(client.post('/login', {name, password})).called()
})

it('login saves token in session', async () => {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse({token: "token"}, 200))

    await service.login('@alice', '1234')

    verify(session.authenticate(anything())).called()
})

it('login with invalid credentials throws error', async () => {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse(null, 401))

    await expect(service.login('@alice', '1234')).rejects.toEqual(new InvalidCredentialsError('Invalid credentials for user @alice'))
    verify(session.authenticate(anything())).never()
})

it('login request fails with non existing users', async () => {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse(null, 404))

    await expect(service.login('@alice', '1234')).rejects.toEqual(new UserNotFoundError('Invalid credentials for user @alice'))
    verify(session.authenticate(anything())).never()
})

beforeEach(() => {
    session = mockEq<SessionState>()
    client = mockEq<HttpClient>()
    service = new HttpAuthService(instance(client), instance(session))
})

let service: AuthService
let client: HttpClient
let session: SessionState
