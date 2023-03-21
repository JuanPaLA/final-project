import {WallService} from "@/core/model/WallService";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {HttpResponse, ReadResponse} from "@/core/infrastructure/http/HttpResponse";

export class HttpWallService implements WallService {
    constructor(private httpClient: HttpClient) { }

    async getTimeline(name: string, token: string): Promise<HttpResponse<ReadResponse>> {
        return  await this.httpClient.get(`/walls/${name}`, { Authorization: token })
    }
}