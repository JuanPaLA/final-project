import {PostService} from "@/core/model/PostService";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {InvalidCredentialsError} from "@/core/infrastructure/InvalidCredentialsError";
import {PostResponse, ReadResponse} from "@/core/infrastructure/http/HttpResponse";

export class HttpPostService implements PostService {
    constructor(private httpClient: HttpClient) {}

    async createPost(username: string, content: string, token: string) {
        let response = await this.httpClient.post<PostResponse>('/posts', { username, content }, { Authorization: token })
        if (response.status === 201) {
            return
        } else {
            if (response.status === 401) {
                throw new InvalidCredentialsError('Invalid credentials')
            } else if(response.status === 404) {
                throw new InvalidCredentialsError('Invalid credentials')
            } else if(response.status === 400) {
                throw new InvalidCredentialsError('invalid arguments')
            } else if(response.status === 500) {
                throw new Error('Unexpected error')
            }
        }
    }

    async getPosts(requester: string, author: string, token: string): Promise<ReadResponse> {
        let url = `/posts/${author}`
        let response = await this.httpClient.get<ReadResponse>(url, {
            Authorization: token,
            Requester: requester
        })
        if (response.status === 200) {
            return
        } else {
            if (response.status === 401) {
                throw new InvalidCredentialsError('Invalid credentials')
            } else if(response.status === 404) {
                throw new InvalidCredentialsError('Invalid credentials')
            } else if(response.status === 400) {
                throw new InvalidCredentialsError('invalid arguments')
            } else if(response.status === 500) {
                throw new Error('Unexpected error')
            }
        }

    }
}

