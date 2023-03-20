import {FollowingService} from "@/core/model/FollowingService";

export class Follow {
    constructor(private followingService: FollowingService) { }

    async exec(follower: string, followee: string, token: string) {
        return await this.followingService.follow(follower, followee, token)
    }
}
