import {Signup} from "@/core/useCases/Signup";
import {instance, mock, verify} from 'ts-mockito'
import {UserService} from "@/core/model/UserService";
import {Unfollow} from "@/core/useCases/Unfollow";
import {FollowingService} from "@/core/model/FollowingService";

it('signup request service with given username and password', () => {
    unfollow.exec("@alice", "@bob", "aToken")

    verify(service.unfollow("@alice", "@bob", "aToken")).once()
})

beforeEach(() => {
    service = mock<FollowingService>()
    unfollow = new Unfollow(instance(service))
})

let service: FollowingService
let unfollow: Unfollow
