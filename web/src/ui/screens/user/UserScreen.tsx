import {WebAppServices} from "@/ui/WebApp";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import React from "react";
import {StyledContainer, StyledMain, StyledNav} from "@/ui/layout/styles/Globals";
import {TweetList} from "@/ui/components/post/TweetList";

const userPresenter = (onChange, services: WebAppServices) => new UserPresenter(
    onChange,
    services.session,
    services.read,
    services.router
)

export const UserScreen = ({user}) => {
    const presenter = useAppPresenter(userPresenter)

    return (
        <StyledContainer>
            <StyledNav>
            </StyledNav>
            <StyledMain>
                {presenter.model.posts.length > 0 &&
                    <TweetList posts={presenter.model.posts}/>
                }
            </StyledMain>
            <StyledNav></StyledNav>
        </StyledContainer>
    )
}

