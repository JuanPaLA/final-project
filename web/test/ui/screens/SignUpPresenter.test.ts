import { SignUpPresenter } from '@/ui/screens/signUp/SignUpPresenter'
import { Router } from '@/ui/services/router/Router'
import { Signup } from "@/core/useCases/Signup";
import { anything, instance, mock, verify, when } from 'ts-mockito'
import { expect } from "expect";
import {mockEq} from "../../common/ts-mockito-extensions";

it('name and passwords starts empty', () => {
    presenter.start()

    expect(presenter.model.username).toEqual(``)
    expect(presenter.model.password).toEqual(``)
})

it('signup is disabled until username has 4 characters long', () => {
    presenter.start()

    presenter.setUsername("bob")

    expect(presenter.isSignupEnabled()).toEqual(false)
})

it('signup is disabled until password has 4 characters long', () => {
    presenter.start()

    presenter.setPassword("pas")

    expect(presenter.isSignupEnabled()).toEqual(false)
})

it('username must start with @', () => {
    presenter.start()

    presenter.setPassword(`1234`)
    presenter.setUsername(`alice`)

    expect(presenter.isSignupEnabled()).toEqual(false)
    verify(signup.exec(`alice`, `1234`)).never()
})

it('usernmame must be one single word', () => {
    presenter.start()

    presenter.setPassword(`1234`)
    presenter.setUsername(`@alice bob`)

    expect(presenter.isSignupEnabled()).toEqual(false)
})

it('valid username and password enable signup', async ()=> {
    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    expect(presenter.isSignupEnabled()).toEqual(true)
})

it('successful signup navigates to welcome', async () => {
    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    await presenter.doSignup()

    verify(router.navigate('/login')).once()
})

it('do signup request with given username and password', () => {
    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    presenter.doSignup()

    verify(signup.exec(`@alice`, `1234`)).once()
})

it('failed signup reset username and password', async () => {
    when(signup.exec(anything(), anything())).thenReject(new Error('error'))

    presenter.start()

    presenter.setUsername("@alice")
    presenter.setPassword("1234")

    await presenter.doSignup()

    expect(presenter.model.password).toEqual('')
    expect(presenter.model.username).toEqual('')
})

beforeEach(() => {
    router = mockEq<Router>()
    signup = mock<Signup>()
    presenter = new SignUpPresenter(changed, instance(signup), instance(router))
})

let router: Router
let presenter: SignUpPresenter
let signup : Signup
const changed = () => {}



















