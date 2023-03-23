import {HttpClient} from "./HttpClient";
import { PostMessageResponse, PostService, ReadResponse, WallResponse } from '../../model/post/PostService'
import { InvalidPostError } from '../../model/post/InvalidPostError'

export class HttpPostService implements PostService{
    constructor(private httpClient: HttpClient) {}

    async postMessage(message: string, sessionToken: string): Promise<PostMessageResponse> {
        const response = await this.httpClient.post('/post', {message}, {Authorization: sessionToken})
        if (response.statusCode !== 201) throw new InvalidPostError(response.data.error)
        return {
            postId: response.data.postId
        }
    }

    async wall(sessionToken: string): Promise<WallResponse> {
        const response = await this.httpClient.get('/wall', {Authorization: sessionToken})
        return {
            posts: response.data.posts
        }
    }

    read(username: string): Promise<ReadResponse> {
        return Promise.resolve(undefined);
    }
}