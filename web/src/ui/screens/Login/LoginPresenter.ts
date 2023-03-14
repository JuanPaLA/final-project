import { ChangeFunc } from "@/ui/lib/presenters/ChangeFunc";
import { Router } from "@/ui/services/router/Router";
import { Login } from "@/core/useCases/Login";
import { DefaultPresenter } from "@/ui/lib/presenters/DefaultPresenter";
import {UserNotFoundError} from "@/core/infrastructure/UserNotFoundError";
import {InvalidCredentialsError} from "@/core/infrastructure/InvalidCredentialsError";

export class LoginPresenter extends DefaultPresenter<LoginVM> {
    constructor(onChange: ChangeFunc, private login: Login, private router: Router) {
        super(onChange)
        this.model = { username: '', password: '', error: '' }
    }

    start() { }

    setUsername(username: string) {
        this.updateModel({ username })
    }

    setPassword(password: string) {
        this.updateModel({ password })
    }

    setError(error: string) {
        this.updateModel({ error })
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
            this.router.navigate('/home')
        } catch (e) {
            if (e instanceof InvalidCredentialsError) {
                this.setError(`Invalid credentials for ${this.model.username}`)
            } else if (e instanceof UserNotFoundError) {
                this.setError(`User not found`)
            } else {
                this.setError(`Unexpected error`)
            }
        }
        this.setPassword('')
        this.setUsername('')
    }
}

interface LoginVM {
    username: string
    password: string
    error: string
}
