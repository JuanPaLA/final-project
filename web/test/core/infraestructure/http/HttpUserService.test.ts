import {HttpUserService} from "@/core/infrastructure/HttpUserService";
import {instance, verify} from "ts-mockito";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {mockEq} from "../../../common/ts-mockito-extensions";

it('signup sends name and password', async () => {
    let name = '@alice';
    let password = '1234';

    await httpService.signup(name, password)

    verify(httpClient.post('/users', {name, password})).called()
})

beforeEach(() => {
    httpClient = mockEq<HttpClient>()
    httpService = new HttpUserService(instance(httpClient))
})

let httpService: HttpUserService
let httpClient: HttpClient
