import {PostService} from "@/core/model/PostService";

export class Post {

    constructor(private postService: PostService) {}

    async exec(username: string, content: string, token: string) {
        await this.postService.createPost(username, content, token)
    }
}