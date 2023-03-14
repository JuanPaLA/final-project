import {HttpUserService} from "@/core/infrastructure/HttpUserService";
import {instance, verify} from "ts-mockito";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {UserService} from "@/core/model/UserService";

it('signup sends name and password', async () => {
    let name = '@alice';
    let password = '1234';

    await service.signup(name, password)

    verify(client.post('/users', {name, password})).called()
})

beforeEach(() => {
    client = mockEq<HttpClient>()
    service = new HttpUserService(instance(client))
})

let service: UserService
let client: HttpClient
