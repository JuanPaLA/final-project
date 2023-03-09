import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {Router} from "@/ui/services/router/Router";
import {Login} from "../../../core/useCases/Login";
import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";

export class LoginUpPresenter extends DefaultPresenter<LoginVM> {
    constructor(onChange: ChangeFunc, private login: Login, private router: Router) {
        super(onChange)
        this.model = { username: '', password: '' }
    }

    start() { }

    setUsername(username: string) {
        this.updateModel({ username })
    }

    setPassword(password: string) {
        this.updateModel({ password })
    }

    isLoginEnabled(): boolean {
        return (
            this.model.username.length > 3 &&
            this.model.password.length > 3 &&
            this.model.username[0] == '@' &&
            this.model.username.indexOf(' ') == -1
        )
    }

    async doLogin() {
        if (!this.isLoginEnabled()) return
        try {
            await this.login.exec(this.model.username, this.model.password)
            this.router.navigate('/welcome')
        } catch (e) {
            console.error(e)
            this.router.navigate('/error')
        }
    }
}

interface LoginVM {
    username: string
    password: string
}
