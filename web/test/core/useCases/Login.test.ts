import {instance, mock, verify, when} from "ts-mockito";
import {Login} from "@/core/useCases/Login";
import {SessionState} from "@/session/SessionState";
import {AuthService} from "@/core/model/AuthService";
import {Router} from "@/ui/services/router/Router";

it('login request service with given username and password', () => {
    login.exec("@alice", "1234")

    verify(service.login("@alice", "1234")).once()
})

beforeEach(() => {
    session = mock<SessionState>()
    service = mock<AuthService>()
    login = new Login(instance(service))
    router = mock<Router>()
})

let service: AuthService
let login: Login
let session: SessionState
let router: Router
