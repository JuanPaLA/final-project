import { WebAppServices } from '@/ui/WebApp'
import { SignUpPresenter } from '@/ui/screens/Home/SignUpPresenter'
import { useAppPresenter } from '@/ui/lib/presenters/useAppPresenter'
import styled from 'styled-components'
import { Container, StyledForm } from '@/ui/layout/styles/Globals.js'

const signUpPresenter = (onChange, services: WebAppServices) =>
    new SignUpPresenter(onChange, services.signup, services.router)

export const SignUpScreen = () => {
    const presenter = useAppPresenter(signUpPresenter)
    return (
        <Container>
            <div>
                <h1>Signup Form</h1>
                <FormControl>
                    <label>Name:</label>
                    <input value={presenter.model.username} onChange={(e) => presenter.setUsername(e.target.value)} />
                </FormControl>
                <FormControl>
                    <label>Password:</label>
                    <input value={presenter.model.password} onChange={(e) => presenter.setPassword(e.target.value)} />
                </FormControl>
                <input type="submit" value="SignUp"/>
            </div>
            <StyledAnchor href={"#"} onClick={() => presenter.doSignup()}>
                Login
            </StyledAnchor>
        </Container>
    )
}

const FormControl = styled.div`
    margin-bottom: 10px;
`
const StyledAnchor = styled.a`
    margin-top: 10px;
    text-decoration: none;
    color: dodgerblue;
`
