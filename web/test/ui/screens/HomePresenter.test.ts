import {Router} from "@/ui/services/router/Router";
import {HomePresenter} from "@/ui/screens/home/HomePresenter";
import {anything, instance, mock, verify, when} from "ts-mockito";
import {SessionState} from "@/session/SessionState";
import {Post} from "@/core/useCases/Post";
import {expect} from "expect";
import {mockEq} from "../../common/ts-mockito-extensions";
import {GetUsers} from "@/core/useCases/GetUsers";
import {Follow} from "@/core/useCases/Follow";
import {Wall} from "@/core/useCases/Wall";
import {Unfollow} from "@/core/useCases/Unfollow";
import {Followers} from "@/core/useCases/Followers";
import {FollowingService} from "@/core/model/FollowingService";

it('logout navigates to signup', () => {
    when(session.isAuthenticated()).thenReturn(true)
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
    presenter.setContent("What a beautiful day")

    await presenter.doPost()

    verify(post.exec("@alice", "What a beautiful day", "1234")).called()
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

it('users starts empty', () => {
    presenter.start()

    expect(presenter.model.posts).toEqual([])
})

it('users are fetched on mounting', async () => {
    when(session.isAuthenticated()).thenReturn(true)
    when(session.getSession()).thenReturn({name: "alice", token: "1234"})

    await presenter.start()

    verify(listUsers.exec(anything(), anything())).once()
})

it('follow', async() => {
    when(session.isAuthenticated()).thenReturn(true)
    when(session.getSession()).thenReturn({name: "alice", token: "1234"})

    await presenter.start()

    await presenter.doFollow("@bob")

    verify(follow.exec(anything(), anything(), anything())).once()
})

it('do wall request at beginning to bring timeline', async () => {
    when(session.isAuthenticated()).thenReturn(true)
    when(session.getSession()).thenReturn({name: "@alice", token: "aToken"})

    await presenter.start()

    verify(wall.exec("@alice", "aToken")).once()
})

it('do unfollow request of given user', async () => {
    when(session.isAuthenticated()).thenReturn(true)
    when(session.getSession()).thenReturn({name: "@alice", token: "aToken"})

    await presenter.doUnfollow("@bob")

    verify(unfollow.exec("@alice", "@bob", "aToken")).once()
})

beforeEach(() => {
    router = mock<Router>()
    post = mockEq<Post>()
    follow = mockEq<Follow>()
    unfollow = mockEq<Unfollow>()
    followers = mockEq<Followers>()
    listUsers = mockEq<GetUsers>()
    session = mock<SessionState>()
    wall = mockEq<Wall>()
    presenter = new HomePresenter(changed, instance(session), instance(post), instance(listUsers), instance(follow), instance(router), instance(wall), instance(unfollow))
    longPost = "This is a really long post This is a really long post This is a really long post This is a really long post This is a really long post This is a really long post This is a really long postThis is a really long post This is a really long post"
})

let router: Router
let presenter: HomePresenter
let session: SessionState
let post: Post
let follow: Follow
let unfollow: Unfollow
let followers: Followers
let listUsers: GetUsers
let longPost: string
let wall: Wall
const changed = () => {}
