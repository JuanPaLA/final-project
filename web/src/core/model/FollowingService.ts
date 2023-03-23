import {FollowersResponse, FollowingsResponse, HttpResponse} from "@/core/infrastructure/http/HttpResponse";

export interface FollowingService {
    follow(follower: string, followee: string, token: string): Promise<void>

    unfollow(follower: string, followee: string, token: string): Promise<void>;

    getFollowers(requester: string, account: string, token: string): Promise<HttpResponse<FollowersResponse>>;

    getFollowing(requester: string, account: string, token: string): Promise<HttpResponse<FollowingsResponse>>;
}