import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";
import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {Router} from "@/ui/services/router/Router";
import {SessionState} from "@/session/SessionState";

export class HomePresenter extends DefaultPresenter<HomeVM> {
    constructor(onChange: ChangeFunc, private session: SessionState, private router: Router) {
        super(onChange);
        this.model = { username: '', token: '' }
    }

    start() { }

    logout() {
        this.session.logout()
        this.router.navigate('/login')
    }

    getName(): string {
        try {
            return this.session.getSession().name
        } catch (e) {
            this.router.navigate('/login')
        }
    }
}

interface HomeVM {
    username: string,
    token: string
}