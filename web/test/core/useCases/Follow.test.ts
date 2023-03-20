import {anything, instance, mock, verify} from 'ts-mockito'
import {Follow} from "@/core/useCases/Follow";
import {FollowingService} from "@/core/model/FollowingService";

it('signup request service with given username and password', () => {
    followTest.exec(anything(), anything(), anything())

    verify(service.follow(anything(), anything(), anything())).once()
})

beforeEach(() => {
    service = mock<FollowingService>()
    followTest = new Follow(instance(service))
})

let service: FollowingService
let followTest: Follow
