import { expect } from "expect";
import {anything, instance, mock, verify, when} from "ts-mockito";
import { Router } from "@/ui/services/router/Router";
import { Login } from "@/core/useCases/Login";
import { LoginPresenter } from "@/ui/screens/login/LoginPresenter";

it('name and passwords starts empty', () => {
    presenter.start()

    expect(presenter.model.username).toEqual(``)
    expect(presenter.model.password).toEqual(``)
})

it('login is disabled until username has 4 characters long', () => {
    presenter.start()

    presenter.setUsername("bob")

    expect(presenter.isLoginEnabled()).toEqual(false)
})

it('login is disabled until password has 4 characters long', () => {
    presenter.start()

    presenter.setPassword("pas")

    expect(presenter.isLoginEnabled()).toEqual(false)
})

it('username must start with @', () => {
    presenter.start()

    presenter.setPassword(`1234`)
    presenter.setUsername(`alice`)

    expect(presenter.isLoginEnabled()).toEqual(false)
    verify(login.exec(`alice`, `1234`)).never()
})

it('username must be one single word', () => {
    presenter.start()

    presenter.setPassword(`1234`)
    presenter.setUsername(`@alice bob`)

    expect(presenter.isLoginEnabled()).toEqual(false)
})

it('valid username and password enable login', async ()=> {
    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    expect(presenter.isLoginEnabled()).toEqual(true)
})

it('successful login navigates to welcome', async () => {
    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    await presenter.doLogin()

    verify(router.navigate('/home')).once()
})

it('do login request with given username and password', () => {
    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    presenter.doLogin()

    verify(login.exec(`@alice`, `1234`)).once()
})

beforeEach(() => {
    router = mock<Router>()
    login = mock<Login>()
    presenter = new LoginPresenter(changed, instance(login), instance(router))
})

let router: Router
let presenter: LoginPresenter
let login : Login
const changed = () => {}