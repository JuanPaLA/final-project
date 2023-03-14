import {Signup} from "@/core/useCases/Signup";
import {instance, mock, verify} from 'ts-mockito'
import {HttpUserService} from "@/core/infrastructure/HttpUserService";
import {UserService} from "@/core/model/UserService";

it('signup request service with given username and password', () => {
    signup.exec("@alice", "1234")

    verify(service.signup("@alice", "1234")).once()
})

beforeEach(() => {
    service = mock<UserService>()

    signup = new Signup(instance(service))
})

let service: UserService
let signup: Signup
