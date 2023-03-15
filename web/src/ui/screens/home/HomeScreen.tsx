import {WebAppServices} from "@/ui/WebApp";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import {HomePresenter} from "@/ui/screens/home/HomePresenter";
import styled from "styled-components";
import {colors, Container, StyledMain} from "@/ui/layout/styles/Globals";
import PrimaryButton from "@/ui/components/buttons/PrimaryButon";
import Header from "@/ui/layout/Header";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import React from "react";

const homePresenter = (onChange, services: WebAppServices) => new HomePresenter(
    onChange,
    services.session,
    services.post,
    services.router
)

export const HomeScreen = () => {
    const presenter = useAppPresenter(homePresenter)
    presenter.start()
    return (
        <>
            <StyledNav>
                <h1>
                    <SecondaryButton
                        onClick={() => presenter.navigateToUser()}
                        value={presenter.getName()}
                    />
                </h1>
                <SecondaryButton
                    onClick={() => presenter.logout()}
                    value={"logout"}
                />
            </StyledNav>
            <StyledMain>
                <Container>
                    <StyledInput
                        type={"text"}
                        value={presenter.model.content}
                        onChange={(e) => presenter.setContent(e.target.value)}
                        placeholder={"What's on your mind?"}
                    >
                    </StyledInput>
                    <PrimaryButton
                        onClick={() => presenter.doPost()}
                        value="Tweet!"
                        disabled={presenter.isPostDisabled()}
                    />
                </Container>
            </StyledMain>
        </>
    )
}

const StyledInput = styled.input`
  width: 100%;
  height: 120px;
  border: 0.1px solid ${colors.gray};
  border-radius: 5px;
  border-top: none;
  padding: 10px;
  font-size: 1.2rem;
  margin-bottom: 10px;
  background-color: ${colors.black};
  color: ${colors.white};
`

const StyledNav = styled.nav`
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: ${colors.black};
  color: ${colors.white};
  padding: 3vw 1vh;
  border: 0.1px solid ${colors.white};
  border: 0.1px none;
  border-bottom-style: solid;
  max-height: 100px;
`