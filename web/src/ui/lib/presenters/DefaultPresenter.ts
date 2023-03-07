import { ChangeFunc } from '@/ui/lib/presenters/ChangeFunc'

export abstract class DefaultPresenter<TModel = any> {
    protected _model: TModel

    protected constructor(protected onChange: ChangeFunc) {
        this._model = {} as TModel
    }

    get model(): TModel {
        return this._model
    }

    protected set model(value: TModel) {
        this._model = value
    }

    protected updateModel<K extends keyof TModel>(changes: Pick<TModel, K>): void {
        this._model = {...this.model, ...changes}
        this.onChange(this._model)
    }
}
