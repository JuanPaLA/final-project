import {Router} from "@/ui/services/router/Router";
import {mockEq} from "../../common/ts-mockito-extensions";
import {HomePresenter} from "@/ui/screens/Home/HomePresenter";
import {instance, mock, verify} from "ts-mockito";
import {SessionState} from "@/session/SessionState";

it('logout navigates to signup', () => {
    presenter.start()

    presenter.logout()

    verify(router.navigate('/login')).once()
});

it('logout erase session', () => {
    presenter.start()

    presenter.logout()

    verify(session.logout()).once()
});

beforeEach(() => {
    router = mock<Router>()
    session = mockEq<SessionState>()
    presenter = new HomePresenter(changed, instance(session), instance(router))
})

let router: Router
let presenter: HomePresenter
let session: SessionState
const changed = () => {}
