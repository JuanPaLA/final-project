import {WebAppServices} from "@/ui/WebApp";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import {HomePresenter} from "@/ui/screens/home/HomePresenter";
import styled from "styled-components";
import {colors, Container, StyledNav, StyledMain, StyledContainer} from "@/ui/layout/styles/Globals";
import PrimaryButton from "@/ui/components/buttons/PrimaryButton";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import React from "react";
import SearchBar from "@/ui/components/searchBar/SearchBar";

const homePresenter = (onChange, services: WebAppServices) => new HomePresenter(
    onChange,
    services.session,
    services.post,
    services.listUsers,
    services.follow,
    services.router
)

export const HomeScreen = () => {
    const presenter = useAppPresenter(homePresenter)
    presenter.start()
    return (
        <StyledContainer>
            <StyledNav>
                <SecondaryButton
                    onClick={() => presenter.navigateToUser()}
                    value={presenter.getName()}
                />
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
            <SearchBar users={presenter.model.users} onClick={(e) => presenter.doFollow(e) } />
        </StyledContainer>
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