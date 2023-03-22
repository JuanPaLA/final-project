import {WebAppServices} from "@/ui/WebApp";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import {Container, FormControl, StyledContainer, StyledMain, StyledNav} from "@/ui/layout/styles/Globals";
import {LoginPresenter} from "@/ui/screens/login/LoginPresenter";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import React from "react";

const loginPresenter = (onChange, services: WebAppServices) => new LoginPresenter(onChange, services.login, services.router)

export const LoginScreen = () => {
    const presenter = useAppPresenter(loginPresenter)

    const inputRef = React.useRef<HTMLInputElement>(null)

    React.useEffect(() => {
        inputRef.current?.focus()
    }, [])

    return (
        <StyledContainer>
            <StyledNav></StyledNav>
            <StyledMain>
                <FormControl>
                    <h1>Login</h1>
                    <FormControl>
                        <label>Name:</label>
                        <input
                            value={presenter.model.username}
                            ref={inputRef}
                            onChange={(e) => presenter.setUsername(e.target.value)}
                        />
                    </FormControl>
                    <FormControl>
                        <label>Password:</label>
                        <input value={presenter.model.password}
                               onChange={(e) => presenter.setPassword(e.target.value)}/>
                    </FormControl>
                    <button onClick={() => !presenter.doLogin()} disabled={!presenter.isLoginEnabled()}>Login</button>
                </FormControl>
                <p id={"error"}>{presenter.model.error}</p>
                <SecondaryButton
                    onClick={() => presenter.navigateToCreate()}
                    value={"Create account"}
                />
            </StyledMain>
            <StyledNav></StyledNav>
        </StyledContainer>
    )
}