import {WebAppServices} from "@/ui/WebApp";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import React from "react";
import styled from "styled-components";
import {colors, Container, StyledContainer, StyledMain, StyledNav} from "@/ui/layout/styles/Globals";

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
                <PostList>
                    {presenter.model.posts.length > 0 && (
                        presenter.model.posts.map((post) => (
                            <Post key={post.id}>
                                <h3>{post.content}</h3>
                                <h4>{post.author}</h4>
                                <h4>{post.date}</h4>
                            </Post>
                        ))
                    )}
                </PostList>
            </StyledMain>
            <StyledNav></StyledNav>
        </StyledContainer>
    )
}

const PostList = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 4vh;
  justify-content: flex-start;
`;

const Post = styled.div`
  margin: 10px;
  color: aliceblue;
  display: flex;
  flex-direction: column;
`;
