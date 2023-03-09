import {WebAppServices} from "@/ui/WebApp";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import {Container, StyledForm} from "@/ui/layout/styles/Globals";
import {LoginUpPresenter} from "@/ui/screens/Home/LoginUpPresenter";

const loginPresenter = (onChange, services: WebAppServices) => new LoginUpPresenter(onChange, services.signup, services.router)

export const LoginScreen = () => {
    const presenter = useAppPresenter(loginPresenter)

    return (
        <Container>
            <StyledForm>

            </StyledForm>
        </Container>
    )
}