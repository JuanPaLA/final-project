import {PostService} from "@/core/model/PostService";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {InvalidCredentialsError} from "@/core/infrastructure/InvalidCredentialsError";
import {HttpResponse, PostResponse, ReadResponse} from "@/core/infrastructure/http/HttpResponse";
import {UserNotFoundError} from "@/core/infrastructure/UserNotFoundError";
import {UserNotAuthenticatedError} from "@/core/infrastructure/UserNotAuthenticatedError";

export class HttpPostService implements PostService {
    constructor(private httpClient: HttpClient) {}

    async createPost(username: string, content: string, token: string) {
        try {
            await this.httpClient.post<PostResponse>('/posts', { username, content }, { Authorization: token })
        } catch (e) {
            if (e.response.status === 401) {
                throw new InvalidCredentialsError('Invalid credentials')
            } else if(e.response.status === 404) {
                throw new UserNotFoundError('User not found')
            } else if(e.response.status === 400) {
                throw new InvalidCredentialsError('invalid arguments')
            } else if(e.response.status === 403) {
                    throw new UserNotAuthenticatedError('invalid credentials')
            } else {
                throw new Error('Unexpected error')
            }
        }
    }

    async getPosts(requester: string, author: string, token: string): Promise<HttpResponse<ReadResponse>> {
        try {
            let url = `/posts/${author}`
            let response = await this.httpClient.get<ReadResponse>(url, {
                Authorization: token,
                Requester: requester
            })
            return response
        } catch (e) {
            if (e.status != 200) {
                throw Error('Unexpected server error')
            }
        }
    }
}

