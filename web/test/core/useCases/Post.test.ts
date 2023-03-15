import {instance, mock, verify} from "ts-mockito";
import {Post} from "@/core/useCases/Post";
import {PostService} from "@/core/model/PostService";

it('post request service with given username, content and token', async () => {
    await post.exec("@alice", "What a beautiful day!", "aToken")

    verify(service.createPost("@alice", "What a beautiful day!", "aToken")).once()
})

beforeEach(()=> {
    service = mock<PostService>()
    post = new Post(instance(service))
})

let post: Post
let service: PostService