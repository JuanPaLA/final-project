import {instance, mock, verify} from "ts-mockito";
import {Post} from "@/core/useCases/Post";
import {PostService} from "@/core/model/PostService";
import {Read} from "@/core/useCases/Read";

it('read posts request service with given users and token', async () => {
    await read.exec("@alice", "@bob", "aToken")

    verify(service.getPosts("@alice", "@bob", "aToken")).once()
})

beforeEach(()=> {
    service = mock<PostService>()
    read = new Read(instance(service))
})

let read: Read
let service: PostService