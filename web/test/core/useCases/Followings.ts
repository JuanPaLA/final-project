import {FollowingService} from "@/core/model/FollowingService";

export class Followings {
    constructor(private followingService: FollowingService) { }

    async exec(requester: string, fromUser: string, token: string) {
        return await this.followingService.getFollowing(requester, fromUser, token)
    }
}