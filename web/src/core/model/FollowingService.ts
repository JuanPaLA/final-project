export interface FollowingService {
    follow(follower: string, followee: string, token: string): Promise<void>

    unfollow(follower: string, followee: string, token: string): Promise<void>;
}