import {UserResponse} from "@/core/infrastructure/http/HttpResponse";

export interface UserService {
    signup(name: string, password: string): Promise<any>
}
