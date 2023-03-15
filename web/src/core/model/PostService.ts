export interface PostService {
    createPost(username: string, content: string, token: string): Promise<void>
    getPosts(requester: string, author: string, token: string): Promise<any>
}