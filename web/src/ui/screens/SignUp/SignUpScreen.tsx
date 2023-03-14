import {WebAppServices} from '@/ui/WebApp'
import {SignUpPresenter} from '@/ui/screens/SignUp/SignUpPresenter'
import {useAppPresenter} from '@/ui/lib/presenters/useAppPresenter'
import {Container, FormControl, StyledAnchor} from '@/ui/layout/styles/Globals.js'

const signUpPresenter = (onChange, services: WebAppServices) =>
    new SignUpPresenter(onChange, services.signup, services.router)

export const SignUpScreen = () => {
    const presenter = useAppPresenter(signUpPresenter)
    return (
        <Container>
            <FormControl>
                <h1>Signup</h1>
                <FormControl>
                    <label>Name:</label>
                    <input value={presenter.model.username} onChange={(e) => presenter.setUsername(e.target.value)} />
                </FormControl>
                <FormControl>
                    <label>Password:</label>
                    <input value={presenter.model.password} onChange={(e) => presenter.setPassword(e.target.value)} />
                </FormControl>
                <button onClick={()=>presenter.doSignup()}  disabled={!presenter.isSignupEnabled()}>
                    Signup
                </button>
            </FormControl>
            <StyledAnchor href={"/login"}>
                Login
            </StyledAnchor>
        </Container>
    )
}
