import {HttpResponse, ReadResponse, UsersListResponse} from "@/core/infrastructure/http/HttpResponse";

export interface WallService {
    getTimeline(name: string, token: string): Promise<HttpResponse<ReadResponse>>
}