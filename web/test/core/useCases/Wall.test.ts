import {instance, mock, verify} from 'ts-mockito'
import {WallService} from "@/core/model/WallService";
import {Wall} from "@/core/useCases/Wall";

it('signup request service with given username and password', () => {
    wall.exec("@alice", "1234")

    verify(service.getTimeline("@alice", "1234")).once()
})

beforeEach(() => {
    service = mock<WallService>()
    wall = new Wall(instance(service))
})

let service: WallService
let wall: Wall
