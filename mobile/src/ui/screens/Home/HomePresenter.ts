import { ChangeFunc } from '@/ui/services/presenters/ChangeFunc'
import { Core } from '@/core/infrastructure/Core'
import { SessionStorage } from '@/ui/services/session/SessionStorage'

export class HomePresenter {
    private _model = new HomeVM()

    constructor(private onChange: ChangeFunc, private core: Core, private session: SessionStorage) {}

    get model() {
        return this._model
    }

    async start() {

    }
}

class HomeVM {
}
