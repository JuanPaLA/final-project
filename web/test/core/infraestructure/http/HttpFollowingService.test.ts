import {anything, instance, verify, when} from "ts-mockito";
import {FakeHttpResponse} from "./FakeHttpResponse";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {HttpFollowingService} from "@/core/infrastructure/HttpFollowingService";
import {FollowingService} from "@/core/model/FollowingService";

it('create following relationship', async ()=> {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse({id: 1}, 201))
    let headers = {Authorization: "aToken"}

    await service.follow("@alice", "@bob", "aToken")

    verify(client.post('/follows',{ follower: "@alice", followee: "@bob" }, headers )).called()
})

it('remove following relationship', async ()=> {
    when(client.put(anything(), anything())).thenResolve(new FakeHttpResponse({id: 1}, 201))
    let headers = {Authorization: "aToken"}

    await service.unfollow("@alice", "@bob", "aToken")

    verify(client.put('/follows',{ follower: "@alice", followee: "@bob" }, headers )).called()
})

it('get followers of given account', async () => {
    when(client.put(anything(), anything())).thenResolve(new FakeHttpResponse({id: 1}, 200))
    let headers = { Authorization: "aToken", Requester: "@alice" }

    await service.getFollowers("@alice", "@bob", "aToken")

    verify(client.get('/followers/@bob', headers )).called()
})

beforeEach(()=> {
    client = mockEq<HttpClient>()
    service = new HttpFollowingService(instance(client))
})

let client: HttpClient
let service: FollowingService