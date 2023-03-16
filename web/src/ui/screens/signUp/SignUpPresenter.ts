import { DefaultPresenter } from '@/ui/lib/presenters/DefaultPresenter'
import { ChangeFunc } from '@/ui/lib/presenters/ChangeFunc'
import { Router } from '@/ui/services/router/Router'
import { Signup } from "@/core/useCases/Signup";
import {RepeatedUserError} from "@/core/infrastructure/RepeatedUserError";

export class SignUpPresenter extends DefaultPresenter<SignUpVM> {
    constructor(onChange: ChangeFunc, private signup: Signup, private router: Router) {
        super(onChange)
        this.model = { username: '', password: '', error: '' }
    }

    start() {
        console.log('presenter started')
    }

    setUsername(username: string) {
        this.updateModel({ username })
    }

    setError(error: string) {
        this.updateModel({ error })
    }

    setPassword(password: string) {
        this.updateModel({ password })
    }

    isSignupEnabled(): boolean {
        return (
            this.model.username.length > 3 &&
            this.model.password.length > 3 &&
            this.model.username[0] == '@' &&
            this.model.username.indexOf(' ') == -1
        )
    }

    async doSignup() {
        if (!this.isSignupEnabled()) return
        try {
            await this.signup.exec(this.model.username, this.model.password)
            this.setPassword('')
            this.setUsername('')
            this.router.navigate('/login')
        } catch (e) {
            if (e instanceof RepeatedUserError) {
                this.setError(e.message)
            } else {
                this.setError(e.message || e.error)
            }
        }
        this.setPassword('')
        this.setUsername('')
    }

    navigateToLogin() {
        this.router.navigate("/login")
    }
}

interface SignUpVM {
    username: string
    password: string
    error: string
}
