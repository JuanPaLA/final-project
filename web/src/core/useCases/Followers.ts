import {FollowingService} from "@/core/model/FollowingService";

export class Followers {
    constructor(private followingService: FollowingService) { }

    async exec(requester: string, fromUser: string, token: string) {
        return await this.followingService.getFollowers(requester, fromUser, token)
    }
}