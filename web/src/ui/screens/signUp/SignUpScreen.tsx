import {WebAppServices} from '@/ui/WebApp'
import {SignUpPresenter} from '@/ui/screens/signUp/SignUpPresenter'
import {useAppPresenter} from '@/ui/lib/presenters/useAppPresenter'
import {FormControl, StyledContainer, StyledMain, StyledNav} from '@/ui/layout/styles/Globals.js'
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";

const signUpPresenter = (onChange, services: WebAppServices) =>
    new SignUpPresenter(onChange, services.signup, services.router)

export const SignUpScreen = () => {
    const presenter = useAppPresenter(signUpPresenter)
    return (
        <StyledContainer>
            <StyledNav></StyledNav>
            <StyledMain>
                <FormControl>
                    <h1>Signup</h1>
                    <FormControl>
                        <label>Name:</label>
                        <input value={presenter.model.username}
                               onChange={(e) => presenter.setUsername(e.target.value)}/>
                    </FormControl>
                    <FormControl>
                        <label>Password:</label>
                        <input value={presenter.model.password}
                               onChange={(e) => presenter.setPassword(e.target.value)}/>
                    </FormControl>
                    <button onClick={() => presenter.doSignup()} disabled={!presenter.isSignupEnabled()}>
                        Signup
                    </button>
                </FormControl>
                <p>{presenter.model.error}</p>
                <SecondaryButton
                    onClick={() => presenter.navigateToLogin()}
                    value={"Login"}
                />
            </StyledMain>
            <StyledNav></StyledNav>
        </StyledContainer>
    )
}


