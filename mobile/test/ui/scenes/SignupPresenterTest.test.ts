import {SignupPresenter} from '@/ui/screens/Signup/SignupPresenter';
import {anything, instance, verify, when} from 'ts-mockito';
import {Core} from '@/core/infrastructure/Core';
import {SessionStorage} from '@/ui/services/session/SessionStorage';
import {ChangeFunc} from "@/ui/services/presenters/ChangeFunc";
import {usePresenterFactory} from "@/ui/components/context/PresentersContext";
import {usePresenter} from "@/ui/services/presenters/usePresenter";
import {InvalidAuthError} from "@/core/model/auth/InvalidAuthError";
import {mockEq} from "../../../../web/test/common/ts-mockito-extensions";
import {SignUp} from "@/core/useCases/Signup";
import {HttpAuthService} from "@/core/infrastructure/http/HttpAuthService";
import {AxiosHttpClient} from "@/core/infrastructure/http/axios/AxiosHttpClient";
import { AuthService } from '@/core/model/auth/AuthService'

it('name and passwords starts empty', () => {
    presenter.start()

    expect(presenter.model.name).toEqual('')
    expect(presenter.model.password).toEqual('')
})

it('valid names have 4 minimum characters long and 10 max', () => {
    presenter.start()
    presenter.setPassword(`1234`)

    presenter.setName('bob')

    expect(presenter.isSignupEnabled()).toEqual(false)

    presenter.setName('AlejandroMagno')

    expect(presenter.isSignupEnabled()).toEqual(false)
})

it('valid pass have 4 minimum characters long and 10 max', () => {
    presenter.start()
    presenter.setName('@bob')

    presenter.setPassword(`123`)

    expect(presenter.isSignupEnabled()).toEqual(false)

    presenter.setPassword(`1234567891234`)

    expect(presenter.isSignupEnabled()).toEqual(false)
})

it('username must start with @', () => {
    presenter.start()

    presenter.setName(`alice`)
    presenter.setPassword(`1234`)

    expect(presenter.isSignupEnabled()).toEqual(false)
})

it('failed signup shows error message', async () => {
    when(await signUp.exec(anything(), anything())).thenReject(new Error("There was a network error"))
    presenter.setName(`@alice`)
    presenter.setPassword(`1234`)

    await presenter.signup()

    expect(presenter.model.error).toEqual("There was a network error")
})

beforeEach(() => {
    core = mockEq(Core)
    signUp = mockEq(SignUp)
    when(core.signUp()).thenReturn(instance(signUp))
    session = mockEq<SessionStorage>()
    presenter = new SignupPresenter(onChange, instance(core), instance(session))
})

let presenter: SignupPresenter
let core: Core
let signUp: SignUp
let session: SessionStorage
const onChange: ChangeFunc = () => { }
