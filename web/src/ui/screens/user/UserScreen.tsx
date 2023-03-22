import {WebAppServices} from "@/ui/WebApp";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import React from "react";
import {StyledContainer, StyledMain, StyledNav} from "@/ui/layout/styles/Globals";
import {TweetList} from "@/ui/components/lists/TweetList";
import {UserList} from "@/ui/components/lists/UserList";
import {useRouter} from "next/router";

const userPresenter = (onChange, services: WebAppServices) => new UserPresenter(
    onChange,
    services.session,
    services.read,
    services.router,
    services.followers,
    services.followings
)

export const UserScreen = () => {
    const presenter = useAppPresenter(userPresenter)
    const router = useRouter()

    React.useEffect(() => {
        presenter.start()
    }, [router.query])

    return (
        <StyledContainer>
            <StyledNav>
                <UserList
                    users={presenter.model.followers}
                    title={"Followers"}
                />
                <UserList
                    users={presenter.model.followings}
                    title={"Following"}
                />
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
