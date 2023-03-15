import {Router} from "@/ui/services/router/Router";
import {HomePresenter} from "@/ui/screens/home/HomePresenter";
import {anything, instance, mock, verify, when} from "ts-mockito";
import {SessionState} from "@/session/SessionState";
import {Post} from "@/core/useCases/Post";
import {expect} from "expect";
import {UserSession} from "@/session/UserSession";
import {mockEq} from "../../common/ts-mockito-extensions";

it('logout navigates to signup', () => {
    presenter.start()

    presenter.logout()

    verify(router.navigate('/login')).once()
});

it('click on logout erase session', () => {
    presenter.start()

    presenter.logout()

    verify(session.logout()).once()
});

it('do post request with given username, content and password', async () => {
    when(session.getSession()).thenReturn({name: "@alice", token: "1234"})
    when(session.isAuthenticated()).thenReturn(true)
    presenter.start()
    presenter.setContent("What a beautiful day")

    await presenter.doPost()

    verify(post.exec("@alice", "1234", "What a beautiful day")).called()
})

it('post can not be empty', async () => {
    when(session.getSession()).thenReturn({name: "@alice", token: "1234"})
    when(session.isAuthenticated()).thenReturn(true)
    presenter.start()
    presenter.setContent('')

    await presenter.doPost()

    expect(presenter.isPostDisabled()).toEqual(true)
    verify(post.exec(anything(), anything(), anything())).never()
})

it('post can not be longer than 140 chars', async () => {
    when(session.getSession()).thenReturn({name: "@alice", token: "1234"})
    presenter.start()
    presenter.setContent(longPost)

    await presenter.doPost()

    expect(presenter.isPostDisabled()).toEqual(true)
    verify(post.exec(anything(), anything(), anything())).never()
})

it('user must be logged in to post', async () => {
    when(session.isAuthenticated()).thenReturn(false)
    presenter.start()
    presenter.setContent("What a beautiful day")

    await presenter.doPost()

    verify(post.exec(anything(), anything(), anything())).never()
})

beforeEach(() => {
    router = mock<Router>()
    post = mockEq<Post>()
    session = mock<SessionState>()
    presenter = new HomePresenter(changed, instance(session), instance(post), instance(router))
    longPost = "This is a really long post This is a really long post This is a really long post This is a really long post This is a really long post This is a really long post This is a really long postThis is a really long post This is a really long post"
})

let router: Router
let presenter: HomePresenter
let session: SessionState
let post: Post
let longPost: string
const changed = () => {}
