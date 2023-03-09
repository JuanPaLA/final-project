import {Signup} from "@/core/useCases/Signup";
import {instance, mock, verify} from 'ts-mockito'
import {HttpUserService} from "@/core/infrastructure/HttpUserService";

it('signup request service with given username and password', () => {
    signup.exec("@alice", "1234")

    verify(httpService.signup("@alice", "1234")).once()
})

beforeEach(() => {
    httpService = mock(HttpUserService)
    signup = new Signup(instance(httpService))
})

let httpService: HttpUserService
let signup: Signup
