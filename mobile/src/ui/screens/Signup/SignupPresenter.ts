import { ChangeFunc } from '@/ui/services/presenters/ChangeFunc'
import { Core } from '@/core/infrastructure/Core'
import { SessionStorage } from '@/ui/services/session/SessionStorage'

export class SignupPresenter {
    private _model = { name: '', password: '', error: '' }

    constructor(private onChange: ChangeFunc, public core: Core, private session: SessionStorage) {}

    get model() {
        return this._model
    }

    async start() { }

    setName(name: string) {
        this.model.name = name
        this.onChange(this.model)
    }

    setPassword(password: string) {
        this.model.password = password
        this.onChange(this.model)
    }

    setError(error: string) {
        this.model.error = error
        this.onChange(this.model)
    }

    async signup() {
        try {
            if (!this.isSignupEnabled()) return
            let response = await this.core.signUp().exec(this.model.name, this.model.password)
        } catch (e) {
            this.setError(e.response.error)
        }
    }

    isSignupEnabled(): boolean {
        return (
            this.model.name.length > 3 &&
            this.model.name.length < 10 &&
            this.model.password.length > 3 &&
            this.model.password.length < 10 &&
            this.model.name[0] == '@' &&
            this.model.name.indexOf(' ') == -1
        )
    }
}

interface SignupVM {
    name: string
    password: string
    error
}
