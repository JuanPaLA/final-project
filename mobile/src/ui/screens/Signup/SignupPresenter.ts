import { ChangeFunc } from '@/ui/services/presenters/ChangeFunc'
import { Core } from '@/core/infrastructure/Core'
import { SessionStorage } from '@/ui/services/session/SessionStorage'

export class SignupPresenter {
    private _model = new SignupVM()

    constructor(private onChange: ChangeFunc, private core: Core, private session: SessionStorage) {}

    get model() {
        return this._model
    }

    async start() {

    }

    async signup() {
        await this.core.signUp().exec(this.model.name, this.model.password)
        console.log('signup! :)')
    }
}

class SignupVM {
    name: string = '@eze5'
    password: string = '1234'
}
