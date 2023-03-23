import {instance, mock, verify} from "ts-mockito";
import {FollowingService} from "@/core/model/FollowingService";
import {Followers} from "@/core/useCases/Followers";

it('read posts request service with given users and token', async () => {
    await followers.exec("@alice", "@bob", "aToken")

    verify(service.getFollowers("@alice", "@bob", "aToken")).once()
})

beforeEach(()=> {
    service = mock<FollowingService>()
    followers = new Followers(instance(service))
})

let followers: Followers
let service: FollowingService