import { HomePresenter } from '@/ui/screens/Home/HomePresenter'
import { Core } from '@/core/Core'
import { Router } from '@/ui/services/router/Router'
import { instance, mock } from 'ts-mockito'

it('counter starts in 0', () => {
    presenter.start()

    expect(presenter.model.counter).toEqual(0)
})

it('increments adds 1 to counter', () => {
    presenter.start()

    presenter.increment()

    expect(presenter.model.counter).toEqual(1)
})

beforeEach(() => {
    core = mock<Core>()
    router = mock<Router>()
    presenter = new HomePresenter(changed, instance(core), instance(router))
})

let core: Core
let router: Router
let presenter: HomePresenter
const changed = () => {}
