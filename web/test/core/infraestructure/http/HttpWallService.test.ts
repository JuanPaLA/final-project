import {anything, instance, verify, when} from "ts-mockito";
import {FakeHttpResponse} from "./FakeHttpResponse";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {WallService} from "@/core/model/WallService";
import {HttpWallService} from "@/core/infrastructure/HttpWallService";

it('request timeline service', async ()=> {
    when(client.get(anything(), anything())).thenResolve(new FakeHttpResponse({id: 1}, 200))
    let headers = {Authorization: "aToken"}

    await service.getTimeline("@alice", "aToken")

    verify(client.get('/walls/@alice', headers )).called()
})

beforeEach(()=> {
    client = mockEq<HttpClient>()
    service = new HttpWallService(instance(client))
})

let client: HttpClient
let service: WallService