import {createGlobalStyle} from "styled-components";
import styled from "styled-components";

export const colors = {
    primary: "#0070f3",
    secondary: "#ff0080",
    black: "#000000",
    white: "whitesmoke",
    gray: "#ccc",
    lightGray: "#eee"
};

const size = {
    small: '425px',
    medium: '768px',
    large: '1024px',
}

export const device = {
    small: `(max-width: ${size.small})`,
    medium: `(max-width: ${size.medium})`,
    mediumlarge: `(min-width: ${size.medium}) and (max-width: ${size.large})`,
    large: `(min-width: ${size.large})`,
    extralarge: `(min-width: 1200px)`,
};

export const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`

export const StyledForm = styled.form`
  padding: 4vh;
`

export const FormControl = styled.div`
    margin-bottom: 10px;
`
export const StyledAnchor = styled.a`
    margin-top: 10px;
    text-decoration: none;
    color: dodgerblue;
`

export const StyledMain = styled.div `
  display: flex;
  justify-content: center;
  align-items: flex-start;
  background-color: ${colors.black};
  height: 100vh;
  width: 100vw;
  color: ${colors.white}
`