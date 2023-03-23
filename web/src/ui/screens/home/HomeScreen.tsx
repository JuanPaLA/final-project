import {WebAppServices} from "@/ui/WebApp";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import {HomePresenter} from "@/ui/screens/home/HomePresenter";
import {Container, StyledContainer, StyledMain, StyledNav} from "@/ui/layout/styles/Globals";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import React from "react";
import SearchBar from "@/ui/components/searchBar/SearchBar";
import {TweetList} from "@/ui/components/lists/TweetList";
import {NewTweet} from "@/ui/components/lists/NewTweet";
import {UserList} from "@/ui/components/lists/UserList";

const homePresenter = (onChange, services: WebAppServices) => new HomePresenter(
    onChange,
    services.session,
    services.post,
    services.listUsers,
    services.follow,
    services.router,
    services.wall,
    services.unfollow
)

export const HomeScreen = () => {
    const presenter = useAppPresenter(homePresenter)
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
                <UserList
                    users={presenter.model.users.filter(u => u.isFollowee == true)}
                    title={"Following"}
                />
                <UserList
                    users={presenter.model.users.filter(u => u.isFollower == true)}
                    title={"Follows you"}
                />
            </StyledNav>
            <StyledMain>
                <Container>
                    <NewTweet presenter={presenter}/>
                    <TweetList posts={presenter.model.posts}/>
                </Container>
            </StyledMain>
            <SearchBar
                current={presenter.getName()}
                users={presenter.model.users}
                follow={(e) => presenter.doFollow(e)}
                unfollow={(e) => presenter.doUnfollow(e)}
            />
        </StyledContainer>
    )
}
