import {FollowingService} from "@/core/model/FollowingService";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";

export class HttpFollowingService implements FollowingService {
    constructor(private httpClient: HttpClient) {}

    async follow(follower: string, followee: string, token: string) {
        await this.httpClient.post('/follows', { follower, followee }, { Authorization: token })
    }
}