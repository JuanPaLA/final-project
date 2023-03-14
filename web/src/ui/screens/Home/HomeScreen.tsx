import {WebAppServices} from "@/ui/WebApp";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import {HomePresenter} from "@/ui/screens/Home/HomePresenter";
import styled from "styled-components";
import {colors, Container, StyledMain} from "@/ui/layout/styles/Globals";

const homePresenter = (onChange, services: WebAppServices) => new HomePresenter(onChange, services.session, services.router)

export const HomeScreen = () => {
    const presenter = useAppPresenter(homePresenter)
    presenter.start()
    return (<>
            <StyledNav>
                <a href={"/"}>Home</a>
                <h1>Welcome {presenter.getName()} </h1>
                <a href="#" onClick={() => presenter.logout()}>Logout</a>
            </StyledNav>
        <StyledMain>

        </StyledMain>
    </>
    )
}

const StyledNav = styled.nav`
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: ${colors.black};
  color: ${colors.white};
  padding: 3vw 1vh;
  border: 0.5px solid ${colors.white}; 
  max-height: 100px;
`