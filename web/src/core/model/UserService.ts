import {HttpResponse, ReadResponse, UserResponse, UsersListResponse} from "@/core/infrastructure/http/HttpResponse";

export interface UserService {
    createUser(name: string, password: string): Promise<any>

    getUsers(name: string, token: string): Promise<HttpResponse<UsersListResponse>>
}
