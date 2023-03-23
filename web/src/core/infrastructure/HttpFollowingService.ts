import {FollowingService} from "@/core/model/FollowingService";
import {HttpClient} from "@/core/infrastructure/http/HttpClient";
import {FollowersResponse, FollowingsResponse, HttpResponse} from "@/core/infrastructure/http/HttpResponse";

export class HttpFollowingService implements FollowingService {
    constructor(private httpClient: HttpClient) {}

    async follow(follower: string, followee: string, token: string) {
        await this.httpClient.post('/follows', { follower, followee }, { Authorization: token })
    }

    async unfollow(follower: string, followee: string, token: string) {
        await this.httpClient.put('/follows', { follower, followee }, { Authorization: token })
    }

    async getFollowers(requester: string, account: string, token: string): Promise<HttpResponse<FollowersResponse>> {
        return await this.httpClient.get(`/followers/${account}`, { Authorization: token, Requester: requester })
    }

    async getFollowing(requester: string, account: string, token: string): Promise<HttpResponse<FollowingsResponse>> {
        return await this.httpClient.get(`/followings/${account}`, { Authorization: token, Requester: requester })
    }
}
