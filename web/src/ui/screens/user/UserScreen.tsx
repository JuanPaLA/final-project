import {WebAppServices} from "@/ui/WebApp";
import {HomePresenter} from "@/ui/screens/home/HomePresenter";
import {UserPresenter} from "@/ui/screens/user/UserPresenter";
import {useAppPresenter} from "@/ui/lib/presenters/useAppPresenter";
import Header from "@/ui/layout/Header";
import React from "react";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import styled from "styled-components";
import {colors} from "@/ui/layout/styles/Globals";


const userPresenter = (onChange, services: WebAppServices) => new UserPresenter(
    onChange,
    services.session,
    services.read,
    services.router
)

export const UserScreen = ({user}) => {
    const presenter = useAppPresenter(userPresenter)

    return(
        <>
            <StyledNav>
                <SecondaryButton
                    onClick={() => presenter.navigateToHome()}
                    value={"Home"}
                />
                <h1>{user}</h1>
            </StyledNav>

            {presenter.model.posts.length > 0 && (
                <div>
                    {presenter.model.posts.map((post, i) => (
                        <div key={i}>
                            {post.content}
                        </div>
                    ))}
                </div>
            )}
           </>
    )
}

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