import {PostService} from "@/core/model/PostService";

export class Read {
    constructor(private postService: PostService) { }

    async exec(requester: string, author: string, token: string) {
        return await this.postService.getPosts(requester, author, token)
    }
}