import { HttpMethod } from './HttpMethod'
import { Post } from "@/core/useCases/Post";
import {UserVM} from "@/ui/viewModels/UserVM";

export interface HttpResponse<T> {
    method: HttpMethod;
    status: number;
    body: T;
    baseUrl: string;
    url: string;
    relativeUrl: string;
    headers: { [key: string]: string };
    requestData: any;
}

export interface LoginResponse {
    token: string;
    payload: string;
    error?: string;
}

export interface PostResponse {
    id?: number;
    error?: string;
}

export interface ReadResponse {
    posts: Post[] | null;
    error?: string;
}

export interface UserResponse {
    error?: string
}

export interface UsersListResponse {
    users: UserVM[]
}