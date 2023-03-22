import {expect} from "expect";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {Router} from "@/ui/services/router/Router";
import {Read} from "@/core/useCases/Read";
import {anything, instance, mock, verify, when} from "ts-mockito";
import {SessionState} from "@/session/SessionState";
import {Followers} from "@/core/useCases/Followers";
import {mockEq} from "../../common/ts-mockito-extensions";
import {Followings} from "../../core/useCases/Followings";

it('users posts are fetched on mounting', async () => {
    when(session.getSession()).thenReturn({name: "alice", token: "1234"})

    await presenter.start()

    verify(read.exec(anything(), anything(), anything())).called()
})

it('users followers are fetched on mounting', async () => {
    when(session.getSession()).thenReturn({name: "alice", token: "1234"})

    await presenter.start()

    verify(followers.exec(anything(), anything(), anything())).once()
})

beforeEach(() => {
    router = mock<Router>()
    read = mockEq<Read>()
    followers = mockEq<Followers>()
    followings = mockEq<Followings>()
    session = mock<SessionState>()
    presenter = new UserPresenter(changed, instance(session), instance(read), instance(router), instance(followers), instance(followings))
})

let presenter: UserPresenter
let router: Router
let followers: Followers
let followings: Followings
let read: Read
let session : SessionState
const changed = () => {}