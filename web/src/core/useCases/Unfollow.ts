import { FollowingService } from "@/core/model/FollowingService";

export class Unfollow {
    constructor(private followingService: FollowingService) {}

    async exec(follower: string, followee: string, token: string) {
        await this.followingService.unfollow(follower, followee, token)
    }
}