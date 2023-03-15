import {anything, instance, mock, verify, when} from "ts-mockito";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {HttpPostService} from "@/core/infrastructure/HttpPostService";
import {mockEq} from "../../../common/ts-mockito-extensions";
import {PostService} from "@/core/model/PostService";
import {FakeHttpResponse} from "./FakeHttpResponse";

it('createPost with username and contents', async ()=> {
    when(client.post(anything(), anything())).thenResolve(new FakeHttpResponse({id: 1}, 201))
    let username = "@alice"
    let content = "What a beautiful day!"
    let token = "aToken"
    let headers = {Authorization: token}

    await service.createPost(username, content, token)

    verify(client.post('/posts', anything(), anything())).called()
})

it('getPosts fetch posts', async ()=> {
    when(client.get(anything(), anything())).thenResolve(new FakeHttpResponse({posts: null}, 200))
    let headers = {Authorization: "token", Requester: "username"}

    service.getPosts(anything(), anything(), anything())

    verify(client.get(anything())).called()
})

beforeEach(()=> {
    client = mockEq<HttpClient>()
    service = new HttpPostService(instance(client))
})

let client: HttpClient
let service: PostService