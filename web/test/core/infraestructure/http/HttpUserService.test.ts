import {HttpUserService} from "@/core/infrastructure/HttpUserService";
import {anything, instance, verify, when} from "ts-mockito";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {UserService} from "@/core/model/UserService";
import {FakeHttpResponse} from "./FakeHttpResponse";
import {expect} from "expect";
import {RepeatedUserError} from "@/core/infrastructure/RepeatedUserError";

it('valid signup sends name and password', async () => {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse(null, 201))
    let name = '@alice';
    let password = '1234';

    await service.signup(name, password)

    verify(client.post('/users', {name, password})).called()
})

it('users can not be created with repeated names', async ()=> {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse(null, 409))
    let name = '@alice';
    let password = '1234';

    await expect(service.signup(name, password)).rejects.toEqual(new RepeatedUserError('Repeated username'))
})

it('failed signup request throws error', async ()=> {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse(null, 500))
    let name = '@alice';
    let password = '1234';

    await expect(service.signup(name, password)).rejects.toEqual(new Error('Unexpected server error'))
})


beforeEach(() => {
    client = mockEq<HttpClient>()
    service = new HttpUserService(instance(client))
})

let service: UserService
let client: HttpClient
