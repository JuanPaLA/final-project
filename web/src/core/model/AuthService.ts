import {HttpResponse, LoginResponse} from "@/core/infrastructure/http/HttpResponse";

export interface AuthService {
    login(name: string, password: string)
}