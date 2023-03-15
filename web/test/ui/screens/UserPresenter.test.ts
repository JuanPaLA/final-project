import {expect} from "expect";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {Router} from "@/ui/services/router/Router";
import {Read} from "@/core/useCases/Read";
import {anything, instance, mock, verify, when} from "ts-mockito";
import {SessionState} from "@/session/SessionState";

it('posts starts empty', () => {
    when(session.getSession()).thenReturn({name: "alice", token: "1234"})

    expect(presenter.model.posts).toEqual(null)

    presenter.start()

    expect(presenter.model.posts).toEqual(null)
})

it('posts are fetched on mounting', async () => {
    when(session.getSession()).thenReturn({name: "alice", token: "1234"})

    await presenter.start()

    verify(read.exec(anything(), anything(), anything())).once()
})

beforeEach(() => {
    router = mock<Router>()
    read = mock<Read>()
    session = mock<SessionState>()
    presenter = new UserPresenter(changed, instance(session), instance(read), instance(router))
})

let presenter: UserPresenter
let router: Router
let read: Read
let session : SessionState
const changed = () => {}