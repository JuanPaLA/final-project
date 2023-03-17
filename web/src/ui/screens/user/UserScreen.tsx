import {WebAppServices} from "@/ui/WebApp";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import React from "react";
import styled from "styled-components";
import {colors, Container, StyledContainer} from "@/ui/layout/styles/Globals";

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
            <Container>
                {presenter.model.posts.length > 0 && (
                <PostList>
                    {presenter.model.posts.map((post, i) => (
                        <Post key={i}>
                            {post.author}: {post.content} - {post.date}
                        </Post>
                    ))}
                </PostList>
                )}
            </Container>
            <StyledNav></StyledNav>
        </StyledContainer>
    )
}

const PostList = styled.div`
    display: grid;
    margin-top: 4vh;
    justify-content: flex-start;
`

const Post = styled.div`
  color: aliceblue;
`

const StyledNav = styled.nav`
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  background-color: ${colors.black};
  color: ${colors.white};
  padding: 3vw 1vh;
  border: 0.1px solid ${colors.white};
  border: 0.1px none;
  max-height: 100px;
`